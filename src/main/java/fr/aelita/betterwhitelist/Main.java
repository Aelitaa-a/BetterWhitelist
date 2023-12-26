package fr.aelita.betterwhitelist;

import fr.aelita.betterwhitelist.commands.WhitelistCommand;
import fr.aelita.betterwhitelist.listener.PlayerJoinListener;
import fr.aelita.betterwhitelist.manager.WhitelistManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private WhitelistManager whitelistManager;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        this.whitelistManager = new WhitelistManager();
        this.registerListeners();
        this.registerCommands();
    }

    @Override
    public void onDisable() {
        instance = null;
        this.whitelistManager = null;
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    private void registerCommands(){
        getCommand("whitelist").setExecutor(new WhitelistCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public WhitelistManager getWhitelistManager() {
        return whitelistManager;
    }
}
