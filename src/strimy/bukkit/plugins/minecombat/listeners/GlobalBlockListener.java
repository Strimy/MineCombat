package strimy.bukkit.plugins.minecombat.listeners;

import java.util.ArrayList;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.SnowFormEvent;

public class GlobalBlockListener extends BlockListener 
{
	private static GlobalBlockListener instance = null;
	private ArrayList<BlockListener> modeListeners = new ArrayList<BlockListener>();
	
	public void addListener(BlockListener listener)
	{
		if(!modeListeners.contains(listener))
			modeListeners.add(listener);
		
		System.out.print("Added listener : " + listener.toString());
	}
	
	public void removeListener(BlockListener listener)
	{
		if(modeListeners.contains(listener))
			modeListeners.remove(listener);
		
		System.out.print("Removed listener : " + listener.toString());
	}
	
	public static GlobalBlockListener getInstance()
	{
		if(instance == null)
			instance = new GlobalBlockListener();
		
		return instance;
	}
	

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockBreak(event);
		}
		super.onBlockBreak(event);
	}

	@Override
	public void onBlockBurn(BlockBurnEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockBurn(event);
		}
		super.onBlockBurn(event);
	}

	@Override
	public void onBlockCanBuild(BlockCanBuildEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockCanBuild(event);
		}
		super.onBlockCanBuild(event);
	}

	@Override
	public void onBlockDamage(BlockDamageEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockDamage(event);
		}
		super.onBlockDamage(event);
	}

	@Override
	public void onBlockDispense(BlockDispenseEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockDispense(event);
		}
		super.onBlockDispense(event);
	}

	@Override
	public void onBlockFromTo(BlockFromToEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockFromTo(event);
		}
		super.onBlockFromTo(event);
	}

	@Override
	public void onBlockIgnite(BlockIgniteEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockIgnite(event);
		}
		super.onBlockIgnite(event);
	}

	@Override
	public void onBlockPhysics(BlockPhysicsEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockPhysics(event);
		}
		super.onBlockPhysics(event);
	}

	@Override
	public void onBlockPlace(BlockPlaceEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockPlace(event);
		}
		super.onBlockPlace(event);
	}

	@Override
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onBlockRedstoneChange(event);
		}
		super.onBlockRedstoneChange(event);
	}

	@Override
	public void onLeavesDecay(LeavesDecayEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onLeavesDecay(event);
		}
		super.onLeavesDecay(event);
	}

	@Override
	public void onSignChange(SignChangeEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onSignChange(event);
		}
		super.onSignChange(event);
	}

	@Override
	public void onSnowForm(SnowFormEvent event) {
		for (BlockListener listener : modeListeners) 
		{
			listener.onSnowForm(event);
		}
		super.onSnowForm(event);
	}

}
