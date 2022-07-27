package _7qv.dev.hub.hook;

import _7qv.dev.hub.hook.ranks.Default;
import _7qv.dev.hub.hook.ranks.chat_luck;
import _7qv.dev.hub.hook.ranks.AquaCore;
import _7qv.dev.hub.hook.ranks.PermissionsEx;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;

public class HookManager {

    private static HookManager instance;

	private Plugin plugin;
    private String hookSystem;
    private Hook hook;
    private Chat chat;
    private long duration;
    private LuckPerms luckperms;

    private boolean permanent;

    public HookManager(Plugin plugin) {
        instance = this;
        this.plugin = plugin;
    }

    public void loadHook() {
        if (Bukkit.getPluginManager().getPlugin("AquaCore") != null) {
            setHook(new AquaCore());
            setHookSystem("AquaCore");
        }
        else if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            loadVault();
            loadLuckPerms();

            if (getChat() == null) {
                setHook(new Default());
                setHookSystem("None");
                return;
            }

            if (getChat().getName().contains("PermissionsEx")) {
                setHook(new PermissionsEx());
                setHookSystem("PermissionsEx");
            }
            else if (getChat().getName().contains("LuckPerms")) {
                setHook(new chat_luck());
                setHookSystem("LuckPerms");
            }
        }
        else {
            setHook(new Default());
            setHookSystem("None");
        }
    }

    private void loadVault() {
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) chat = rsp.getProvider();
    }

    private void loadLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            this.luckperms = provider.getProvider();
        }
    }
    
    public static HookManager getInstance() { return instance; }

	public static void setInstance(HookManager instance) { HookManager.instance = instance; }

	public Plugin getPlugin() { return plugin; }

	public void setPlugin(Plugin plugin) { this.plugin = plugin; }

	public String getHookSystem() { return hookSystem; }

	public void setHookSystem(String rankSystem) { this.hookSystem = rankSystem; }

	public Hook getHook() { return hook; }

	public void setHook(Hook rank) { this.hook = rank; }

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

    public boolean isPermanent() {
        return this.permanent;

   }

    public LuckPerms getLuckPerms() {
        return luckperms;
    }

}
