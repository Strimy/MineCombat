package strimy.bukkit.plugins.minecombat.interfaces;

import org.bukkit.entity.Player;

public interface IMode 
{
	public int getPlayerCount();
	public Player[] getPlayers();
	public String getInstanceName();
	public void joinPlayer(Player p);
	public void removePlayer(Player p);
	public boolean isEnabled();
	
	public String getModeName();
}
