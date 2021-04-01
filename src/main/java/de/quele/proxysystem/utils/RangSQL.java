package de.quele.proxysystem.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RangSQL {

    public static boolean playerExists(ProxiedPlayer player) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + player.getUniqueId().toString() + "'");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("UUID") != null;
            }
        } catch(SQLException ignored) {

        }
        return false;
    }

    public static void registerPlayer(ProxiedPlayer player) {
        if(!(playerExists(player))) {
            MySQL.update("INSERT INTO rang(UUID, Rang, DiscordId) VALUES ('" + player.getUniqueId().toString() + "', 'Spieler', '')");
        } else {
            ProxyServer.getInstance().getConsole().sendMessage("User existiert bereits");
        }
    }

    public static void setDiscordId(String uuid, String id) {
       de.hype.perms.utils.MySQL.update("UPDATE rang SET DiscordId= '" + id + "' WHERE UUID= '" + uuid + "'");
    }

    public static Rang getRang(String uuid) {
        try {
            ResultSet result = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert result != null;
            if (result.next()) {
                return Rang.valueOf(result.getString("Rang"));
            }
        } catch (SQLException ignored) {
            return Rang.Spieler;
        }
        return Rang.Spieler;
    }

    public static boolean playerExistsDc(String uuid) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM rang WHERE UUID= '" + uuid + "'");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("DiscordId") != null;
            }
        } catch(SQLException ignored) {

        }
        return false;
    }

    public static String getUUIDbyDCid(Long id) {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM rang WHERE DiscordId= '" + id + "'");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("UUID");
            }
        } catch(SQLException ignored) {
            return "Fehler";
        }
        return "Fehler";
    }

}
