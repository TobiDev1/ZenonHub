package _7qv.dev.hub.utils.bungee;

import _7qv.dev.hub.Hub;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Bungee implements PluginMessageListener {
    public static int PLAYER_COUNT;

    static {
        Bungee.PLAYER_COUNT = 0;
    }

    public static void updateCount(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF("ALL");
        player.sendPluginMessage(Hub.getInst(), "BungeeCord", output.toByteArray());
    }

    public static void sendPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(Hub.getInst(), "BungeeCord", out.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if (!channel.equalsIgnoreCase("BungeeCord"))
            return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);

        String subchannel = input.readUTF();

        if (subchannel.equals("PlayerCount") && input.readUTF().equalsIgnoreCase("ALL")) {
            Bungee.PLAYER_COUNT = input.readInt();
        }

    }

}
