package _7qv.dev.hub.commands.queue;

import _7qv.dev.hub.Hub;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveQueueCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        if (args.length > 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /leavequeue");
            return true;
        }
        if (Hub.getInst().getQueueManager().getQueue(player) == null) {
            return true;
        }
        Hub.getInst().getQueueManager().getQueue(player).removeFromQueue(player);
        return false;
    }
}
