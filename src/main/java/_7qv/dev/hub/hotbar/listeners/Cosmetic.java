package _7qv.dev.hub.hotbar.listeners;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.hotbar.listeners.outfits.Outfits;
import _7qv.dev.hub.hotbar.listeners.particles.Particles;
import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.ItemBuilder;
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

import java.util.Arrays;
import java.util.List;

public class Cosmetic implements Listener {

    public static ItemStack Cosmetic() {
        ItemStack cosmetic = new ItemStack(Material.getMaterial(Hub.getInst().getHotbarConfig().getString("HOTBAR.COSMETIC.MATERIAL")));
        ItemMeta cosmeticm = cosmetic.getItemMeta();;
        cosmeticm.setDisplayName(Format.Style(Hub.getInst().getHotbarConfig().getString("HOTBAR.COSMETIC.NAME")));
        cosmetic.setItemMeta(cosmeticm);
        return cosmetic;
    }

    public void CosmeticInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, Format.Style(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.DISPLAYNAME")));
        List<String> armors = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.ARMORS.LORE"));
        List<String> armorsno = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.ARMORS.LORE.FOOTER"));

        List<String> hats = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.TAGS.LORE"));
        List<String> hatsno = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.TAGS.LORE.FOOTER"));

        List<String> Particles = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.PARTICLES.LORE"));
        List<String> Particlesno = Arrays.asList(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.PARTICLES.LORE.FOOTER"));

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(16 - 1, new ItemBuilder(Material.NAME_TAG).title("&2Tags").lores(hats).build());
        }
        else{
            inv.setItem(16 - 1, new ItemBuilder(Material.NAME_TAG).title("&2Tags").lores(hatsno).build());
        }

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(14 - 1, new ItemBuilder(Material.DIAMOND_CHESTPLATE).title("&2Armors").lores(armors).build());
        }else{
            inv.setItem(14 - 1, new ItemBuilder(Material.DIAMOND_CHESTPLATE).title("&2Armors").lores(armorsno).build());
        }

        if (p.hasPermission("hub.admin") && p.hasPermission("hub.donator")) {
            inv.setItem(12 - 1, new ItemBuilder(Material.BLAZE_POWDER).title("&2Particles").lores(Particles).build());
        }else{
            inv.setItem(12 - 1, new ItemBuilder(Material.BLAZE_POWDER).title("&2Particles").lores(Particlesno).build());
        }

        p.openInventory(inv);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction().equals((Object) Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object) Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            if (player.getItemInHand().equals((Object) Cosmetic())) {
                this.CosmeticInventory(player);
            }
        }
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
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(Format.Style(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.TITLE")))) {
            if (event.getSlot() == 14 - 1) {
                if(player.hasPermission("hub.donator")){
                    Outfits.OutfitInv(player);
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(Format.Style(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.STORE")));
                }
            } else if (event.getSlot() == 12 - 1) {
                if(player.hasPermission("hub.donator")){
                    Particles.ParticleInv(player);
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(Format.Style(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.STORE")));
                }
            } else if (event.getSlot() == 16 - 1) {
                if(player.hasPermission("hub.donator")){
                    Bukkit.dispatchCommand((CommandSender) player, Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.ITEMS.TAGS.COMMAND"));
                    event.setCancelled(true);
                }else{
                    player.closeInventory();
                    player.sendMessage(Format.Style(Hub.getInst().getCosmeticConfig().getString("MENU.COSMETICS.STORE")));
                }
            }
        }
    }
}