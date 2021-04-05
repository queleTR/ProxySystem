/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.api.HypeAPI;
import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerCommand extends Command {
    public ServerCommand() {
        super("serverconnect", null, "serverc");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (RangSQL.getRangId(player.getUniqueId().toString()) > 6) {
            if (strings.length == 1) {
                final String server = strings[0];
                final ProxiedPlayer p = (ProxiedPlayer) commandSender;
                final ServerInfo info = ProxyServer.getInstance().getServerInfo(server);
                p.connect(info);

            } else {
                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /serverconnect <server> or /serverc <server>"));
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /serverconnect <Server> oder /serverc <Server>"));
                }
            }
        }else {
            ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
        }
    }
}
