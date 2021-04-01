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
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalChatClearCommand extends Command {

    public GlobalChatClearCommand() {
        super("globalchatclear", null, "gcc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (RangSQL.getRangId(player.getUniqueId().toString()) < 7) {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Nicht genug §cRechte§8."));
                return;
            }
            if ((strings.length < 1)) {
                for (int i = 0; i < 1000; i++) {
                    ProxyServer.getInstance().broadcast(new TextComponent(" "));
                }
                ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "Der Chat wurde von §e" + player.getName() + " §7geleert§8"));
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /gcc§8!"));
            }
        }
    }
}
