package _7qv.dev.hub.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Format {

    public static String Style(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static List<String> list(List<String> lore) {
        ArrayList<String> toAdd = new ArrayList<>();
        for (String lor : lore) {
            toAdd.add(Style(lor));
        }
        return toAdd;
    }

    public static String formatMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static List<String> formatMessages(List<String> messages) {
        List<String> buffered = new ArrayList<>();
        for (String message : messages){
            buffered.add(formatMessage("&r" + message));
        }
        return buffered;
    }

    public static String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);

    }
}
