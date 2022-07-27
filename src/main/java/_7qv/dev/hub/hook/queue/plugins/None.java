package _7qv.dev.hub.hook.queue.plugins;

import _7qv.dev.hub.hook.queue.Queue;
import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.bungee.Bungee;
import org.bukkit.entity.Player;

public class None extends Queue {
    @Override
    public boolean inQueue(Player p) {
        return false;
    }

    @Override
    public String getQueueIn(Player p) {
        return "";
    }

    @Override
    public int getPosition(Player p) {
        return 0;
    }

    @Override
    public int getQueueSize(String server) {
        return 0;
    }

    @Override
    public void sendPlayer(Player p, String server) {
        if (Hub.getInst().getConfig().getBoolean("BUNGEECORD")) {
            Bungee.sendPlayerToServer(p, server);
        }
    }
}
