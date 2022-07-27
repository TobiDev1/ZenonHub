package _7qv.dev.hub.commands.hide;

import _7qv.dev.hub.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideAll {

    public static void HideAll(CommandSender sender){
        Player p = (Player) sender;
        if(!p.hasPermission("hub.admin")){
            sender.sendMessage(Format.Style("&cNo Permission."));
        }else{
            p.sendMessage(Format.Style("&aYou has been hide players."));
            for(Player players : Bukkit.getServer().getOnlinePlayers()){
                p.hidePlayer(players);
                players.hidePlayer(p);
                    }
                }
            }
        }



