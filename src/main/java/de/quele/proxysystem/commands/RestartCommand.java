/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RestartCommand extends Command {

    public RestartCommand() {
        super("restart");
    }
    private int count = 61;

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (!(RangSQL.getRangId(player.getUniqueId().toString()) < 8)) {
                if (strings.length == 0) {
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            if (count <= 61) {
                                count--;
                            }
                            switch(count) {
                                case 60:
                                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix()
                                            + "\n\n§7Der Server startet in §6einer Minute §7neu§8.\n\n"));
                                    break;
                                case 59: case 30: case 15: case 10: case 5: case 4: case 3: case 2:
                                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix()
                                            + "\n\n§7Der Server startet in §630 Sekunden §7neu§8.\n\n"));
                                    break;
                                case 1:
                                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix()
                                            + "\n\n§7Der Server startet in §6" + count + " Sekunde §7neu§8.\n\n"));
                                    break;
                                case 0:
                                    ProxyServer.getInstance().broadcast(new TextComponent(ProxySystem.getProxySystem().getPrefix()
                                            + "\n\n§7Der Server startet sich nun neu§8.\n\n"));
                                    timer.cancel();
                                    ProxyServer.getInstance().stop(
                                            "§8§m------------------" +
                                                    "\n §6§lHypeTimeEU §7wird neugestartet§8." +
                                                    "\n§8§m------------------");
                                    break;
                            }
                        }
                    };
                    timer.schedule(task, 0, 1000);
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c/restart"));
                }
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Nicht genug §cRechte§8."));
            }
        }
    }
}