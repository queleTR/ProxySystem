/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.api.HypeAPI;
import de.hype.api.utils.LanguageManager;
import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.Rang;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BroadcastCommand extends Command {

    public BroadcastCommand() {
        super("broadcast", null, "bc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;

            if (RangSQL.getRangId(player.getUniqueId().toString()) < 7) {
                ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
                return;
            }
            if (strings.length > 0) {
                    StringBuilder message = new StringBuilder();
                    for (String string : strings) {
                        message.append(string).append(" ");

                    }

                    message = new StringBuilder(ChatColor.translateAlternateColorCodes('&', message.toString()));
                    for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                        ProxySystem.getProxySystem().getLanguageManager().sendMessageCustom(all, "%prefix% " + message);
                    }


            } else {
                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /broadcast <message>"));
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /broadcast <Nachricht>"));
                }
            }
        }
    }
}