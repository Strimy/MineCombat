package strimy.bukkit.plugins.minecombat.tdm;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;

public class TDMEntityListener extends EntityListener 
{
	TeamDeathMatchMode plugin;

	public TDMEntityListener(TeamDeathMatchMode teamDeathMatchMode) 
	{
		this.plugin = teamDeathMatchMode;
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) 
	{
		if(event.isCancelled())
			return;
		
		if(event instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent)event;
			Player damager = null;
			Player damaged = null;
			
			if(event.getEntity() instanceof Player)
			{				
				damaged = (Player)event.getEntity();
			}
			if(entityEvent.getDamager() instanceof Player)
			{
				damager = (Player)entityEvent.getDamager();
			}

			if(damager == null && damaged != null)
			{
				// The damager should be a mob
				if(plugin.isPlayerInTDM(damaged))
				{
					event.setDamage(0);
					event.setCancelled(true);
				}
			}
			else if(damager != null && damaged == null)
			{
				// A mob has been damaged
				return;
			}
			else if(damager != null && damaged != null)
			{
				if(!plugin.isPlayerInTDM(damager) && !plugin.isPlayerInTDM(damaged))
				{
					// If this is between player that are not in this TDM
					// I don't care
					return;
				}

				if(plugin.isPlayerInTDM(damager) ^ plugin.isPlayerInTDM(damaged))
				{
					if(plugin.isPlayerInTDM(damager))
					{
						damager.sendMessage("You can't attack people that are not in this TDM instance");
					}
					else
					{
						damager.sendMessage("You can't attack people that are in a TDM instance");
					}
					event.setDamage(0);
					event.setCancelled(true);
					return;
				}
				
				if(plugin.getState() != TDMState.InGame)
				{
					damager.sendMessage(ChatColor.YELLOW + "TDM hasn't started...");

					event.setDamage(0);
					event.setCancelled(true);
					return;
				}
				
				boolean sameTeam = (plugin.getTeamFromPlayer(damaged) == plugin.getTeamFromPlayer(damager));
				if(sameTeam && !plugin.getConfig().isFriendlyFire())
				{
					event.setCancelled(true);
					event.setDamage(0);
				}
				else
				{
					event.setDamage(0);
					//damaged.damage(5);
							
				}
			}
			
		}
		
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event) 
	{
		super.onEntityDeath(event);
	}

	@Override
	public void onEntityTarget(EntityTargetEvent event) {
		// TODO Auto-generated method stub
		super.onEntityTarget(event);
	}

	@Override
	public void onEntityInteract(EntityInteractEvent event) 
	{
		super.onEntityInteract(event);
	}

}
