package _7qv.dev.hub.commands;

import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.LocationUtil;
import _7qv.dev.hub.Hub;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    FileConfiguration config = Hub.getInst().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player p = (Player)sender;
        if(!(sender instanceof Player)){
            sender.sendMessage(Format.Style("&cDont use this command in console."));
        }else if(!p.hasPermission("hub.spawn")) {
            sender.sendMessage(Format.Style("&cYou don have permission for use this command."));
        }else {
            if (LocationUtil.parseToLocation(config.getString("Spawn")) != null) {
                p.teleport(LocationUtil.parseToLocation(config.getString("Spawn")));

            } else {
                Bukkit.getConsoleSender().sendMessage(Format.Style("&cThere's no spawn set."));
            }
            p.teleport(LocationUtil.parseToLocation(config.getString("Spawn")));
            p.sendMessage(Format.Style("&6Sending you to spawn."));

        }
        return false;
    }
}
