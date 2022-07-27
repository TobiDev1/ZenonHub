package _7qv.dev.hub.task;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author JavaBits on 8/1/2022.
 * @project [SRC] ZenonHub
 */
public class TipsTask implements Runnable {

    private AtomicInteger size = new AtomicInteger(0);

    @Override
    public void run() {
        List<String> tips = Hub.getInst().getConfig().getStringList("TIPS.MESSAGES");
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(Format.Style(tips.get(size.get()).replace("{0}", "\n")));
        });
        size.getAndIncrement();
        if (size.get() > tips.size() - 1) {
            size.set(0);
        }
    }

}