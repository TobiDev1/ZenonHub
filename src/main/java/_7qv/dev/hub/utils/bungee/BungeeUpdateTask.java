package _7qv.dev.hub.utils.bungee;

import org.bukkit.Bukkit;

public class BungeeUpdateTask implements Runnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream().findFirst().ifPresent(Bungee::updateCount);
    }
}
