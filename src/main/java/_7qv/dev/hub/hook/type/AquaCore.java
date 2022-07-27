package _7qv.dev.hub.hook.ranks;

import _7qv.dev.hub.hook.Hook;
import org.bukkit.OfflinePlayer;

import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCoreAPI;

public class AquaCore implements Hook {

    @Override
    public String getName(OfflinePlayer player) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());
        return (data == null) ? "None" : data.getHighestRank().getName();
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());
        return (data == null) ? "None" : data.getHighestRank().getPrefix();
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());
        return (data == null) ? "None" : data.getHighestRank().getSuffix();
    }

    @Override
    public String getColor(OfflinePlayer player) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());
        return (data == null) ? "None" : data.getHighestRank().getColor() + data.getHighestRank().getName();
    }
}
