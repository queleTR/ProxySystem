/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem;

import de.quele.proxysystem.commands.*;
import de.quele.proxysystem.discord.DiscordManager;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.Map;
import java.util.UUID;


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

        //DiscordManager.buildJDA();
    }
    private void initListeners() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
    }

    private void initCommands() {
    PluginManager pluginManager = this.getProxy().getPluginManager();
    pluginManager.registerCommand(this, new WhereamiCommand());
    pluginManager.registerCommand(this, new WhereisCommand());
    pluginManager.registerCommand(this, new GlobalChatClearCommand());
    pluginManager.registerCommand(this, new BroadcastCommand());
    pluginManager.registerCommand(this, new MessageCommand());
    pluginManager.registerCommand(this, new OkCommand());
        pluginManager.registerCommand(this, new PingCommand());

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
