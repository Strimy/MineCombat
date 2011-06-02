package strimy.bukkit.plugins.minecombat.tdm;

import java.lang.reflect.Type;
import java.util.HashMap;

import strimy.bukkit.plugins.minecombat.global.CombatInventory;
import strimy.bukkit.plugins.minecombat.global.CombatZone;
import strimy.bukkit.plugins.minecombat.global.Map;
import strimy.bukkit.plugins.minecombat.global.ReadyRoom;
import strimy.bukkit.plugins.minecombat.global.Spawn;
import strimy.bukkit.plugins.minecombat.global.Team;

public class TDMMap extends Map 
{
	private Map baseMap;
	
	HashMap<Team, Spawn> teamSpawns = new HashMap<Team, Spawn>();

	@Override
	public CombatZone getZone() 
	{
		if(baseMap == null)
			return null;

		return baseMap.getZone();
	}

	@Override
	public void setZone(CombatZone zone) 
	{
		baseMap.setZone(zone);
	}

	@Override
	public CombatInventory getDefaultInventorySet() {
		if(baseMap == null)
			return null;
		return baseMap.getDefaultInventorySet();
	}

	@Override
	public void setDefaultInventorySet(CombatInventory defaultInventorySet) {
		// TODO Auto-generated method stub
		baseMap.setDefaultInventorySet(defaultInventorySet);
	}

	@Override
	public Type[] getSupportedModes() {
		if(baseMap == null)
			return null;
		return baseMap.getSupportedModes();
	}

	@Override
	public void setSupportedModes(Type[] supportedModes) {
		// TODO Auto-generated method stub
		baseMap.setSupportedModes(supportedModes);
	}

	@Override
	public ReadyRoom getReadyRoom() {
		if(baseMap == null)
			return null;
		return baseMap.getReadyRoom();
	}

	@Override
	public void setReadyRoom(ReadyRoom readyRoom) {
		// TODO Auto-generated method stub
		baseMap.setReadyRoom(readyRoom);
	}

	
}
