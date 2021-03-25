package de.quele.proxysystem;

import de.quele.proxysystem.commands.GlobalChatClearCommand;
import de.quele.proxysystem.commands.WhereamiCommand;
import de.quele.proxysystem.commands.WhereisCommand;
import de.quele.proxysystem.discord.DiscordManager;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.JDAImpl;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import javax.security.auth.login.LoginException;


public class ProxySystem extends Plugin {

    private static ProxySystem proxySystem;
    private final String prefix = "§3Proxy §8» §7";

    public static JDA jda;
    public static String token;

    @SneakyThrows
    @Override
    public void onEnable() {
        proxySystem = this;

        this.initListeners();
        this.initCommands();

        this.getLogger().info("Plugin successfully activated!");
        this.getLogger().info(("Coded by quele"));

        DiscordManager.buildJDA();
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
        this.getLogger().info(("Coded by quele"));
    }

    public String getPrefix() {
        return prefix;
    }

    public static ProxySystem getProxySystem() {
        return proxySystem;
    }

}
