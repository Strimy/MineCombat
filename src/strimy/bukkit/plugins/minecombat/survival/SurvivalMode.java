package strimy.bukkit.plugins.minecombat.survival;

import org.bukkit.plugin.java.JavaPlugin;

public class SurvivalMode extends JavaPlugin
{
	public static SurvivalMode Instance;
	
	@Override
	public void onDisable() 
	{
		Instance = null;
	}

	@Override
	public void onEnable() 
	{
		Instance = this;
	}

}
