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
        super("broadcast", "command.use.broadcast", "bc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;


                if (strings.length > 0) {
                    StringBuilder message = new StringBuilder();
                    for (String string : strings) {
                        message.append(string).append(" ");

                    }

                    message = new StringBuilder(ChatColor.translateAlternateColorCodes('&', message.toString()));
                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix() + message));

                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c/broadcast <Nachricht>"));
                }
        }
    }
}