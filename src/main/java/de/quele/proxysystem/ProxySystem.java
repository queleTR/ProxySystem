package de.quele.proxysystem;

import de.quele.proxysystem.commands.GlobalChatClearCommand;
import de.quele.proxysystem.commands.WhereamiCommand;
import de.quele.proxysystem.commands.WhereisCommand;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class ProxySystem extends Plugin {

    private static ProxySystem proxySystem;
    private final String prefix = "§3Proxy §8» §7";

    @Override
    public void onEnable() {
        proxySystem = this;

        this.initListeners();
        this.initCommands();

        this.getLogger().info("Plugin successfully activated!");
        this.getLogger().info(("Coded by quele | Muhammed"));
    }
    private void initListeners() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
    }

    private void initCommands() {
    PluginManager pluginManager = this.getProxy().getPluginManager();
    pluginManager.registerCommand(this, new WhereamiCommand());
    pluginManager.registerCommand(this, new WhereisCommand());
    pluginManager.registerCommand(this, new GlobalChatClearCommand());
    }

    @Override
    public void onDisable() {
        proxySystem = this;
        this.getLogger().info("Plugin successfully disabled!");
        this.getLogger().info(("Coded by quele | Muhammed"));
    }

    public String getPrefix() {
        return prefix;
    }

    public static ProxySystem getProxySystem() {
        return proxySystem;
    }
}
