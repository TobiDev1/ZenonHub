package _7qv.dev.hub.commands;

import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.Hub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Author implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player p = (Player)sender;
        p.sendMessage(Format.Style("&7&m---[+]--------------------------[+]---"));
        p.sendMessage(Format.Style(""));
        p.sendMessage(Format.Style("&7[&4Zenon-Hub&7] &eDeveloped for &eInvite.gg/BurningDev"));
        p.sendMessage(Format.Style("&7[&4Zenon-Hub&7] &eVersion &a&l" + Hub.getInst().getVersion()));
        p.sendMessage(Format.Style("&7[&4Zenon-Hub&7] &eContributors &aJavaBits,Kuij" ));
        p.sendMessage(Format.Style("" ));
        p.sendMessage(Format.Style("&7&m---[+]--------------------------[+]---"));
        return false;
    }
}
