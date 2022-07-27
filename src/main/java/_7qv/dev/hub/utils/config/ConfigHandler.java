package _7qv.dev.hub.utils.config;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.java.*;
import java.io.*;
import org.bukkit.configuration.*;

public class ConfigHandler extends YamlConfiguration {
    private File file;

    private String name;

    private String directory;

    public ConfigHandler(JavaPlugin plugin, String name, String directory) {
        setName(name);
        setDirectory(directory);
        this.file = new File(directory, String.valueOf(name) + ".yml");
        if (!this.file.exists())
            plugin.saveResource(String.valueOf(name) + ".yml", false);
        load();
        save();
    }

    public void load() {
        try {
            load(this.file);
        } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return (Configuration)this;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void reloadConfiguration() {
        YamlConfiguration.loadConfiguration(this.file);
    }
}
