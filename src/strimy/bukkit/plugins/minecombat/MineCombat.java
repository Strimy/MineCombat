package strimy.bukkit.plugins.minecombat;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import strimy.bukkit.plugins.minecombat.interfaces.IMode;
import strimy.bukkit.plugins.minecombat.listeners.GlobalEntityListener;
import strimy.bukkit.plugins.minecombat.listeners.GlobalPlayerListener;
import strimy.bukkit.plugins.minecombat.tdm.TeamDeathMatchMode;

public class MineCombat extends JavaPlugin 
{
	public static MineCombat Instance = null;
	
	ArrayList<Player> playerInCombat = new ArrayList<Player>();
	
	ArrayList<IMode> listCombat = new ArrayList<IMode>();
	
	
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
		
		PluginManager pm = getServer().getPluginManager();
		ArrayList<Type> disabledTypes = new ArrayList<Type>();
		disabledTypes.add(Type.PLAYER_INVENTORY);
		
		for (Type type : Type.values()) 
		{
			if(type.toString().startsWith("PLAYER_") && !disabledTypes.contains(type))
			{
				pm.registerEvent(type, GlobalPlayerListener.getInstance(), Priority.Normal, this);
			}
			else if(type.toString().startsWith("ENTITY_") && !disabledTypes.contains(type))
			{
				pm.registerEvent(type, GlobalEntityListener.getInstance(), Priority.Normal, this);
			}
		}
	}
	
	public void addTeamDeathMatch(CommandSender admin)
	{
		TeamDeathMatchMode p = new TeamDeathMatchMode(getPluginLoader(), getServer(), getDescription(), getFile(), getFile(), getClassLoader());
		p.setInstanceAdmin(admin);
		listCombat.add(p);
		
		getServer().getPluginManager().enablePlugin(p);
		
	}

	public void disableModePlugin(JavaPlugin plugin)
	{
		getServer().getPluginManager().disablePlugin(plugin);
		listCombat.remove(plugin);
	}
	

}
