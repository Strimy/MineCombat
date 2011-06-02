package strimy.bukkit.plugins.minecombat.global;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CombatInventory
{
	private ItemStack boots = null;
	private ItemStack chestplate = null;
	private ItemStack helmet = null;
	private ItemStack leggings = null;
	private boolean isBackup = false;
	
	
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
		boots = correctItemStack(arg0);
	}

	public void setChestplate(ItemStack arg0) 
	{
		chestplate = correctItemStack(arg0);

	}

	public void setHelmet(ItemStack arg0) {
		helmet = correctItemStack(arg0);

	}


	public void setLeggings(ItemStack arg0) {
		leggings = correctItemStack(arg0);

	}

	
	public void setBackup(boolean isBackup) {
		this.isBackup = isBackup;
	}

	public boolean isBackup() {
		return isBackup;
	}

	public void applyPlayerInventory(Player p)
	{
		PlayerInventory pi = p.getInventory();
		pi.clear();
		
		pi.setContents(this.getItems());
		
		if(!isBackup())
		{
			pi.setBoots(new ItemStack(this.getBoots().getType(), 1));
			pi.setChestplate(new ItemStack(this.getChestplate().getType(), 1));
			pi.setHelmet(new ItemStack(this.getHelmet().getType(), 1));
			pi.setLeggings(new ItemStack(this.getLeggings().getType(), 1));
		}
		else
		{
			System.out.print("Using Backup inventory");
			pi.setBoots(this.getBoots());
			pi.setChestplate(this.getChestplate());
			pi.setHelmet(this.getHelmet());
			pi.setLeggings(this.getLeggings());
		}
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
	
	private ItemStack correctItemStack(ItemStack stack)
	{
		if(stack.getType() == Material.AIR)
			return null;
		
		return stack;
	}
}
