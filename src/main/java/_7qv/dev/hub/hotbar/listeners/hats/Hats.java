package _7qv.dev.hub.hotbar.listeners.hats;

import _7qv.dev.hub.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Hats implements Listener {

    public static void HatsInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, Format.Style("&2Hats Menu"));
        List<String> lore = Arrays.asList("", "&bClick to select Outfit", "");

        ItemStack mob = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta mobm = mob.getItemMeta();
        mobm.setDisplayName(Format.Style("&2&lMobs"));
        mob.setItemMeta(mobm);

        ItemStack astrounat = new ItemStack(Material.STAINED_GLASS, 1, (short) 0);
        ItemMeta astrounatm = astrounat.getItemMeta();
        astrounatm.setDisplayName(Format.Style("&f&lAstronaut"));
        astrounat.setItemMeta(astrounatm);

        ItemStack Colour = new ItemStack(Material.WOOL, 1, (short) 6);
        ItemMeta Colourm = Colour.getItemMeta();
        Colourm.setDisplayName(Format.Style("&dColours"));
        Colour.setItemMeta(Colourm);

        inv.setItem(12 - 1, mob);
        inv.setItem(14 - 1, astrounat);
        inv.setItem(16 - 1, Colour);

        p.openInventory(inv);
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(Format.Style("&2Hats Menu"))) {
            if (player.hasPermission("hub.admin") && player.hasPermission("hub.donator")) {
                if (event.getSlot() == 12 - 1) {
                    player.sendMessage("");
                    event.setCancelled(true);
                } else if (event.getSlot() == 14 - 1) {
                    event.setCancelled(true);
                }
                else if(event.getSlot() == 16 -1){
                    event.setCancelled(true);
                }
            } else {
                player.sendMessage(Format.Style("&cPurchase it at store.serpentmc.club"));
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}

