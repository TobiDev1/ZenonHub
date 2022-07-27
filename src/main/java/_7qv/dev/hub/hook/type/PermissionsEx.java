package _7qv.dev.hub.hook.ranks;

import _7qv.dev.hub.hook.Hook;
import _7qv.dev.hub.hook.HookManager;
import _7qv.dev.hub.utils.Format;
import org.bukkit.OfflinePlayer;

public class PermissionsEx implements Hook {

    @Override
    public String getName(OfflinePlayer player) {
        return Format.Style(HookManager.getInstance().getChat().getPrimaryGroup(String.valueOf(HookManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player) .replace("default", "None"));
    }

    @Override
    public String getPrefix(OfflinePlayer player) {
        return Format.Style(HookManager.getInstance().getChat().getPlayerPrefix(String.valueOf(HookManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }

    @Override
    public String getSuffix(OfflinePlayer player) {
        return Format.Style(HookManager.getInstance().getChat().getPlayerSuffix(String.valueOf(HookManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }

    @Override
    public String getColor(OfflinePlayer player) {
        return Format.Style(HookManager.getInstance().getChat().getPrimaryGroup(String.valueOf(HookManager.getInstance().getPlugin().getServer().getWorlds().get(0).getName()), player));
    }
}
