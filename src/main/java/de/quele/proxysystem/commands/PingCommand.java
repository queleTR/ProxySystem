/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");

    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (RangSQL.getRangId(player.getUniqueId().toString()) > 0) {
                int ping = player.getPing();
                if (strings.length == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Dein aktueller Ping beträgt§8: §e" + ping + "§7ms"));
                } else {
                    commandSender.sendMessage("§7Der Befehl wird wie folgt genutzt: §a/ping");
                }
            }else {
                player.sendMessage(HypePermsBungee.getInstance().getPrefix() + "§7Nicht genug §cRechte§8.");
            }
        }
    }
}