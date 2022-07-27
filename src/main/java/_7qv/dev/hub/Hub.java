package _7qv.dev.hub;

import _7qv.dev.hub.commands.Author;
import _7qv.dev.hub.commands.SetSpawn;
import _7qv.dev.hub.commands.Spawn;
import _7qv.dev.hub.commands.queue.JoinQueueCommand;
import _7qv.dev.hub.commands.queue.LeaveQueueCommand;
import _7qv.dev.hub.commands.queue.PauseQueueCommand;
import _7qv.dev.hub.hook.queue.Queue;
import _7qv.dev.hub.hook.queue.custom.CustomQueue;
import _7qv.dev.hub.hook.queue.manager.QueueManager;
import _7qv.dev.hub.hook.queue.plugins.Custom;
import _7qv.dev.hub.hook.queue.plugins.EzQueue;
import _7qv.dev.hub.hook.queue.plugins.None;
import _7qv.dev.hub.hook.queue.plugins.Portal;
import _7qv.dev.hub.hotbar.HotbarHandler;
import _7qv.dev.hub.hotbar.listeners.Cosmetic;
import _7qv.dev.hub.hotbar.listeners.EnderButt;
import _7qv.dev.hub.utils.Format;
import _7qv.dev.hub.utils.tablist.provider.TablistProvider;
import _7qv.dev.hub.utils.tablist.v1_8_R3.v1_8_R3TabAdapter;
import _7qv.dev.hub.commands.hide.HideCommand;
import _7qv.dev.hub.hook.HookManager;
import _7qv.dev.hub.hotbar.listeners.hats.Hats;
import _7qv.dev.hub.hotbar.listeners.outfits.Outfits;
import _7qv.dev.hub.hotbar.listeners.particles.Particles;
import _7qv.dev.hub.listener.LunarClientListener;
import _7qv.dev.hub.listener.NormalListener;
import _7qv.dev.hub.task.TipsTask;
import _7qv.dev.hub.utils.bungee.Bungee;
import _7qv.dev.hub.utils.config.ConfigHandler;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import io.github.thatkawaiisam.assemble.provider.ScoreboardProvider;
import _7qv.dev.hub.utils.tablist.shared.TabHandler;
import _7qv.dev.hub.utils.tablist.v1_7_R4.v1_7_R4TabAdapter;
import lombok.Getter;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class Hub extends JavaPlugin {

    public static Hub inst;
    private HookManager hookManager;
    public static Chat chat;
    private Bungee serverHandler;
    private boolean isPlaceholderAPI = false;
    String version = Bukkit.getVersion();
    private QueueManager queueManager;
    private ConfigHandler hotbarConfig;
    private ConfigHandler scoreboardConfig;
    private ConfigHandler tablistConfig;
    private ConfigHandler messagesConfig;
    private ConfigHandler menusConfig;
    private ConfigHandler queueConfig;
    private ConfigHandler cosmeticConfig;

    private Queue queue;

    public void onEnable() {
        inst = this;
        saveDefaultConfig();
        this.serverHandler = new Bungee();

        if (Hub.getInst().getConfig().getBoolean("TIPS.ENABLED")) {
            int seconds = Hub.getInst().getConfig().getInt("TIPS.SEND-EVERY");
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, new TipsTask(), 20L * seconds, 20L * seconds);
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.isPlaceholderAPI = true;
            Bukkit.getConsoleSender().sendMessage(Format.Style("&aHooked into PlaceholderAPI"));
        }

        Bukkit.getConsoleSender().sendMessage(Format.Style("&8&m--------------------------"));
        Bukkit.getConsoleSender().sendMessage(Format.Style("&cZenonHub has been &aenabled"));
        Bukkit.getConsoleSender().sendMessage(Format.Style("&cMade by &cBurning Development"));
        Bukkit.getConsoleSender().sendMessage(Format.Style("&8&m--------------------------"));

        // saveDefaultConfig();
        // reloadConfig();
        list();
        registerYMLS();
        scoreboardProvide();
        tablistProvide();
        setupQueue();

        getCommand("build").setExecutor(new Build());
        getCommand("hide").setExecutor(new HideCommand());
        getCommand("setspawn").setExecutor(new SetSpawn());
        getCommand("Spawn").setExecutor(new Spawn());
        getCommand("author").setExecutor(new Author());
        getCommand("joinqueue").setExecutor(new JoinQueueCommand());
        getCommand("pausequeue").setExecutor(new PauseQueueCommand());
        getCommand("leavequeue").setExecutor(new LeaveQueueCommand());
    }
    public void onDisable() {
        inst = null;
        reloadConfig();
    }
    public void list() {
        PluginManager m = Bukkit.getPluginManager();
        new LunarClientListener();
        hookManager = new HookManager(this);
        hookManager.loadHook();
        queueManager = new QueueManager(this);
        m.registerEvents(new LunarClientListener(), this);
        m.registerEvents(new HotbarHandler(), this);
        m.registerEvents(new NormalListener(), this);
        m.registerEvents(new Selector(), this);
        m.registerEvents(new EnderButt(),this);
        m.registerEvents(new Cosmetic(),this);
        m.registerEvents(new Outfits(),this);
        m.registerEvents(new Particles(),this);
        m.registerEvents(new Hats(),this);
    }
    public void scoreboardProvide() {

        Assemble assemble = new Assemble(this, new ScoreboardProvider());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.MODERN);
    }

    public void tablistProvide() {

        if (Bukkit.getVersion().contains("1.7")) new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(), this, 20L);
        if (Bukkit.getVersion().contains("1.8")) new TabHandler(new v1_8_R3TabAdapter(), new TablistProvider(), this, 20L);
    }

    public void registerYMLS() {
        this.hotbarConfig = new ConfigHandler(this, "hotbar", this.getDataFolder().getAbsolutePath());
        this.scoreboardConfig = new ConfigHandler(this, "scoreboard", this.getDataFolder().getAbsolutePath());
        this.messagesConfig = new ConfigHandler(this, "messages", this.getDataFolder().getAbsolutePath());
        this.menusConfig = new ConfigHandler(this, "menus", this.getDataFolder().getAbsolutePath());
        this.queueConfig = new ConfigHandler(this, "queue", this.getDataFolder().getAbsolutePath());
        this.tablistConfig = new ConfigHandler(this, "tab", this.getDataFolder().getAbsolutePath());
        this.cosmeticConfig = new ConfigHandler(this, "cosmetics", this.getDataFolder().getAbsolutePath());
    }

    public void setupQueue() {
        String queuef = getConfig().getString("QUEUE-SYSTEM").toUpperCase();
        queue = null;
        switch (queuef) {
            case "EZQUEUE":
                if (Bukkit.getServer().getPluginManager().getPlugin("EzQueueSpigot") == null) {
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                }
                queue = new EzQueue();
                break;
            case "PORTAL":
                if (Bukkit.getServer().getPluginManager().getPlugin("Portal") == null) {
                    Bukkit.getServer().getPluginManager().disablePlugin(this);
                }
                queue = new Portal();
                break;
            case "CUSTOM":
                queue = new Custom();
                queueManager.getQueues().clear();
                for (String sect : getQueueConfig().getConfigurationSection("QUEUES").getKeys(false)) {
                    String name = getQueueConfig().getString("QUEUES." + sect + ".BUNGEE-NAME");
                    queueManager.getQueues().add(new CustomQueue(name));
                }
                queueManager.getQueues().forEach(queue -> Bukkit.getConsoleSender().sendMessage(Format.Style("&b" + queue.getServer() + " &fwas found and has been created!")));
                int queueDelay = getQueueConfig().getInt("QUEUE-DELAY");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        queueManager.getQueues().forEach(queue -> {
                            if (!queue.isPaused() && !queue.getPlayers().isEmpty()) {
                                queue.update();
                                queue.removeFromQueue(queue.getPlayer(0));
                            }
                        });
                    }
                }.runTaskTimer(getInst(), queueDelay * 20L, queueDelay * 20L);
                Bukkit.getPluginManager().registerEvents(new QueueManager(this), this);
                break;
            default:
                queue = new None();
                break;
        }
    }

    public static Hub getInst() {
        return inst;
    }
    public Bungee getServerHandler() {
        return this.serverHandler;
    }

}
