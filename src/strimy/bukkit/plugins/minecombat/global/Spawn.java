package strimy.bukkit.plugins.minecombat.global;

import org.bukkit.Location;

public class Spawn 
{
	private String teamName;
	private Location spawnPoint;
	
	public Spawn(String name) 
	{
		setTeamName(name);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Location getSpawnPoint() {
		return spawnPoint;
	}

	public void setSpawnPoint(Location spawnPoint) {
		this.spawnPoint = spawnPoint;
	}
}
