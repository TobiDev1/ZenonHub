package _7qv.dev.hub.listener;

import _7qv.dev.hub.Hub;
import _7qv.dev.hub.hotbar.listeners.particles.ParticleParameter;
import _7qv.dev.hub.utils.LocationUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;


public class NormalListener implements Listener {

    @EventHandler
    public void Join(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();
        p.setFoodLevel(20);
        p.setHealth(20);
        p.setWalkSpeed(0.3f);


        if (Hub.getInst().getConfig().getBoolean("BOOLEANS.JOIN-MESSAGE.ENABLE")) {
            for (String list : Hub.getInst().getMessagesConfig().getStringList("JOIN-MESSAGE")) {
                p.sendMessage(PlaceholderAPI.setPlaceholders(p, list));
            }
        }

        p.teleport(LocationUtil.parseToLocation(Hub.getInst().getConfig().getString("Spawn")));
    }

    @EventHandler
    public void onFodd(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDanage(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        if (!(event.getEntityType() == EntityType.PLAYER)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void damage(EntityDamageEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (ParticleParameter.set.containsKey(e.getPlayer())) {
            Bukkit.getScheduler().cancelTask((int)ParticleParameter.set.get(e.getPlayer()));
        }
        e.setQuitMessage(null);

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.PHYSICAL){
            Block block = e.getClickedBlock();
            if(block.getType() == Material.SOIL && block.getRelative(BlockFace.UP).getType() == Material.CROPS){
                e.setUseInteractedBlock(Event.Result.DENY);
                e.setCancelled(true);
                block.setTypeIdAndData(block.getType().getId(), block.getData(), true);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (Build.build.contains(e.getPlayer().getName()) && player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.admin")) {
            return;
        }
        e.setCancelled(true);
    }
    @EventHandler
    public void onClickInventory(final InventoryClickEvent event) {
        if (event.getWhoClicked().getGameMode().equals((Object)GameMode.CREATIVE)) {
            event.setCancelled(false);
            return;
        }
        event.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (Build.build.contains(e.getPlayer().getName()) && player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.admin")) {
            return;
        }
        e.setCancelled(true);
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (Build.build.contains(e.getPlayer().getName()) && player.getGameMode() == GameMode.CREATIVE && player.hasPermission("hub.admin")) {
            return;
        }
        e.setCancelled(true);
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        World world = Bukkit.getWorld("world");
        world.setWeatherDuration(0);
        e.setCancelled(true);
    }
}
