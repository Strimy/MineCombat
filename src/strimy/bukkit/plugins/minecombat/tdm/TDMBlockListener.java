package strimy.bukkit.plugins.minecombat.tdm;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TDMBlockListener extends BlockListener 
{

	@Override
	public void onBlockBreak(BlockBreakEvent event) 
	{
		// TODO Auto-generated method stub
		super.onBlockBreak(event);
	}

	@Override
	public void onBlockCanBuild(BlockCanBuildEvent event) {
		// TODO Auto-generated method stub
		super.onBlockCanBuild(event);
	}

	@Override
	public void onBlockPlace(BlockPlaceEvent event) 
	{
		super.onBlockPlace(event);
	}

}
