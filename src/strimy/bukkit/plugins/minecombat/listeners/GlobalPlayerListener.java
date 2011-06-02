package strimy.bukkit.plugins.minecombat.listeners;

import java.util.ArrayList;

import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class GlobalPlayerListener extends PlayerListener 
{
	private static GlobalPlayerListener instance = null;
	private ArrayList<PlayerListener> modeListeners = new ArrayList<PlayerListener>();
	
	public void addListener(PlayerListener listener)
	{
		if(!modeListeners.contains(listener))
			modeListeners.add(listener);
	}
	
	public void removeListener(PlayerListener listener)
	{
		if(modeListeners.contains(listener))
			modeListeners.remove(listener);
	}
	
	public static GlobalPlayerListener getInstance()
	{
		if(instance == null)
			instance = new GlobalPlayerListener();
		
		return instance;
	}

	@Override
	public void onInventoryOpen(PlayerInventoryEvent event) 
	{
		for (PlayerListener listener : modeListeners) 
		{
			listener.onInventoryOpen(event);
		}

		super.onInventoryOpen(event);
	}

	@Override
	public void onItemHeldChange(PlayerItemHeldEvent event) 
	{
		for (PlayerListener listener : modeListeners) 
		{
			listener.onItemHeldChange(event);
		}
		super.onItemHeldChange(event);
	}

	@Override
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerAnimation(event);
		}
		super.onPlayerAnimation(event);
	}

	@Override
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerBedEnter(event);
		}
		super.onPlayerBedEnter(event);
	}

	@Override
	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerBedLeave(event);
		}
		super.onPlayerBedLeave(event);
	}

	@Override
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerBucketEmpty(event);
		}
		super.onPlayerBucketEmpty(event);
	}

	@Override
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerBucketFill(event);
		}
		super.onPlayerBucketFill(event);
	}

	@Override
	public void onPlayerChat(PlayerChatEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerChat(event);
		}
		super.onPlayerChat(event);
	}

	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerCommandPreprocess(event);
		}
	}

	@Override
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerDropItem(event);
		}
		super.onPlayerDropItem(event);
	}

	@Override
	public void onPlayerEggThrow(PlayerEggThrowEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerEggThrow(event);
		}
		super.onPlayerEggThrow(event);
	}

	@Override
	public void onPlayerInteract(PlayerInteractEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerInteract(event);
		}
		super.onPlayerInteract(event);
	}

	@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerInteractEntity(event);
		}
		super.onPlayerInteractEntity(event);
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerJoin(event);
		}
		super.onPlayerJoin(event);
	}

	@Override
	public void onPlayerKick(PlayerKickEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerKick(event);
		}
		super.onPlayerKick(event);
	}

	@Override
	public void onPlayerLogin(PlayerLoginEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerLogin(event);
		}
		super.onPlayerLogin(event);
	}

	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerMove(event);
		}
		super.onPlayerMove(event);
	}

	@Override
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerPickupItem(event);
		}
		super.onPlayerPickupItem(event);
	}

	@Override
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerPreLogin(event);
		}
		super.onPlayerPreLogin(event);
	}

	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerQuit(event);
		}
		super.onPlayerQuit(event);
	}

	@Override
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerRespawn(event);
		}
		super.onPlayerRespawn(event);
	}

	@Override
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerTeleport(event);
		}
		super.onPlayerTeleport(event);
	}

	@Override
	public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		for (PlayerListener listener : modeListeners) 
		{
			listener.onPlayerToggleSneak(event);
		}
		super.onPlayerToggleSneak(event);
	}

}
