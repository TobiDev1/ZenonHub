package _7qv.dev.hub.hook;

import org.bukkit.OfflinePlayer;

public interface Hook {

    String getName(OfflinePlayer offlinePlayer);
    String getPrefix(OfflinePlayer offlinePlayer);
    String getSuffix(OfflinePlayer offlinePlayer);
    String getColor(OfflinePlayer offlinePlayer);



}
