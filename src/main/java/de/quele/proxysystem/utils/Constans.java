/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
