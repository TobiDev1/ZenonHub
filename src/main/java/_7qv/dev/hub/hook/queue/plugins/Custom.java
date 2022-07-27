package _7qv.dev.hub.hook.queue.plugins;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import org.bukkit.entity.Player;
import _7qv.dev.hub.hook.queue.Queue;

public class Custom extends Queue {
    @Override
    public boolean inQueue(Player p) {
        return Hub.getInst().getQueueManager().getQueue(p) != null;
    }

    @Override
    public String getQueueIn(Player p) {
        return Hub.getInst().getQueueManager().getQueue(p).getServer();
    }

    @Override
    public int getPosition(Player p) {
        int pos = Hub.getInst().getQueueManager().getQueue(p).getPlayers().indexOf(p) + 1;
        return pos;
    }

    @Override
    public int getQueueSize(String server) {
        return Hub.getInst().getQueueManager().getQueue(server).getSize();
    }

    @Override
    public void sendPlayer(Player p, String server) {
        if (Hub.getInst().getQueueManager().getQueue(server) == null) {
            p.sendMessage(Format.Style("&cFailed to add you to the " + server + "'s queue."));
            return;
        }
        Hub.getInst().getQueueManager().getQueue(server).addToQueue(p);
    }
}
