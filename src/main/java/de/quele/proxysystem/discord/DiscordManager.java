/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord;

import de.quele.proxysystem.discord.commands.HelpCommand_Discord;
import de.quele.proxysystem.discord.commands.PingCommand_Discord;
import de.quele.proxysystem.discord.core.CommandManager_Discord;
import de.quele.proxysystem.discord.utils.Config_Discord;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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
            jda = new JDABuilder(AccountType.BOT).setToken(Config_Discord.TOKEN).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        jda.getPresence().setPresence(Activity.listening("nervige Musik"), true);

        new CommandManager_Discord().load();

        // Command register
        CommandManager_Discord.addCommand(new PingCommand_Discord());
        CommandManager_Discord.addCommand(new HelpCommand_Discord());
    }
}