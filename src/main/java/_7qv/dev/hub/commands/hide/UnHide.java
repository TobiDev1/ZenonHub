package _7qv.dev.hub.commands.hide;

import _7qv.dev.hub.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnHide {

    public static void unHide(CommandSender sender){
        Player p = (Player) sender;
        if(!p.hasPermission("hub.admin")){
            sender.sendMessage(Format.Style("&cNo Permission."));
        }else{
            p.sendMessage(Format.Style("&aYou has been unhide players."));
            for(Player Players : Bukkit.getServer().getOnlinePlayers()){
                Players.showPlayer(p);
                p.showPlayer(Players);
            }
        }
    }
}
