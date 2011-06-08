package strimy.bukkit.plugins.minecombat.builder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import strimy.bukkit.plugins.minecombat.global.CombatZone;
import strimy.bukkit.plugins.minecombat.global.Map;
import strimy.bukkit.plugins.minecombat.global.Spawn;

public class BuilderPlayerListener extends PlayerListener 
{

	@Override
	public void onPlayerMove(PlayerMoveEvent event) 
	{
		BuilderMode bm = BuilderMode.getBuilderModeFromPlayer(event.getPlayer());
		
		if(bm != null)
		{
			if(bm.getState() == BuilderState.SpawnPoints)
			{
				if(!bm.getMap().getZone().isInCombatZone(event.getPlayer().getLocation()))
				{
					event.getPlayer().sendMessage("You are out of the CombatZone");
				}
			}
		}
		
		super.onPlayerMove(event);
		
	}

	@Override
	public void onItemHeldChange(PlayerItemHeldEvent event) 
	{
		// TODO Auto-generated method stub
		super.onItemHeldChange(event);
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) 
	{
		BuilderMode.disable(event.getPlayer());
		super.onPlayerKick(event);
	}

	@Override
	public void onPlayerPickupItem(PlayerPickupItemEvent event) 
	{
		if(BuilderMode.isBuilder(event.getPlayer()))
		{
			event.setCancelled(true);
		}
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) 
	{
		BuilderMode bm = BuilderMode.getBuilderModeFromPlayer(event.getPlayer());
		if(bm != null)
		{
			String message = event.getMessage();
			if(message.startsWith("/build"))
			{
				String[] args = message.replace("/build ", "").split(" ");
				if(args.length > 0)
				{
					if(args[0].equals("map"))
					{
						if(args[1].equals("save"))
						{
							event.getPlayer().sendMessage("Saving map");
							bm.getMap().save();
						}
						else if(args.length != 3)
						{
							event.getPlayer().sendMessage("Missing map informations");
							return;
						}
						
						if(args[1].equals("create"))
						{
							Map map = new Map();
							map.setName(args[2]);
							event.getPlayer().sendMessage("You are now creating the "+args[2]+" map.");
							event.getPlayer().sendMessage("You have to defines the combat zone first. Hit a block to define a point.");
							event.getPlayer().sendMessage("Once it's done, type /build state spawn");
							bm.setMap(map);
							bm.setState(BuilderState.CombatZone);
						}
						else if(args[1].equals("load"))
						{
							Map selectedMap = null;
							for (Map map : Map.getMaps()) {
								if(map.getName().equals(args[2]))
								{
									selectedMap = map;
									break;
								}
							}
							if(selectedMap != null)
							{
								bm.setMap(selectedMap);
								event.getPlayer().sendMessage("Map "+selectedMap.getName()+" loaded.");
							}
							else
							{
								event.getPlayer().sendMessage("Can't find the map "+args[2]+"");
							}
						}

					}
					else if(args[0].equals("state"))
					{
						if(args[1].equals("spawn"))
						{
							bm.setState(BuilderState.SpawnPoints);
						}
					}
					else if(args[0].equals("exit"))
					{
						BuilderMode.disable(event.getPlayer());
					}	
				}
			}
		}
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent event) 
	{
		BuilderMode bm = BuilderMode.getBuilderModeFromPlayer(event.getPlayer());
		if(bm != null)
		{
			if(bm.getMap() != null)
			{
				Material usedItem = event.getPlayer().getItemInHand().getType();
				System.out.print(usedItem);
				if(usedItem == Material.DIAMOND_SWORD)
				{
					if(bm.getMap().getZone() == null)
					{
						bm.getMap().setZone(new CombatZone());
					}
					bm.getMap().getZone().addPoint(event.getClickedBlock().getLocation());
					event.getPlayer().sendMessage("Added point : " + event.getClickedBlock().getLocation().toString());
				}
				else if(usedItem == Material.STONE_PLATE)
				{
					Spawn s = new Spawn();
					s.setSpawnPoint(event.getClickedBlock().getLocation());
					bm.getMap().addSpawn(s);
					event.getPlayer().sendMessage(ChatColor.GOLD + "Added Spawn Point");
					event.setCancelled(true);
				}
			}
		}
		
		super.onPlayerInteract(event);
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		BuilderMode.disable(event.getPlayer());
		super.onPlayerQuit(event);
	}

}
