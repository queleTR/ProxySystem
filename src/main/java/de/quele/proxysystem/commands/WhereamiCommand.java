/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WhereamiCommand extends Command {

    public WhereamiCommand() {
        super("whereami", "command.use.whereami");

    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;
            if (strings.length == 1) {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "Du befindest dich derzeit auf§8: §e" + player.getServer().getInfo().getName()));
                }else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /whereami§8!"));
            }
        }
    }
}
