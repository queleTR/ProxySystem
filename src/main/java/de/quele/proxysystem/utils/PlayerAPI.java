/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PlayerAPI {

    public static boolean playerExists(ProxiedPlayer player) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM user WHERE UUID= '" + player.getUniqueId().toString() + "'");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
        } catch(SQLException ignored) {

        }
        return false;
    }

    public static void registerPlayer(ProxiedPlayer player) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM HH:mm");
        if(!(playerExists(player))) {
            MySQL.update("INSERT INTO user(Player, UUID, IPAdress, FirstJoin, LastJoin) VALUES" +
                    " ('" + player.getName() +
                    "', '" + player.getUniqueId().toString() +
                    "', '" + player.getSocketAddress().toString() +
                    "', '" + simpleDateFormat.format(System.currentTimeMillis()) +
                    "', '" + simpleDateFormat.format(System.currentTimeMillis()) + ")");
        } else {
            ProxyServer.getInstance().getConsole().sendMessage("User existiert bereits");
        }
    }

    public static void updateLastJoin(ProxiedPlayer player) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM HH:mm");
        MySQL.update("UPDATE user SET LastJoin = '"
                + simpleDateFormat.format(System.currentTimeMillis()) + "' WHERE UUID = '" + player.getUniqueId().toString() + "'");
    }

}
