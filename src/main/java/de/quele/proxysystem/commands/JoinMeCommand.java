/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.Datas;
import de.quele.proxysystem.utils.ImageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;


public class JoinMeCommand extends Command {
    public JoinMeCommand() {
        super("joinme");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(RangSQL.getRangId(player.getUniqueId().toString()) > 3) {
                if(player.getServer().getInfo().getName().contains("Lobby")) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Du darfst diesen Befehl §cnicht §7auf der §6Lobby §7ausführen§8."));
                    return;
                }
                if(Datas.joinme.containsKey(player)) {
                    if(Datas.joinme.get(player) < System.currentTimeMillis()) {
                        for (ProxiedPlayer allPlayers : ProxyServer.getInstance().getPlayers()) {
                            String rangprefix = RangSQL.getRang(player.getUniqueId().toString()).getPrefix();
                            String joinMeMessage = " §6" + rangprefix + player.getName() + " §7spielt auf §a" + player.getServer().getInfo().getName();
                            TextComponent clickableJoin = new TextComponent(" §7Klicke §ahier §7um den §6Server §7zu §abetreten§8.");
                            clickableJoin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                            try {
                                ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessage), clickableJoin);
                                Datas.joinme.put(player, System.currentTimeMillis() + 1000 * 60 * 30);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        player.sendMessage(new TextComponent("§7Bitte warte §6" +
                                formatSeconds(System.currentTimeMillis() - Datas.joinme.get(player))
                                + " §7bis du erneut einen §6JoinMe §7erstellen kannst"));
                    }
                } else {
                    for (ProxiedPlayer allPlayers : ProxyServer.getInstance().getPlayers()) {
                        String rangprefix = RangSQL.getRang(player.getUniqueId().toString()).getPrefix();
                        String joinMeMessage = " §6" + rangprefix + player.getName() + " §7spielt auf §a" + player.getServer().getInfo().getName();
                        TextComponent clickableJoin = new TextComponent(" §7Klicke §ahier §7um den §6Server §7zu §abetreten§8.");
                        clickableJoin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                        try {
                            ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessage), clickableJoin);
                            Datas.joinme.put(player, System.currentTimeMillis() + 1000 * 60 * 30);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Keine Rechte§8."));
            }
        }
    }

    String formatSeconds(long millis)
    {
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds).replace("-", "");
    }
}
