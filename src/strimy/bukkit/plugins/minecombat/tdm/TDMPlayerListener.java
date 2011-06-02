package strimy.bukkit.plugins.minecombat.tdm;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TDMPlayerListener extends PlayerListener 
{
	TeamDeathMatchMode plugin;
	
	public TDMPlayerListener(TeamDeathMatchMode tdm) {
		// TODO Auto-generated constructor stub
		plugin = tdm;
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		// TODO Auto-generated method stub
		super.onPlayerQuit(event);
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		// TODO Auto-generated method stub
		super.onPlayerKick(event);
	}

	@Override
	public void onPlayerLogin(PlayerLoginEvent event) {
		// TODO Auto-generated method stub
		super.onPlayerLogin(event);
	}

	@Override
	public void onPlayerDropItem(PlayerDropItemEvent event) 
	{
		super.onPlayerDropItem(event);
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) 
	{
		String message = event.getMessage();
		if(message.startsWith("/tdm"))
		{
			event.setCancelled(true);
			String[] args = message.replace("/tdm ", "").split(" ");
			if(args[0].equals("enable"))
			{
				if(hasRights(event.getPlayer()))
					plugin.start();
			}
			else if(args[0].equals("start"))
			{
				if(hasRights(event.getPlayer()))
					plugin.start();
			}
			else if(args[0].equals("stop"))
			{
				if(hasRights(event.getPlayer()))
					plugin.stop();
			}
		}
		// TODO Auto-generated method stub
		super.onPlayerCommandPreprocess(event);
		
	}

	private boolean hasRights(Player p)
	{
		if(p == plugin.getInstanceAdmin())
			return true;
		
		p.sendMessage(ChatColor.RED + "You can't do this action");
		return false;
	}
}
