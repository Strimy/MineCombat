package strimy.bukkit.plugins.minecombat.global;

import java.lang.reflect.Type;


public class Map 
{
	private CombatZone zone;
	private CombatInventory defaultInventorySet;
	private Type[] supportedModes;
	private ReadyRoom readyRoom;
	
	
	public CombatZone getZone() {
		return zone;
	}
	public void setZone(CombatZone zone) {
		this.zone = zone;
	}
	public CombatInventory getDefaultInventorySet() {
		return defaultInventorySet;
	}
	public void setDefaultInventorySet(CombatInventory defaultInventorySet) {
		this.defaultInventorySet = defaultInventorySet;
	}
	public Type[] getSupportedModes() {
		return supportedModes;
	}
	public void setSupportedModes(Type[] supportedModes) {
		this.supportedModes = supportedModes;
	}
	public ReadyRoom getReadyRoom() {
		return readyRoom;
	}
	public void setReadyRoom(ReadyRoom readyRoom) {
		this.readyRoom = readyRoom;
	}
}
