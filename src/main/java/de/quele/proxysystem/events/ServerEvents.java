/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.events;

import de.quele.proxysystem.utils.Constans;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class ServerEvents implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        ServerPing ping = event.getResponse();
        ServerPing.Players players = ping.getPlayers();
        ServerPing.PlayerInfo playerInfo1 = new ServerPing.PlayerInfo("§8§m          §8[§6HypeTime§8]§8§m          ", UUID.randomUUID());
        ServerPing.PlayerInfo playerInfo2 = new ServerPing.PlayerInfo("§6Website§8: §aHypeTime.eu", UUID.randomUUID());
        ServerPing.PlayerInfo playerInfo3 = new ServerPing.PlayerInfo("§6TeamSpeak§8: §aHypeTime.eu", UUID.randomUUID());
        ServerPing.PlayerInfo playerInfo4 = new ServerPing.PlayerInfo("§6Forum§8: §aForum.HypeTime.EU", UUID.randomUUID());
        ServerPing.PlayerInfo playerInfo5 = new ServerPing.PlayerInfo("§8§m          §8[§6HypeTime§8]§8§m          ", UUID.randomUUID());
        ServerPing.PlayerInfo[] allPlayer = {playerInfo1, playerInfo2, playerInfo3, playerInfo4, playerInfo5};
        players.setSample(allPlayer);

        String percent = "5%";
        String completed = "§cJetzt verbinden §8»»»                                   §aWartungsarbeiten §6Fortschritt§8: §c" + percent;

        if(Constans.getMaintenance()) {
            ping.setVersion(new ServerPing.Protocol(completed, ping.getVersion().getProtocol() - 1));
            ping.setDescriptionComponent(new TextComponent("§8» §6§lHypeTimeEU §8| §7Fall into the Hype §aツ\n" +
                    "§8» §4Wir sind aktuell in §lWartungen §8! \n"));
        } else {
            ping.setVersion(new ServerPing.Protocol("HypeTime.EU", ping.getVersion().getProtocol()));
            ping.setDescriptionComponent(new TextComponent("§8» §6§lHypeTimeEU §8| §7Fall into the Hype §aツ\n" +
                    "§8» §aNews§8: RELEASE§8! \n"));
        }
    }
}
