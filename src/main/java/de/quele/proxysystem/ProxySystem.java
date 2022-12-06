/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem;

import de.hype.api.utils.LanguageManager;
import de.quele.proxysystem.commands.*;
import de.quele.proxysystem.events.ServerEvents;
import de.quele.proxysystem.utils.MySQL;
import lombok.SneakyThrows;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;


public class ProxySystem extends Plugin {

    public static ProxySystem proxySystem;
    private LanguageManager languageManager;

    @SneakyThrows
    @Override
    public void onEnable() {
        proxySystem = this;

        boolean exists = getDataFolder().mkdirs();

        LanguageManager.pluginName = "ProxySystem";
        languageManager = new LanguageManager();
        languageManager.setPrefix("%scolor%HypeTime.EU §8»§7");

        this.initListeners();
        this.initCommands();
        this.getLogger().info("Plugin successfully activated!");
        this.getLogger().info("Coded by QuadrixYT");

        new MySQL("127.0.0.1", "root", "", "test");

        MySQL.connect();

        MySQL.createTable();

        languageManager.addMessage(0, "noperms", "%prefix% No §cpermissions§8.");
        languageManager.addMessage(1, "noperms", "%prefix% Keine §cRechte§8.");

        languageManager.addMessage(0, "notonline", "%prefix% The player is currently %scolor%not §7online§8.");
        languageManager.addMessage(1, "notonline", "%prefix% Der Spieler ist derzeit %scolor%nicht §7online§8.");

        languageManager.addMessage(0, "ip", "%prefix% The IP of %target% is %ip%§8.");
        languageManager.addMessage(1, "ip", "%prefix% Die IP von %target% lautet %ip%§8.");

    }

    private void initListeners() {
        PluginManager pluginManager = this.getProxy().getPluginManager();

        //Listener
        pluginManager.registerListener(this, new ServerEvents());
    }

    private void initCommands() {
        PluginManager pluginManager = this.getProxy().getPluginManager();
        //Commands
        pluginManager.registerCommand(this, new WhereamiCommand());
        pluginManager.registerCommand(this, new WhereisCommand());
        pluginManager.registerCommand(this, new GlobalChatClearCommand());
        pluginManager.registerCommand(this, new BroadcastCommand());
        pluginManager.registerCommand(this, new PingCommand());
        pluginManager.registerCommand(this, new JoinMeCommand());
        pluginManager.registerCommand(this, new ServerCommand());
        pluginManager.registerCommand(this, new ServerConnectCommand());
        pluginManager.registerCommand(this, new HelpCommand());
        pluginManager.registerCommand(this, new VerifyCommand());
        pluginManager.registerCommand(this, new WartungCommand());
        pluginManager.registerCommand(this, new WListCommand());
        pluginManager.registerCommand(this, new GetIPCommand());
        pluginManager.registerCommand(this, new TeamChatCommand());
        pluginManager.registerCommand(this, new RestartCommand());

    }

    @Override
    public void onDisable() {
        proxySystem = this;
        this.getLogger().info("Plugin successfully disabled!");
        this.getLogger().info(("Coded by QuadrixYT and quele"));
    }

    public String getPrefix() {
        return "§6Proxy §8» §7";
    }

    public static ProxySystem getProxySystem() {
        return proxySystem;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }
}
