package _7qv.dev.hub.hook.queue.plugins;

import _7qv.dev.hub.hook.queue.Queue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Portal extends Queue {
    @Override
    public boolean inQueue(Player p) {
        me.joeleoli.portal.shared.queue.Queue queue = me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId());
        return queue != null;
    }

    @Override
    public String getQueueIn(Player p) {
        me.joeleoli.portal.shared.queue.Queue queue = me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId());
        return queue.getName();
    }

    @Override
    public int getPosition(Player p) {
        me.joeleoli.portal.shared.queue.Queue queue = me.joeleoli.portal.shared.queue.Queue.getByPlayer(p.getUniqueId());
        return queue.getPosition(p.getUniqueId());
    }

    @Override
    public int getQueueSize(String server) {
        me.joeleoli.portal.shared.queue.Queue queue = me.joeleoli.portal.shared.queue.Queue.getByName(server);
        return queue.getPlayers().size();
    }

    @Override
    public void sendPlayer(Player p, String server) {
        Bukkit.getServer().dispatchCommand(p, "joinqueue " + server);
    }
}
