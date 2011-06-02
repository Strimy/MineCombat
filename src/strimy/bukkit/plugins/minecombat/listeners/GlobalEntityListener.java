package strimy.bukkit.plugins.minecombat.listeners;

import java.util.ArrayList;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;

public class GlobalEntityListener extends EntityListener 
{
	private static GlobalEntityListener instance = null;
	private ArrayList<EntityListener> modeListeners = new ArrayList<EntityListener>();
	
	public void addListener(EntityListener listener)
	{
		if(!modeListeners.contains(listener))
			modeListeners.add(listener);
		
		System.out.print("Added listener : " + listener.toString());
	}
	
	public void removeListener(EntityListener listener)
	{
		if(modeListeners.contains(listener))
			modeListeners.remove(listener);
		
		System.out.print("Removed listener : " + listener.toString());
	}
	
	public static GlobalEntityListener getInstance()
	{
		if(instance == null)
			instance = new GlobalEntityListener();
		
		return instance;
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		for (EntityListener listener : modeListeners) 
		{
			listener.onEntityDamage(event);
		}
		super.onEntityDamage(event);
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		for (EntityListener listener : modeListeners) 
		{
			listener.onEntityDeath(event);
		}
		super.onEntityDeath(event);
	}

	@Override
	public void onEntityInteract(EntityInteractEvent event) {
		for (EntityListener listener : modeListeners) 
		{
			listener.onEntityInteract(event);
		}
		super.onEntityInteract(event);
	}

	@Override
	public void onEntityTarget(EntityTargetEvent event) {
		for (EntityListener listener : modeListeners) 
		{
			listener.onEntityTarget(event);
		}
		super.onEntityTarget(event);
	}
}
