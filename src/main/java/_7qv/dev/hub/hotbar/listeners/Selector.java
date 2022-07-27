package in.hubcore.items.listeners;

import com.lunarclient.bukkitapi.LunarClientAPI;
import in.hubcore.Hub;
import in.hubcore.items.ItemsHandler;
import in.hubcore.utils.Bungee;
import in.hubcore.utils.Format;
import in.hubcore.utils.ItemBuilder;
import me.joeleoli.portal.shared.queue.Queue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;
import java.util.regex.Pattern;

public class Selector implements Listener {

    private Pattern status;


    public void SelectorInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, Format.Style("&2Server Selector"));

        ArrayList<String> lore1 = new ArrayList<>();
        lore1.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(11 - 1, new ItemBuilder(Material.DIAMOND_SWORD).title("&2&lHCF").lores(lore1).build());
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(12 - 1, new ItemBuilder(Material.ENDER_CHEST).title("&2&lKits").lores(lore2).build());
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(13 - 1, new ItemBuilder(Material.IRON_SWORD).title("&2&lLeagues").lores(lore3).build());
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(14 - 1, new ItemBuilder(Material.STAINED_GLASS_PANE).title("&2&lServer Media").lores(lore4).build());

        ArrayList<String> lore5 = new ArrayList<>();
        lore3.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(15 - 1, new ItemBuilder(Material.STONE_SWORD).title("&2&lRegions").lores(lore5).build());
        ArrayList<String> lore6 = new ArrayList<>();
        lore3.addAll(Collections.singleton("&7Test Put your Config"));
        inv.setItem(16 - 1, new ItemBuilder(Material.WOOD_SWORD).title("&2&lVultane").lores(lore6).build());

        p.openInventory(inv);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals((Object) Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object) Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            if (player.getItemInHand().equals((Object) ItemsHandler.Selector())) {
                this.SelectorInventory(player);
            }
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Queue queue = Queue.getByPlayer(player.getUniqueId());

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(Format.Style("&2Server Selector"))) {
            if (event.getSlot() == 11 - 1) {
                Bukkit.dispatchCommand((CommandSender) player, "joinqueue hcf");
                player.closeInventory();
            } else if (event.getSlot() == 12 - 1) {
                Bukkit.dispatchCommand((CommandSender) player, "joinqueue kits");
                player.closeInventory();
            }
        }
    }
}


