package _7qv.dev.hub.utils;

import _7qv.dev.hub.Hub;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PetUtils {


    public static void FollowPet(ArmorStand a, Player p){
        new BukkitRunnable(){
            @Override
            public void run() {
                if (p.getLocation().distanceSquared(a.getLocation()) >= 32.0) {
                    Location loc = a.getLocation().add(p.getLocation().toVector().subtract(a.getLocation().toVector().setY(0).normalize().multiply(0.6)));
                    a.teleport(loc);
                }
            }
        }.runTaskTimer(Hub.getInst(),0L,0L);
    }
}
