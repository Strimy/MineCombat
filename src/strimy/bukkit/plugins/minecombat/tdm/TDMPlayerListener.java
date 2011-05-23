package strimy.bukkit.plugins.minecombat.tdm;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class TDMPlayerListener extends PlayerListener 
{
	TeamDeathMatchMode plugin;
	
	public TDMPlayerListener(TeamDeathMatchMode tdm) {
		// TODO Auto-generated constructor stub
		plugin = tdm;
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) 
	{
		
		String message = event.getMessage();
		
		if(message.startsWith("/tdm start"))
		{
			plugin.start();
			event.setCancelled(true);
		}
		if(message.startsWith("/tdm stop"))
		{
			plugin.start();
			event.setCancelled(true);
		}
		// TODO Auto-generated method stub
		super.onPlayerCommandPreprocess(event);
		
	}

}
