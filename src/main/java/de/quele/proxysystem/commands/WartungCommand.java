/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.api.HypeAPI;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.Constans;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WartungCommand extends Command {

    public WartungCommand() {
        super("wartung");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 0) {
            if(sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;
                if(RangSQL.getRangId(player.getUniqueId().toString()) > 6) {
                    if(Constans.getMaintenance()) {
                        Constans.setMaintenance(false);
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Maintenance §cdisabled"));
                    } else {
                        Constans.setMaintenance(true);
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Maintenance §aactivated"));
                    }
                } else {
                    ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
                }
            } else {
                if(Constans.getMaintenance()) {
                    Constans.setMaintenance(false);
                    sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Maintenance §cdisabled"));
                } else {
                    Constans.setMaintenance(true);
                    sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Maintenance §aactivated"));
                }
            }
        }else {
            if(sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;

                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wartung"));
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /wartung"));
                }
            } else {
                sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse wartung"));
            }
        }
    }
}
