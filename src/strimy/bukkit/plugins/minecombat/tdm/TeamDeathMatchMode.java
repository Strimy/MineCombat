package strimy.bukkit.plugins.minecombat.tdm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import strimy.bukkit.plugins.minecombat.MineCombat;
import strimy.bukkit.plugins.minecombat.global.Map;
import strimy.bukkit.plugins.minecombat.global.Team;
import strimy.bukkit.plugins.minecombat.global.TeamColor;
import strimy.bukkit.plugins.minecombat.interfaces.IMode;
import strimy.bukkit.plugins.minecombat.listeners.GlobalEntityListener;
import strimy.bukkit.plugins.minecombat.listeners.GlobalPlayerListener;

public class TeamDeathMatchMode extends JavaPlugin implements IMode
{
	private static final String NAME = "Team Deathmatch";
	private TDMState state;
	private Team[] teams;
	private ArrayList<Player> listPlayers = new ArrayList<Player>();
	private HashMap<Player, Team> player2Team = new HashMap<Player, Team>();
	private TeamDeathMatchConfiguration config;
	private CommandSender instanceAdmin;
	private Map currentMap;
	
	private PlayerListener pl;
	private EntityListener el;
	
	public TeamDeathMatchMode(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader)
	{
		initialize(pluginLoader, instance, desc, folder, plugin, cLoader);
	}
	
	@Override
	public String getModeName() 
	{
		return NAME;
	}

	@Override
	public void onDisable() 
	{
		getServer().broadcastMessage("Stopping TDM Mode");
		removeListeners();
	}

	@Override
	public void onEnable() 
	{
		setState(TDMState.Stopped);
		if(getInstanceAdmin() != null)
		{
			getInstanceAdmin().sendMessage(ChatColor.LIGHT_PURPLE + "You have created a new TeamDeathmatch instance.");
			getInstanceAdmin().sendMessage(ChatColor.LIGHT_PURPLE + "Use the /tdm command to configure the instance before players join.");
			
			if(getInstanceAdmin() instanceof Player)
			{
				joinPlayer((Player)getInstanceAdmin());
			}
		}
		
		
		setConfig(new TeamDeathMatchConfiguration());
		
		
		addListeners();
	}
	
	private void addListeners()
	{
		if(pl != null)
		{
			GlobalPlayerListener.getInstance().removeListener(pl);
		}
		pl = new TDMPlayerListener(this);
		GlobalPlayerListener.getInstance().addListener(pl);
		
		if(el != null)
		{
			GlobalEntityListener.getInstance().removeListener(el);
		}
		el = new TDMEntityListener(this);
		GlobalEntityListener.getInstance().addListener(el);
	}
	
	private void removeListeners()
	{
		if(pl != null)
		{
			GlobalPlayerListener.getInstance().removeListener(pl);
			pl = null;
		}
		
		if(el != null)
		{
			GlobalEntityListener.getInstance().removeListener(el);
			el = null;
		}
	}
	
	public void removePlayer(Player p)
	{
		if(player2Team.containsKey(p))
		{
			player2Team.get(p).removePlayer(p);
		}
		if(listPlayers.contains(p))
			listPlayers.remove(p);
	}
	
	public void addTeamPlayer(Player p, Team t)
	{
		if(!player2Team.containsKey(p))
		{
			player2Team.put(p, t);
			t.joinPlayer(p);
			System.out.println("Player : " + p.getDisplayName() + " / Team : " + t.getColor().toString());
		}
	}

	public void joinPlayer(Player p)
	{
		p.sendMessage(ChatColor.GOLD + "You have joined an instance of Team DeathMatch");
		listPlayers.add(p);
	}
	
	public void createAutoTeam(int teamCount)
	{
		System.out.println("Creating teams...");
		if(teams != null)
		{
			for (int i = 0; i < teams.length; i++) 
			{
				teams[i].clear();
			}
		}
		
		teams = new Team[teamCount];
		for (int i = 0; i < teamCount; i++) 
		{
			teams[i] = new Team();
			teams[i].setColor(TeamColor.values()[i]);
			System.out.println("New team : " + teams[i].getColor().toString());
		}
		
		for (int i = 0; i < listPlayers.size(); i++) 
		{
			addTeamPlayer(listPlayers.get(i), teams[i % teamCount]);
		}
	}

	public void start()
	{
		createAutoTeam(getConfig().getMaxTeamCount());
		
		for (int i = 0; i < teams.length; i++) 
		{
			teams[i].applyInventory();
		}
		setState(TDMState.InGame);
	}
	
	public void stop()
	{
		if(teams != null)
		{
			for (int i = 0; i < teams.length; i++) 
			{
				teams[i].clear();
			}
		}
		
		MineCombat.Instance.disableModePlugin(this);
	}
	
	public Team getTeamFromPlayer(Player p)
	{
		if(player2Team.containsKey(p))
			return player2Team.get(p);
		
		return null;
	}
	
	public boolean isPlayerInTDM(Player p)
	{
		return listPlayers.contains(p);
	}

	public void setConfig(TeamDeathMatchConfiguration config) {
		this.config = config;
	}

	public TeamDeathMatchConfiguration getConfig() {
		return config;
	}

	public void setInstanceAdmin(CommandSender admin) {
		this.instanceAdmin = admin;
	}

	public CommandSender getInstanceAdmin() {
		return instanceAdmin;
	}

	@Override
	public int getPlayerCount() 
	{
		if(listPlayers == null)
			return 0;
		
		return listPlayers.size();
	}

	@Override
	public Player[] getPlayers() 
	{
		return (Player[])listPlayers.toArray();
	}

	@Override
	public String getInstanceName() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setState(TDMState state) {
		this.state = state;
	}

	public TDMState getState() {
		return state;
	}

	public void setCurrentMap(Map currentMap) 
	{
		for (Player p : listPlayers) 
		{
			p.sendMessage(ChatColor.DARK_AQUA + "Map has changed to "+ currentMap);
			p.teleport((currentMap.getSpawns().get(0).getSpawnPoint()));
		}
		this.currentMap = currentMap;
		
		
	}

	public Map getCurrentMap() {
		return currentMap;
	}
}
