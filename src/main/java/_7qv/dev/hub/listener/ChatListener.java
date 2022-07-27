package _7qv.dev.hub.listener;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    public boolean isLunarClient(Player player) {
        return LunarClientAPI.getInstance().isRunningLunarClient(player);
    }

    @EventHandler
    public void onChatFormat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String lunarclient = isLunarClient(player) ? Format.Style("&9&l\uff2c&b&l\uff23 ") : "";

        for (String chat : Hub.getInst().getConfig().getStringList("CHAT-FORMAT")) {

            chat = chat.replace("%rank%", Hub.getInst().getHookManager().getHook().getPrefix(player));
            chat = chat.replace("%player%", player.getName());

            chat = chat.replace("%lunarclient%", lunarclient);
            chat = chat.replace("%message%", event.getMessage());

            event.setFormat(Format.Style(chat));
        }
    }

}
