package _7qv.dev.hub.hotbar.listeners.particles;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ParticleParameter {

    public static Map<Player, Integer> set;

        static{
        ParticleParameter.set =new HashMap<Player, Integer>();
    }
}
