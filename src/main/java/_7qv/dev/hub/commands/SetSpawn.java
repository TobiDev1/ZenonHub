package _7qv.dev.hub.commands;

import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.LocationUtil;
import _7qv.dev.hub.Hub;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawn  implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player p = (Player)sender;
        if(!(sender instanceof Player)){
            sender.sendMessage(Format.Style("&cDont use this command in console."));
        }else if(!p.hasPermission("hub.setspawn")) {
            sender.sendMessage(Format.Style("&cYou don have permission for use this command."));
        }else{
            Location loc = p.getLocation();

            Hub.getInst().getConfig().set("Spawn", LocationUtil.parseToString(loc));
            p.sendMessage(Format.Style("&6you have setted spawn point."));

            Hub.getInst().saveConfig();
            Hub.getInst().reloadConfig();
        }
        return false;
    }
}
