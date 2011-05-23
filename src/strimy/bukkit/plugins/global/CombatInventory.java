package strimy.bukkit.plugins.global;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CombatInventory
{
	private ItemStack boots = null;
	private ItemStack chestplate = null;
	private ItemStack helmet = null;
	private ItemStack leggings = null;
	
	
	private ItemStack[] items = new ItemStack[36];

	public ItemStack[] getItems() 
	{
		return items;
	}
	
	public void setItems(ItemStack[] i) 
	{
		items = i;
	}
	
	public ItemStack getBoots() 
	{
		return boots;
	}

	public ItemStack getChestplate() 
	{
		return chestplate;
	}

	public int getHeldItemSlot() 
	{
		return 0;
	}

	public ItemStack getHelmet() 
	{
		return helmet;
	}

	public ItemStack getLeggings() 
	{
		return leggings;
	}

	public void setBoots(ItemStack arg0) 
	{
		boots = arg0;
	}

	public void setChestplate(ItemStack arg0) 
	{
		chestplate = arg0;

	}

	public void setHelmet(ItemStack arg0) {
		helmet = arg0;

	}


	public void setLeggings(ItemStack arg0) {
		leggings = arg0;

	}

	
	public void applyPlayerInventory(Player p)
	{
		PlayerInventory pi = p.getInventory();
		pi.clear();
		
		pi.setContents(this.getItems());
		pi.setBoots(this.getBoots());
		pi.setChestplate(this.getChestplate());
		pi.setHelmet(this.getHelmet());
		pi.setLeggings(this.getLeggings());
	}
	
	public static CombatInventory fromPlayer(Player p)
	{
		CombatInventory ci = new CombatInventory();
		PlayerInventory inventory = p.getInventory();
		
		ci.setBoots(inventory.getBoots());
		ci.setChestplate(inventory.getChestplate());
		ci.setHelmet(inventory.getHelmet());
		ci.setLeggings(inventory.getLeggings());
		ci.setItems(inventory.getContents());	
		
		return ci;
	}
}
