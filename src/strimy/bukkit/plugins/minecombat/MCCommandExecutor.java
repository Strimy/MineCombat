package strimy.bukkit.plugins.minecombat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MCCommandExecutor implements CommandExecutor 
{
	public MCCommandExecutor(MineCombat plugin) 
	{
		super();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandStr, String[] args) 
	{
		String senderName;
		
		if(args.length > 0)
		{
			if(args[0].equals("tdm"))
			{
				if(sender instanceof Player)
				{
					senderName = ((Player)sender).getDisplayName();
				}
				else
				{
					senderName = "Console";
				}
				MineCombat.Instance.getServer().broadcastMessage(senderName + " has requested a Team Deathmatch. " +
						"Type '/combat accept' to join the match");
				
				MineCombat.Instance.addTeamDeathMatch();
				
				
			}
		}
		
		return false;
	}

}
