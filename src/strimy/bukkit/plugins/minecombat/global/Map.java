package strimy.bukkit.plugins.minecombat.global;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bukkit.Location;
import org.bukkit.World;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import strimy.bukkit.plugins.minecombat.MineCombat;


public class Map 
{
	private static ArrayList<Map> listMaps = new ArrayList<Map>();
	
	private CombatZone zone = new CombatZone();
	private CombatInventory defaultInventorySet;
	private Type[] supportedModes;
	private ReadyRoom readyRoom;
	private String name;
	private ArrayList<Spawn> spawns = new ArrayList<Spawn>();
	
	private static  String path = MineCombat.Instance.getDataFolder() + "/maps.xml";
	private static Document doc;
	private static Element root;
	
	
	public CombatZone getZone() {
		return zone;
	}
	public void setZone(CombatZone zone) {
		this.zone = zone;
	}
	public CombatInventory getDefaultInventorySet() {
		return defaultInventorySet;
	}
	public void setDefaultInventorySet(CombatInventory defaultInventorySet) {
		this.defaultInventorySet = defaultInventorySet;
	}
	public Type[] getSupportedModes() {
		return supportedModes;
	}
	public void setSupportedModes(Type[] supportedModes) {
		this.supportedModes = supportedModes;
	}
	public ReadyRoom getReadyRoom() {
		return readyRoom;
	}
	public void setReadyRoom(ReadyRoom readyRoom) {
		this.readyRoom = readyRoom;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public static ArrayList<Map> getMaps()
	{
		return listMaps;
	}
	
	public static void loadMaps()
	{
		if(!(new File(path).exists()))
		{
			new File(path).getParentFile().mkdirs();
			CreateXmlDocument();
		}
		
		listMaps.clear();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try 
		{
			db = dbf.newDocumentBuilder();
			doc = db.parse(path);
			root = doc.getDocumentElement();
			NodeList mapNodes = root.getElementsByTagName("map");
			
			for (int i = 0; i < mapNodes.getLength(); i++) 
			{
				parseMapElement(mapNodes.item(i));
			}

		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void parseMapElement(Node mapNode)
	{
		Map map = new Map();
		NodeList currentNode = null;
		
		currentNode = ((Element)mapNode).getElementsByTagName("name");
		if(currentNode.getLength() != 0)
		{
			map.setName(currentNode.item(0).getTextContent());
		}
		
		currentNode = ((Element)mapNode).getElementsByTagName("combatzone");
		if(currentNode.getLength() != 0)
		{
			NodeList nodes = ((Element)currentNode.item(0)).getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) 
			{
				Node pointNode = nodes.item(i);
				if(pointNode.getNodeName().equals("point"))
				{
					System.out.print(pointNode);
					Point p = new Point();
					p.X = Double.valueOf(pointNode.getAttributes().getNamedItem("X").getTextContent());
					p.Y = Double.valueOf(pointNode.getAttributes().getNamedItem("Y").getTextContent());
					
					map.getZone().addPoint(p);
				}
			}
		}
		
		currentNode = ((Element)mapNode).getElementsByTagName("spawns");
		if(currentNode.getLength() != 0)
		{
			NodeList nodes = ((Element)currentNode.item(0)).getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) 
			{
				Node spawnNode = nodes.item(i);
				if(spawnNode.getNodeName().equals("spawn"))
				{
					Spawn p = new Spawn();
					
					for(World w : MineCombat.Instance.getServer().getWorlds())
					{
						if(w.getName().equals(spawnNode.getAttributes().getNamedItem("World").getTextContent()))
						{
							Location l = new Location(w, 
									Double.valueOf(spawnNode.getAttributes().getNamedItem("X").getTextContent()),
									Double.valueOf(spawnNode.getAttributes().getNamedItem("Y").getTextContent()),
									Double.valueOf(spawnNode.getAttributes().getNamedItem("Z").getTextContent()));
							
							p.setSpawnPoint(l);
						}
					}
					
					if(p.getSpawnPoint() == null)
					{
						System.out.print("Fail to find the map");
					}
					
					map.addSpawn(p);
				}
			}
		}
		
		listMaps.add(map);
	}
	
	public void save()
	{
		if(!(new File(path).exists()))
		{
			new File(path).getParentFile().mkdirs();
			CreateXmlDocument();
		}
		
		addMap(this);
		
		
	}
	
	private static void addMap(Map map)
	{
		if(doc == null)
			loadMaps();
		
		Element mapElem = doc.createElement("map");
		
		Element nameElem = doc.createElement("name");
		nameElem.setTextContent(map.getName());
		mapElem.appendChild(nameElem);
		
		Element combatZoneElem = doc.createElement("combatzone");
		for (Point point : map.getZone().getPoints()) 
		{
			Element pointElem = doc.createElement("point");
			pointElem.setAttribute("X", String.valueOf(point.X));
			pointElem.setAttribute("Y", String.valueOf(point.Y));
			
			combatZoneElem.appendChild(pointElem);
		}
		mapElem.appendChild(combatZoneElem);
		
		Element spawnsElem = doc.createElement("spawns");
		for (Spawn spawn : map.getSpawns()) 
		{
			Element spawnElem = doc.createElement("spawn");
			spawnElem.setAttribute("X", String.valueOf(spawn.getSpawnPoint().getBlockX()));
			spawnElem.setAttribute("Y", String.valueOf(spawn.getSpawnPoint().getBlockY()));
			spawnElem.setAttribute("Z", String.valueOf(spawn.getSpawnPoint().getBlockZ()));
			spawnElem.setAttribute("World", String.valueOf(spawn.getSpawnPoint().getWorld().getName()));
			
			spawnsElem.appendChild(spawnElem);
		}
		mapElem.appendChild(spawnsElem);
		
		root.appendChild(mapElem);
		SaveXml();
		
		listMaps.add(map);
	}
	
	private static void CreateXmlDocument()
	{
		DocumentBuilderFactory factory   = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try 
		{
			builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			
			doc = impl.createDocument(null, null, null);
			doc.appendChild(doc.createElement("maps"));
			root = doc.getDocumentElement();
			SaveXml();
			
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
	}
	
	private static void SaveXml()
	{
		 try {
		        // Prepare the DOM document for writing
		        Source source = new DOMSource(doc);

		        // Prepare the output file
		        File file = new File(path);
		        Result result = new StreamResult(file);

		        // Write the DOM document to the file
		        Transformer xformer = TransformerFactory.newInstance().newTransformer();
		        xformer.setOutputProperty(OutputKeys.INDENT, "yes");
		        xformer.transform(source, result);
		    } catch (TransformerConfigurationException e) {
		    } catch (TransformerException e) {
		    }

	}
	public void setSpawns(ArrayList<Spawn> spawns) {
		this.spawns = spawns;
	}
	public ArrayList<Spawn> getSpawns() {
		return spawns;
	}
	
	public void addSpawn(Spawn s)
	{
		spawns.add(s);
	}
}
