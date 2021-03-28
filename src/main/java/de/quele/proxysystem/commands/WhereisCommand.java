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

public class WhereisCommand extends Command {

    public WhereisCommand() {
        super("whereis", "command.use.whereis");

    }

    @Override
    public void execute (CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

//            if (player.hasPermission("command.use.whereis")) {
                    if (strings.length == 1) {


                        ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(strings[0]);


                        if (targetPlayer == null) {
                            player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDieser Spieler ist derzeit nicht auf dem Netzwerk§8!"));
                            return;
                        }

                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "Der Spieler §a" + targetPlayer.getName() + " §7befindet sich derzeit auf dem Server §e" + targetPlayer.getServer().getInfo().getName()));
                    }else {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /whereis <Name>§8!"));;
                }
//            }else {
//                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cDazu besitzt du keine Berechtigung§8!"));
//            }
        }
    }
}
