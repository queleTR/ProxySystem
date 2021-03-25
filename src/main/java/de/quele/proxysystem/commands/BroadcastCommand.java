/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCommand extends Command {

    public BroadcastCommand() {
        super("bc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;

            if (strings.length > 0) {
                String message = "";
                for (int i = 0; i < strings.length; i++) {
                    message = message + strings[i] + " ";

                }

                message = ChatColor.translateAlternateColorCodes('&', message);
                ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix() +message));

            }else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c/broadcast <Nachricht>"));
            }
        }
    }
}