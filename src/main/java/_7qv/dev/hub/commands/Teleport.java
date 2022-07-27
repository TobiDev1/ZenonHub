package _7qv.dev.hub.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author 7qv_ on 25/1/2022.
 * @project [SRC] HubCore
 */
public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player  = (Player) sender;
        return false;
    }
}
