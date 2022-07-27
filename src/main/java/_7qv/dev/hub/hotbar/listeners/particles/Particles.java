package _7qv.dev.hub.hotbar.listeners.particles;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class Particles implements Listener {

    public static void ParticleInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, Format.Style("&2Particle Menu"));
        List<String> lore = Arrays.asList("","&6Click to select Outfit","");

       /* for (int i = 0; i < 27; i++) {
            ItemStack asd = new ItemBuilder(Material.STAINED_GLASS_PANE).title(" ").data((short)7).build();
            inv.setItem(i, asd);
        }*/

        inv.setItem(12 -1, new ItemBuilder(Material.BLAZE_POWDER).title("&6Flame Particle").build());
        inv.setItem(14 -1, new ItemBuilder(Material.TNT).title("&eExplosion Particle").build());
        inv.setItem(16 -1, new ItemBuilder(Material.REDSTONE).title("&cHeart Particle").build());

        p.openInventory(inv);
    }

    @EventHandler
    public void onClickInventory (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(Format.Style("&2Particle Menu"))) {
            if (player.hasPermission("hub.admin") && player.hasPermission("hub.donator")) {
                if (event.getSlot() == 12 - 1) {
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.FLAME.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been Changed your &2Particle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.FLAME.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been set &6Flame &fParticle!"));
                        player.closeInventory();
                    }
                    }else if(event.getSlot() == 14 - 1){
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.EXPLOSION_NORMAL.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been Changed your &2Particle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.EXPLOSION_NORMAL.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been set &eExplosion &fParticle!"));
                        player.closeInventory();
                    }
                    }else if(event.getSlot() == 16 - 1){
                    if(ParticleParameter.set.containsKey(player)){
                        Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(player));
                        ParticleParameter.set.replace(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.HEART.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been Changed your &2Particle!"));
                        player.closeInventory();
                    }else {
                        ParticleParameter.set.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Hub.getInst(), () -> ParticleEffect.HEART.display(10.0f, 15.0f, 0.0f, 0.0f, 0, player.getLocation().add(-0.0, 2.0, 0.0), 10.0), 0L, 0L));
                        player.sendMessage(Format.Style("&fYou has been set &cHeart &fParticle!"));
                        player.closeInventory();
                    }
                }
            } else {
                player.sendMessage(Format.Style("&cPurchase it at store.serpentmc.club"));
                player.closeInventory();
                event.setCancelled(true);
            }
        }
    }
}
