package _7qv.dev.hub.commands.queue;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.hook.queue.custom.CustomQueue;
import _7qv.dev.hub.utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JoinQueueCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /joinqueue <server>");
            return true;
        }

        CustomQueue queue = Hub.getInst().getQueueManager().getQueue(args[0]);
        if (queue == null) {
            sender.sendMessage(Format.Style("&cCould not find the queue '" + args[0] + "."));
            return true;
        }
        Player player = (Player) sender;
        queue.addToQueue(player);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) {
            return Collections.emptyList();
        }
        List<String> option = new ArrayList<>();
        for (CustomQueue q : Hub.getInst().getQueueManager().getQueues()) {
            option.add(q.getServer());
        }
        for (String o : option) {
            if (args[0].startsWith(o)) {
                return Collections.singletonList(o);
            }
        }
        return option;
    }
}
