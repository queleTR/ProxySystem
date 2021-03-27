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
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalChatClearCommand extends Command {

    public GlobalChatClearCommand() {
        super("globalchatclear", "command.use.globalchatclear", "gcc");

    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (player.hasPermission("command.use.globalchatclear")) {
                if ((strings.length < 1)) {
                    for (int i = 0; i < 1000; i++) {
                        ProxyServer.getInstance().broadcast(new TextComponent(" "));
                    }
                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "Der Chat wurde von §e" + player.getName() + " §7geleert§8"));

                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /gcc§8!"));
                }
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDazu besitzt du keine Berechtigung§8!"));
            }

        }
    }
}
