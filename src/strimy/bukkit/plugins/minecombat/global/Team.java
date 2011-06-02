package strimy.bukkit.plugins.minecombat.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Team 
{
	private String name;
	private TeamColor color;
	private List<Player> players;
	private HashMap<Player, CombatInventory> inventoryBackup = new HashMap<Player, CombatInventory>();
	
	private CombatInventory teamInventory = new CombatInventory();
	
	public Team()
	{
		players = new ArrayList<Player>();
	}
	
	public CombatInventory getTeamInventory()
	{
		return teamInventory;
	}

	public void setColor(TeamColor color) 
	{
		this.color = color;
		updateTeamColorInventory();
	}

	public TeamColor getColor() {
		return color;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	
	public void applyInventory()
	{
		for (Player p : players) 
		{
			backupInventory(p, false);
			
			teamInventory.applyPlayerInventory(p);
		}
	}

	public void setInventoryBackup(HashMap<Player, CombatInventory> inventoryBackup) {
		this.inventoryBackup = inventoryBackup;
	}

	public HashMap<Player, CombatInventory> getInventoryBackup() {
		return inventoryBackup;
	}
	
	private void updateTeamColorInventory()
	{
		if(color != null)
		{
			teamInventory.setBoots(new ItemStack(Material.valueOf(color.toString()+"_BOOTS"), 1));
			teamInventory.setChestplate(new ItemStack(Material.valueOf(color.toString()+"_CHESTPLATE")));
			teamInventory.setHelmet(new ItemStack(Material.valueOf(color.toString()+"_HELMET")));
			teamInventory.setLeggings(new ItemStack(Material.valueOf(color.toString()+"_LEGGINGS")));
			
			teamInventory.getItems()[0] = new ItemStack(Material.valueOf(color.toString()+"_SWORD"), 1);
		}
		
	}
	
	public void removePlayer(Player p)
	{
		if(inventoryBackup.containsKey(p))
		{
			inventoryBackup.get(p).applyPlayerInventory(p);
			inventoryBackup.remove(p);
		}
	}
	
	public void joinPlayer(Player p)
	{
		backupInventory(p, false);
		
		if(!players.contains(p))
			players.add(p);
		
		ChatColor c = ChatColor.GOLD;
		if(getColor() == TeamColor.DIAMOND)
		{
			c = ChatColor.AQUA;
		}
		else if(getColor() == TeamColor.IRON)
		{
			c = ChatColor.GRAY;
		}
		else if(getColor() == TeamColor.LEATHER)
		{
			c = ChatColor.RED;
		}
		
		p.sendMessage(c + "You have joined the "+getColor().toString() + " team.");
	}
	
	private void backupInventory(Player p, boolean force)
	{
		if(force && inventoryBackup.containsKey(p)) 
			inventoryBackup.remove(p);
		
		if(!inventoryBackup.containsKey(p))
		{
			CombatInventory ci = CombatInventory.fromPlayer(p);
			ci.setBackup(true);
			inventoryBackup.put(p, ci);
		}

	}
	
	public void clear()
	{
		for (Player p : players) 
		{
			removePlayer(p);
		}
		players.clear();
	}
}
