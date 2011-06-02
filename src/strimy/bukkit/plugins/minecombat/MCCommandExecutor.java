package strimy.bukkit.plugins.minecombat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import strimy.bukkit.plugins.minecombat.interfaces.IMode;

public class MCCommandExecutor implements CommandExecutor 
{
	public MCCommandExecutor(MineCombat plugin) 
	{
		super();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandStr, String[] args) 
	{
		if(args.length > 0)
		{
			if(args[0].equals("tdm"))
			{				
				MineCombat.Instance.addTeamDeathMatch(sender);
				
				return true;
			}
			else if(args[0].equals("list"))
			{
				sender.sendMessage("Instances list : ");
				int i = 0;
				for (IMode mode : MineCombat.Instance.listCombat) 
				{
					sender.sendMessage(ChatColor.LIGHT_PURPLE + " "+ i++ +" / " +mode.getModeName() + " - " + mode.getModeName() + " : " + mode.getPlayerCount() + " players");
					
				}
				
				return true;
			}
			else if(args[0].equals("join"))
			{
				if(args.length == 1)
				{
					sender.sendMessage(ChatColor.RED + "You must enter an instance number (/combat join 0). Use '/combat list' to display the instances");
				}
				else
				{
					MineCombat.Instance.listCombat.get(Integer.parseInt(args[1])).joinPlayer((Player)sender);
				}
				return true;
			}
			else if(args[0].equals("boots"))
			{
				((Player)sender).getInventory().setBoots(null);
			}
		}
		
		return false;
	}

}
