package strimy.bukkit.plugins.global;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ReadyRoom
{
	private ArrayList<Player> unlockedFor = new ArrayList<Player>();
	
	private Location spawnLocation;

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

	public Location getSpawnLocation() {
		return spawnLocation;
	}

	public void setUnlockedFor(ArrayList<Player> unlockedFor) {
		this.unlockedFor = unlockedFor;
	}

	public ArrayList<Player> getUnlockedFor() {
		return unlockedFor;
	}
}
