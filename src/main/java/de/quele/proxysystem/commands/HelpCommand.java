/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import com.google.common.collect.Lists;
import de.hype.api.HypeAPI;
import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;
import java.util.Collections;

public class HelpCommand extends Command {

    public HelpCommand() {
        super( "help");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (RangSQL.getRangId(player.getUniqueId().toString()) > 0) {
            if (strings.length == 0) {
                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "HELP"));
                }else{
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "HILFE"));
                }
            } else {
                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /help"));
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /help"));
                }
            }
        } else {
            ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
        }
    }
}
