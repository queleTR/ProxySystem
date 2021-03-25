package de.quele.proxysystem.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class DiscordManager {

    public static JDA jda;
    public static String token;
    public static String prefix = "-";

    public static void main(String[] args) {
        token = "NzM4NjgzMDAzODY5NDYyNTgw.XyPeKA.uON-cCyhkV9mDxzYjeerxPTS29s";
        buildJDA();
    }


    public static void buildJDA() {
        try {
            jda = JDABuilder.createDefault(token).build();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        jda.getPresence().setPresence(Activity.listening("nervige Musik"), true);
    }

}