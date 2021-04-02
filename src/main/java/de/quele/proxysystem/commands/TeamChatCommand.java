/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.perms.commands.RangCommand;
import de.hype.perms.utils.Rang;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


public class TeamChatCommand extends Command {


    public TeamChatCommand() {
        super("teamchat", null, "tc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (!(RangSQL.getRangId(player.getUniqueId().toString()) < 7)) {
            if (strings.length >= 1) {
                StringBuilder msg = new StringBuilder();
                for (int i = 0; i < strings.length; i++)
                    msg.append(strings[i]).append(" ");
                msg = new StringBuilder(ChatColor.translateAlternateColorCodes('&', msg.toString()));
                for (ProxiedPlayer player2 : ProxyServer.getInstance().getPlayers()) {
                    player2.sendMessage(new TextComponent("§cTeamChat §8| §7" + RangSQL.getRangName((player).getUniqueId().toString()) + " " + commandSender.getName()
                            + " §8» §7" + msg));
                }
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c/teamchat <Nachricht>"));
            }
            }else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Nicht genug §cRechte§8."));
                return;
            }
        }
    }
}