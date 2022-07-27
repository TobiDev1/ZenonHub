package _7qv.dev.hub.hotbar.listeners;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.utils.Format;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderButt implements Listener {


	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.hasItem()) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getItem().getItemMeta() == null) { return; }
				if (e.getItem().getItemMeta().getDisplayName() == null) { return; }
				if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Format.Style(Hub.getInst().getHotbarConfig().getString("HOTBAR.ENDERBUTT.NAME")))) {
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3.5F));
                    e.setCancelled(true);
                    e.setUseItemInHand(Event.Result.DENY);
					e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    e.getPlayer().updateInventory();

                    }
				}
			}
		}
	}

