/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Iterator;

public class JoinMeCommand extends Command {
    public JoinMeCommand() {
        super("joinme", "command.use.joinme");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 0) {
                final Iterator playersender = ProxyServer.getInstance().getPlayers().iterator();
                final ProxiedPlayer ps = (ProxiedPlayer) playersender.next();
                final Iterator player2 = ProxyServer.getInstance().getPlayers().iterator();
                final ProxiedPlayer p = (ProxiedPlayer) player2.next();
                final String server = p.getServer().getInfo().getName();
                final TextComponent msg = new TextComponent("§cZum nachjoinen §4HIER §cklicken");
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlick mich um den Server zu betreten").create()));
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/verbinden478474878547854 " + p.getServer().getInfo().getName()));
                ProxyServer.getInstance().broadcast("§7§k==================================");
                ProxyServer.getInstance().broadcast("");
                ProxyServer.getInstance().broadcast("§a" + p.getName() + " §7spielt auf §a" + server);
                ProxyServer.getInstance().broadcast(msg);
                ProxyServer.getInstance().broadcast("");
                ProxyServer.getInstance().broadcast("§7§k==================================");
                commandSender.sendMessage("§aDu hast den JoinMe Befehl benutzt");
        } else {
                commandSender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Befehl wird wie folgt genutzt: §a/joinme"));
        }
    }
}
