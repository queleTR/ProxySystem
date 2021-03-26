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

public class ServerConnectCommand extends Command
{
    public ServerConnectCommand() {
        super("verbinden478474878547854");
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            final String server = args[0];
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            final ServerInfo info = ProxyServer.getInstance().getServerInfo(server);
            p.connect(info);
        }
    }
}
