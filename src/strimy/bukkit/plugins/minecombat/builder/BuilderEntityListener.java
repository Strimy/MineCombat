package strimy.bukkit.plugins.minecombat.builder;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class BuilderEntityListener extends EntityListener 
{

	@Override
	public void onEntityDamage(EntityDamageEvent event) 
	{
		if(event.getEntity() instanceof Player && BuilderMode.isBuilder((Player)event.getEntity()))
			event.setCancelled(true);
		
	}

}
