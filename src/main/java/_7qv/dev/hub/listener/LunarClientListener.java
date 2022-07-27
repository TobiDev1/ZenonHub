
package _7qv.dev.hub.listener;

import java.util.ArrayList;
import java.util.List;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class LunarClientListener implements Listener {

	public void updateNameTag( Player player) {
		for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			Player player2 = onlinePlayer.getPlayer();
			scheduler.scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> LunarClientAPI.getInstance().overrideNametag(player2, (List)this.resetNameTag(player2), player), 0L, 20L);
		}
	}

	public List<String> resetNameTag(Player player) {
		List<String> tag = new ArrayList<String>(); {
		tag.add(Format.Style(Hub.getInst().getHookManager().getHook().getPrefix(player) + Hub.getInst().getHookManager().getHook().getName(player)));
		}
		tag.add(Format.Style(Hub.getInst().getHookManager().getHook().getPrefix(player) + player.getName()));
		return tag;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		this.updateNameTag(player);
	}
}