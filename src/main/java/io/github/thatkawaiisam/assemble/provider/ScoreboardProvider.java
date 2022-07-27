package io.github.thatkawaiisam.assemble.provider;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardProvider implements AssembleAdapter {

    private final LuckPerms luckperms = Hub.getInst().getHookManager().getLuckPerms();

    @Override
    public String getTitle(Player player) {
        return Format.Style(Hub.getInst().getScoreboardConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        Player p = player.getPlayer();
        final List<String> board = new ArrayList<>();
        for (String score : Hub.getInst().getScoreboardConfig().getStringList("SCOREBOARD.LINES")) {
            board.add(Format.Style(PlaceholderAPI.setPlaceholders(player, score))
                    .replaceAll("<rank>", Hub.getInst().getHookManager().getHook().getSuffix(player) + Hub.getInst().getHookManager().getHook().getName(player))
                    .replaceAll("<player>", player.getName()));
        }
        return board;
    }
}