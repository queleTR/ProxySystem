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
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GetIPCommand extends Command {

    public GetIPCommand() {
        super("getip");
    }

    @Override
    public void execute (CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (!(RangSQL.getRangId(player.getUniqueId().toString()) < 7)) {
                if (strings.length == 1) {

                    ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(strings[0]);


                    if (targetPlayer == null) {
                        ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "notonline");
                        return;
                    }
                    if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "The IP address of §a" + targetPlayer.getName() + " §7is §e" + player.getSocketAddress()));
                    }else{
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "Die IP von §a" + targetPlayer.getName() + " §7lautet §e" + player.getSocketAddress()));
                    }
                } else {
                    if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /getip <name>"));
                    } else {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /getip <Name>"));
                    }
                }
            }else {
                ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
            }
        }
    }
}
