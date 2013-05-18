package code.husky;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChestCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("cm")) {
			String arg = args[0];
			
			if(arg.equalsIgnoreCase("")) {
				
			} else if(arg.equalsIgnoreCase("")) {
				
			}
			return true;
		}
		return false;
	}

}
