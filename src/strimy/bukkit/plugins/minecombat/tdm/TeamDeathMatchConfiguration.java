package strimy.bukkit.plugins.minecombat.tdm;

import strimy.bukkit.plugins.minecombat.global.Map;

public class TeamDeathMatchConfiguration 
{
	private Map map;
	private String instanceName;
	private int maxTeamCount = 2;
	private boolean autoTeamSelection = true;
	private boolean friendlyFire;
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public int getMaxTeamCount() 
	{
		return maxTeamCount;
	}
	public void setMaxTeamCount(int maxTeamCount) 
	{
		this.maxTeamCount = maxTeamCount;
	}
	public boolean isAutoTeamSelection() {
		return autoTeamSelection;
	}
	public void setAutoTeamSelection(boolean autoTeamSelection) {
		this.autoTeamSelection = autoTeamSelection;
	}
	public boolean isFriendlyFire() {
		return friendlyFire;
	}
	public void setFriendlyFire(boolean friendlyFire) {
		this.friendlyFire = friendlyFire;
	}
}
