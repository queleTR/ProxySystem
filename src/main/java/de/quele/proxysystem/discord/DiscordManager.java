/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord;

import de.quele.proxysystem.ProxySystem;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordManager {

    public static JDA jda;
    public static String token = "NzM4NjgzMDAzODY5NDYyNTgw.XyPeKA.uON-cCyhkV9mDxzYjeerxPTS29s";
    public static String prefix = "-";

    public static void main(String[] args) {
        buildJDA();
    }

    public static void buildJDA() {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken("NzM4NjgzMDAzODY5NDYyNTgw.XyPeKA.uON-cCyhkV9mDxzYjeerxPTS29s").build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}