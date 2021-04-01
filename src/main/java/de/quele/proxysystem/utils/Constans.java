/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.utils;

import com.google.common.collect.Lists;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constans {

    public static boolean getMaintenance() {
        try {
            ResultSet resultSet = MySQL.getResult("SELECT * FROM bungee_datas");

            assert resultSet != null;
            if(resultSet.next()) {
                return resultSet.getBoolean("maintenance");
            }
        } catch (SQLException ignored) {

        }
        return false;
    }

    public static void setMaintenance(boolean value) {
        if(value) {
            MySQL.update("UPDATE bungee_datas SET maintenance = '1'");
        } else {
            MySQL.update("UPDATE bungee_datas SET maintenance = '0'");
        }
    }

    public static String getWhitelistRaw() {
        return String.valueOf(MySQL.get("*", "bungee_datas"));
    }

    public static ArrayList<String> getWhitelist() {
        String whitelist = String.valueOf(MySQL.get("*", "bungee_datas"));
        ArrayList<String> toreturn = Lists.newArrayList();
        if(whitelist.isEmpty())
            return toreturn;
        String[] whiteList = whitelist.split(";");
        toreturn.addAll(Arrays.asList(whiteList));
        return toreturn;
    }

    public void addFriend(String uuid) {
        String whitelist = getWhitelistRaw();
        whitelist = whitelist + uuid + ";";

        MySQL.update("UPDATE bungee_datas SET whitelist='" + whitelist + "'");
    }

    public void removePlayer(String uuid) {
        String whitelist = getWhitelistRaw();
        whitelist = whitelist.replace(uuid + ";", "");

        MySQL.update("UPDATE bungee_datas SET whitelist='" + whitelist + "'");
    }

}
