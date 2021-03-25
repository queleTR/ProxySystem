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

public class MessageCommand extends Command {

    public MessageCommand() {
        super("msg");

    }
    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (strings.length > 1) {

                ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(strings[0]);
                if (player.hasPermission("command.use.msg")) {
                    if (targetPlayer == null) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDieser Spieler ist derzeit nicht auf dem Netzwerk§8!"));

                        // player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDer Spieler §e" + targetPlayer.getName() + "§cist nicht online §8!"));
                        return;
                    }
                    if ((player == targetPlayer)) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDa ist jemand wohl selbstverliebt :)"));
                    }else {
                        String msg = "";
                        for (int i = 1; i < strings.length; i++) {
                            msg = msg + " " + strings[i];
                        }


                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Ich §8» §c" + targetPlayer.getName() + "§r: " + msg));
                        targetPlayer.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c" + player.getName() + " §8» §7Ich§r:" + msg));
                    }
                    }else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDazu besitzt du keine Berechtigung§8!"));
                    return;
                }
            }
        }
    }
}
