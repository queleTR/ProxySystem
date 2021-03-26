/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerCommand extends Command
{
    public ServerCommand() {
        super("serverc");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        ProxiedPlayer player = (ProxiedPlayer)commandSender;
        if (player.hasPermission("command.use.serverc")) {
            if (strings.length == 1) {
                final String server = strings[0];
                final ProxiedPlayer p = (ProxiedPlayer)commandSender;
                final ServerInfo info = ProxyServer.getInstance().getServerInfo(server);
                p.connect(info);

            }
        }
    }
}
