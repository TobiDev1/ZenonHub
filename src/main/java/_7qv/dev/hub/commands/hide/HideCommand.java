package _7qv.dev.hub.commands.hide;

import _7qv.dev.hub.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("hub.admin")) {
            sender.sendMessage(Format.Style("&cNo Permission."));
        }
        if (args.length < 1) {
            p.sendMessage(Format.Style("&7&m-----------------"));
            p.sendMessage(Format.Style("&bHideCommand &7- &fHelp Arguments"));
            p.sendMessage(Format.Style(""));
            p.sendMessage(Format.Style("&f/Hide All &7-&f hide all players."));
            p.sendMessage(Format.Style("&f/Hide unhide &7-&f unhide all players."));
            p.sendMessage(Format.Style("&f/Hide add &7-&f add show players."));
            p.sendMessage(Format.Style(""));
            p.sendMessage(Format.Style("&cThe command is in the testing phase!"));
            p.sendMessage(Format.Style("&7&m-----------------"));
        } else if (args[0].equalsIgnoreCase("all")) {
            HideAll.HideAll(p);
        } else if (args[0].equalsIgnoreCase("unhide")) {
            UnHide.unHide(p);
        } else if (args[0].equalsIgnoreCase("add")) {
            if (args.length < 2) {
                p.sendMessage(Format.Style("&7&m-----------------"));
                p.sendMessage(Format.Style("&bHideCommand &7- &fHelp Arguments"));
                p.sendMessage(Format.Style(""));
                p.sendMessage(Format.Style("&f/Hide All &7-&f hide all players."));
                p.sendMessage(Format.Style("&f/Hide unhide &7-&f unhide all players."));
                p.sendMessage(Format.Style("&f/Hide add &7-&f add show players."));
                p.sendMessage(Format.Style(""));
                p.sendMessage(Format.Style("&cThe command is in the testing phase!"));
                p.sendMessage(Format.Style("&7&m-----------------"));
            } else {
                Player t = Bukkit.getPlayer(args[1]);
                if (t == null) {
                    p.sendMessage(ChatColor.RED + "That player is currently offline");
                } else {
                    p.showPlayer(t);
                    p.sendMessage(Format.Style("&aYou has been add " + t.getName() + " at your list the show players."));
                }
            }
        }
        return false;
    }
}

