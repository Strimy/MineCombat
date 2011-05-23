package strimy.bukkit.plugins.minecombat.tdm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import strimy.bukkit.plugins.global.CombatZone;
import strimy.bukkit.plugins.global.Team;
import strimy.bukkit.plugins.global.TeamColor;

public class TeamDeathMatchMode extends JavaPlugin 
{
	public static TeamDeathMatchMode Instance = null;
	
	private CombatZone zone;
	private Team[] teams;
	private ArrayList<Player> listPlayers = new ArrayList<Player>();
	private HashMap<Player, Team> player2Team = new HashMap<Player, Team>();
	
	public TeamDeathMatchMode(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader)
	{
		initialize(pluginLoader, instance, desc, folder, plugin, cLoader);
	}

	@Override
	public void onDisable() 
	{
		Instance = null;
	}

	@Override
	public void onEnable() 
	{
		Instance = this;
		getServer().broadcastMessage("Entering TDM Mode");
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new TDMPlayerListener(this), Priority.Normal, this);
	
		joinPlayer(getServer().getOnlinePlayers()[0]);	
	}
	
	public void removePlayer(Player p)
	{
		if(player2Team.containsKey(p))
		{
			player2Team.get(p).removePlayer(p);
		}
	}
	
	public void addTeamPlayer(Player p, Team t)
	{
		if(!player2Team.containsKey(p))
		{
			player2Team.put(p, t);
			t.joinPlayer(p);
		}
	}

	public void joinPlayer(Player p)
	{
		p.sendMessage("You have joined an instance of Team DeathMatch");
		p.sendMessage("You are now in the ready room");
		listPlayers.add(p);
	}
	
	public void createAutoTeam(int teamCount)
	{
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
		}
		
		for (int i = 0; i < listPlayers.size(); i++) 
		{
			addTeamPlayer(listPlayers.get(i), teams[i % teamCount]);
		}
	}

	public void setZone(CombatZone zone) {
		this.zone = zone;
	}

	public CombatZone getZone() {
		return zone;
	}
	
	public void start()
	{
		createAutoTeam(2);
		for (int i = 0; i < teams.length; i++) {
			teams[i].applyInventory();
		}
	}
	
	public void stop()
	{
		for (int i = 0; i < teams.length; i++) {
			teams[i].clear();
		}
	}
}
