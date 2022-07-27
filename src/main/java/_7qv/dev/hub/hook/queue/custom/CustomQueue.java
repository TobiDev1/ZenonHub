package _7qv.dev.hub.hook.queue.custom;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.bungee.Bungee;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

@Getter
public class CustomQueue {

    private static Set<CustomQueue> offlinePlayers = new HashSet<>();

    private Hub plugin;
    private String server;
    private List<UUID> players;
    private Map<Player, BukkitTask> playerTaskMap;
    @Setter
    private boolean paused;

    public CustomQueue(String server) {
        this.plugin = Hub.getInst();
        players = new ArrayList<>();
        this.playerTaskMap = new HashMap<>();
        this.paused = false;
        this.server = server;
        new BukkitRunnable() {
            @Override
            public void run() {
                players.forEach(player -> {
                    if (Bukkit.getPlayer(player).isOnline()) {
                        if (plugin.getQueueManager().getQueue(Bukkit.getPlayer(player)).isPaused()) {
                            for (String msg : plugin.getMessagesConfig().getStringList("QUEUE.PAUSED")) {
                                String pos = String.valueOf(plugin.getQueueManager().getQueue(Bukkit.getPlayer(player)).getPlayers().indexOf(player) + 1);
                                String size = String.valueOf(plugin.getQueueManager().getQueue(Bukkit.getPlayer(player)).getSize());
                                Bukkit.getPlayer(player).sendMessage(Format.Style(msg
                                        .replaceAll("%size%", size)
                                        .replaceAll("%queue%", server)
                                        .replaceAll("%pos%", pos)));
                            }
                        } else {
                            String pos = String.valueOf(plugin.getQueueManager().getQueue(Bukkit.getPlayer(player)).getPlayers().indexOf(player) + 1);
                            String size = String.valueOf(plugin.getQueueManager().getQueue(Bukkit.getPlayer(player)).getSize());
                            for (String msg : plugin.getMessagesConfig().getStringList("QUEUE.PAUSED")) {
                                Bukkit.getPlayer(player).sendMessage(Format.Style(msg
                                        .replaceAll("%size%", size)
                                        .replaceAll("%queue%", server)
                                        .replaceAll("%pos%", pos)));
                            }
                        }
                    } else {
                        players.remove(player);

                    }
                });
            }
        }.runTaskTimerAsynchronously(Hub.getInst(), 20 * plugin.getQueueConfig().getInt("QUEUE-MESSAGE.DELAY"), 20 * plugin.getQueueConfig().getInt("QUEUE-MESSAGE.DELAY"));
    }

    public void addToQueue(Player player) {
        if (players.contains(player.getUniqueId())) {
            return;
        }
        if (plugin.getQueueManager().getPriority(player) == 0 && player.hasPermission("queue.bypass")) {
            if (Hub.getInst().getConfig().getBoolean("BungeeCord")) {
                Bungee.sendPlayerToServer(player, this.server);
            } else {
                Bukkit.getConsoleSender().sendMessage(Format.Style("&cWe tried to send " + player.getName() + " to a server using the built in queue, but couldn't because hub doesn't have BungeeCord enabled in the config.yml."));
            }
            return;
        }
        List<String> message = Hub.getInst().getMessagesConfig().getStringList("QUEUE.JOINED");
        for (String msg : message) {
            player.sendMessage(Format.Style(msg.replaceAll("%queue%", this.server)));
        }
        players.add(player.getUniqueId());

        players.forEach(queuePlayer -> {
            int pos = players.indexOf(queuePlayer);
            if (Bukkit.getPlayer(queuePlayer) != player && this.plugin.getQueueManager().getPriority(player) < this.plugin.getQueueManager().getPriority(Bukkit.getPlayer(queuePlayer))) {
                if (Bukkit.getPlayer(players.get(pos)).isOnline()) {
                    List<String> msg = Hub.getInst().getMessagesConfig().getStringList("QUEUE.HIGHER-PRIORITY");
                    for (String mes : msg) {
                        Bukkit.getPlayer(players.get(pos)).sendMessage(Format.Style(mes));
                    }
                }
                Collections.swap(players, pos, players.size() - 1);
            }
        });
    }

    public void removeFromQueue(Player p) {
        if (!players.contains(p.getUniqueId())) {
            return;
        }
        players.remove(p.getUniqueId());
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayer(int p) {
        return Bukkit.getPlayer(players.get(p));
    }

    public String getQueued(int p) {
        return "true";
    }

    public String getPlayerRank(int p) {
         Player player = getPlayer(p);
        return Hub.getInst().getHookManager().getHook().getSuffix(player) + Hub.getInst().getHookManager().getHook().getName(player);}

    public void update() {
        if (!players.isEmpty()) {
            Player p = Bukkit.getPlayer(players.get(0));
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(this.server);
            p.sendPluginMessage(Hub.getInst(), "BungeeCord", out.toByteArray());
        }
    }

}
