package strimy.bukkit.plugins.minecombat.builder;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import strimy.bukkit.plugins.minecombat.global.CombatInventory;
import strimy.bukkit.plugins.minecombat.global.Map;
import strimy.bukkit.plugins.minecombat.listeners.GlobalBlockListener;
import strimy.bukkit.plugins.minecombat.listeners.GlobalEntityListener;
import strimy.bukkit.plugins.minecombat.listeners.GlobalPlayerListener;

public class BuilderMode 
{
	private static HashMap<Player, BuilderMode> listBuilders = new HashMap<Player,BuilderMode>();
	private static BuilderBlockListener blockListener;
	private static BuilderPlayerListener playerListener;
	private static BuilderEntityListener entityListener;
	
	private Map map;
	private BuilderState state = BuilderState.None;
	private CombatInventory backupInventory = null;
	private static CombatInventory builderInventory;

	public static boolean isBuilder(Player p) 
	{
		return listBuilders.containsKey(p);
	}
	
	public static CombatInventory getBuilderInventory()
	{
		if(builderInventory == null)
		{
			builderInventory = new CombatInventory();
			builderInventory.getItems()[1] = new ItemStack(Material.DIAMOND_SWORD, 1);
			builderInventory.getItems()[2] = new ItemStack(Material.STONE_PLATE);
			builderInventory.getItems()[3] = new ItemStack(Material.STONE, 64);
			builderInventory.getItems()[4] = new ItemStack(Material.GRASS, 64);
			builderInventory.getItems()[5] = new ItemStack(Material.SAND, 64);
			builderInventory.getItems()[6] = new ItemStack(Material.WOOD, 64);
			builderInventory.getItems()[7] = new ItemStack(Material.ICE, 64);
			
		}
		return builderInventory;
	}
	
	public static BuilderMode getBuilderModeFromPlayer(Player p)
	{
		if(isBuilder(p))
		{
			return listBuilders.get(p);
		}
		return null;
	}
	
	public BuilderMode(Player p) throws Exception
	{
		if(isBuilder(p))
		{
			throw new Exception("What are you doing ?");
		}
		if(blockListener == null)
		{
			blockListener = new BuilderBlockListener();
			GlobalBlockListener.getInstance().addListener(blockListener);
		}
		if(playerListener == null)
		{
			playerListener = new BuilderPlayerListener();
			GlobalPlayerListener.getInstance().addListener(playerListener);
		}
		if(entityListener == null)
		{
			entityListener = new BuilderEntityListener();
			GlobalEntityListener.getInstance().addListener(entityListener);
		}
		listBuilders.put(p, this);

		
		setInventory(p);
		
		p.sendMessage(ChatColor.AQUA + "Entering Builder mode...");
	}
	
	private void setInventory(Player p) 
	{
		backupInventory = CombatInventory.fromPlayer(p);
		backupInventory.setBackup(true);
		
		getBuilderInventory().applyPlayerInventory(p);
	}

	public static void disable(Player p)
	{
		if(listBuilders.containsKey(p))
		{
			listBuilders.get(p).backupInventory.applyPlayerInventory(p);
			listBuilders.remove(p);
		}
		
		if(listBuilders.size() == 0)
		{
			GlobalEntityListener.getInstance().removeListener(entityListener);
			GlobalBlockListener.getInstance().removeListener(blockListener);
			GlobalPlayerListener.getInstance().removeListener(playerListener);
			
			entityListener = null;
			blockListener = null;
			playerListener = null;
		}
		
		
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public void setState(BuilderState state) {
		this.state = state;
	}

	public BuilderState getState() {
		return state;
	}
}
