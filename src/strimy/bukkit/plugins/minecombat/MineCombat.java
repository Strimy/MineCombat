package strimy.bukkit.plugins.minecombat;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import strimy.bukkit.plugins.minecombat.tdm.TeamDeathMatchMode;

public class MineCombat extends JavaPlugin 
{
	public static MineCombat Instance = null;
	
	ArrayList<Player> playerInCombat = new ArrayList<Player>();
	
	ArrayList<JavaPlugin> listCombat = new ArrayList<JavaPlugin>();

	@Override
	public void onDisable() 
	{
		Instance = null;
	}

	@Override
	public void onEnable() 
	{
		Instance = this;
		
		getCommand("combat").setExecutor(new MCCommandExecutor(this));
	}
	
	public void addTeamDeathMatch()
	{
		JavaPlugin p = new TeamDeathMatchMode(getPluginLoader(), getServer(), getDescription(), getFile(), getFile(), getClassLoader());
		listCombat.add(p);
		
		getPluginLoader().enablePlugin(p);
	}

}
