package _7qv.dev.hub.hook.queue;

import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class Queue {
    public abstract boolean inQueue(Player player);

    public abstract String getQueueIn(Player player);

    public abstract int getPosition(Player player);

    public abstract int getQueueSize(String player);

    public abstract void sendPlayer(Player player, String server);


}
