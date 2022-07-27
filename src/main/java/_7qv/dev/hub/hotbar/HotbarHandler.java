package _7qv.dev.hub.hotbar;

import _7qv.dev.hub.hotbar.listeners.Cosmetic;
import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.Hub;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HotbarHandler implements Listener {

    @EventHandler
    public void GiveItems(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        addItems(p);
    }

    public static void addItems(Player p){

        p.getInventory().setItem(Hub.getInst().getHotbarConfig().getInt("HOTBAR.SELECTOR.SLOT", 5-1), Selector());
        p.getInventory().setItem(Hub.getInst().getHotbarConfig().getInt("HOTBAR.ENDERBUTT.SLOT", 1-1), EnderPearl());
        p.getInventory().setItem(Hub.getInst().getHotbarConfig().getInt("HOTBAR.COSMETIC.SLOT", 9-1), Cosmetic.Cosmetic());
    }
    public static ItemStack Selector(){
        ItemStack selector = new ItemStack(Material.getMaterial(Hub.getInst().getHotbarConfig().getString("HOTBAR.SELECTOR.MATERIAL")));
        ItemMeta selectorm = selector.getItemMeta();
        selectorm.setDisplayName(Format.Style(Hub.getInst().getHotbarConfig().getString("HOTBAR.SELECTOR.NAME")));
        selector.setItemMeta(selectorm);
        return  selector;
    }
    public static ItemStack EnderPearl(){
        ItemStack enderpearl = new ItemStack(Material.getMaterial(Hub.getInst().getHotbarConfig().getString("HOTBAR.ENDERBUTT.MATERIAL")));
        ItemMeta enderpearlm = enderpearl.getItemMeta();
        enderpearlm.setDisplayName(Format.Style(Hub.getInst().getHotbarConfig().getString("HOTBAR.ENDERBUTT.NAME")));
        enderpearl.setItemMeta(enderpearlm);
        return  enderpearl;
    }
}
