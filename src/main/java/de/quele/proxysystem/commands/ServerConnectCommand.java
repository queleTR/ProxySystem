/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerConnectCommand extends Command
{
    public ServerConnectCommand() {
        super("hWeI7w42l");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target != null) {
                if(target != player) {
                    player.connect(target.getServer().getInfo());
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Du kannst deinen eigenen §6JoinMe §7nicht benutzen"));
                }
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der §7Spieler §7ist nicht online!"));
            }
        }
    }
}
