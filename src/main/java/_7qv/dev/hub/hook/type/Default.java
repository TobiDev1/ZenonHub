package _7qv.dev.hub.hook.ranks;

import _7qv.dev.hub.hook.Hook;
import org.bukkit.OfflinePlayer;


public class Default implements Hook {

    @Override
    public String getName(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return "None";
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return "None";
    }
}
