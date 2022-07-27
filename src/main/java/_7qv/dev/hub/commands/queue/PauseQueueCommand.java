package _7qv.dev.hub.commands.queue;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.hook.queue.custom.CustomQueue;
import _7qv.dev.hub.utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PauseQueueCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("hub.command.pausequeue")) {
            sender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /pausequeue <server>");
            return true;
        }
        CustomQueue queue = Hub.getInst().getQueueManager().getQueue(args[0]);
        if (queue == null) {
            sender.sendMessage(Format.Style("&cCould not find the queue '" + args[0] + "."));
            return true;
        }
        sender.sendMessage(Format.Style("&fYou have just toggled " + args[0] + "'s queue status to " +
                (queue.isPaused() ? "&ajoinable" : "&coff")
        ));
        queue.setPaused(!queue.isPaused());
        return true;
    }
}
