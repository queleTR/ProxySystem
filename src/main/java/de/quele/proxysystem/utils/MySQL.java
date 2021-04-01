package de.quele.proxysystem.utils;


import de.quele.proxysystem.ProxySystem;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.sql.*;

public class MySQL {

    public static Connection connection;
    public static String host, username, password, database;

    public MySQL(String host, String username, String password, String database) {
        MySQL.host = host;
        MySQL.username = username;
        MySQL.password = password;
        MySQL.database = database;
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true&useSSL=false", username, password);
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "MySQL Connection Successfully"));

        } catch (SQLException ignored) {
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "MySQL Connection Error"));
        }
    }

    public static void createTable() {
        update("CREATE TABLE IF NOT EXISTS bungee_datas (maintenance BOOLEAN NOT NULL, whitelist VARCHAR(64000) NOT NULL);");

        if(!defaultValueSet()) {
            MySQL.update("INSERT INTO bungee_datas(maintenance, whitelist) VALUES ('0', '')");
        }
    }

    public static boolean defaultValueSet() {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM bungee_datas");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getString("maintenance") != null;
            }
        } catch(SQLException ignored) {

        }
        return false;
    }

    public static ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isConnected() {
        return connection != null;
    }

    public static void update(String qry) {
        if (isConnected()) {
            try {
                Statement st = connection.createStatement();
                st.executeUpdate(qry);
                st.close();
            } catch (SQLException e) {
                connect();
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String qry) {
        if (isConnected()) {
            PreparedStatement ps;
            try {
                ps = connection.prepareStatement(qry);
                return ps;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static Object get(String select, String database) {

        ResultSet rs = getResult("SELECT " + select + " FROM " + database + "");
        try {
            assert rs != null;
            if (rs.next()) {
                return rs.getObject(select);
            }
        } catch (SQLException e) {
            return "ERROR";
        }

        ProxyServer.getInstance().getConsole().sendMessage("§cCould not load Data.");
        return "ERROR";
    }

    public static Object get(String select, String database, String where, String whereresult) {

        ResultSet rs = getResult("SELECT " + select + " FROM " + database + " WHERE " + where + "='" + whereresult + "'");
        try {
            assert rs != null;
            if (rs.next()) {
                return rs.getObject(select);
            }
        } catch (SQLException e) {
            return "ERROR";
        }

        ProxyServer.getInstance().getConsole().sendMessage("§cCould not load Data.");
        return "ERROR";
    }

}
