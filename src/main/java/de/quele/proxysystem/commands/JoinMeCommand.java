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
        super("joinme");
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage("§7Der JoinMe Befehl wird wie folgt genuzt: §a/joinme");
        }
        else if (sender.hasPermission("command.use.joinme")) {
            final Iterator psender = ProxyServer.getInstance().getPlayers().iterator();
            final ProxiedPlayer ps = (ProxiedPlayer) psender.next();
            final Iterator pl = ProxyServer.getInstance().getPlayers().iterator();
            final ProxiedPlayer p = (ProxiedPlayer) pl.next();
            final String server = p.getServer().getInfo().getName();
            final TextComponent msg = new TextComponent(String.valueOf("§cZum nachjoinen §4HIER §cklicken"));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlick mich um den Server zu betreten").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/verbinden478474878547854 " + p.getServer().getInfo().getName()));
            ProxyServer.getInstance().broadcast("§7§k==================================");
            ProxyServer.getInstance().broadcast("");
            ProxyServer.getInstance().broadcast("§a" + p.getName() + " §7spielt auf §a" + server);
            ProxyServer.getInstance().broadcast((BaseComponent)msg);
            ProxyServer.getInstance().broadcast("");
            ProxyServer.getInstance().broadcast("§7§k==================================");
            sender.sendMessage("§aDu hast den JoinMe Befehl benutzt");
        }
        else {
            sender.sendMessage("§cDu darfst den JoinMe Befehl nicht nutzen");
        }
    }
}
