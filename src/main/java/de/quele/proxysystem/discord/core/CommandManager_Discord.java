/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord.core;

import de.quele.proxysystem.discord.DiscordManager;
import de.quele.proxysystem.discord.utils.Config_Discord;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class CommandManager_Discord extends ListenerAdapter {

    private static ArrayList<Command_Discord> commands = new ArrayList<Command_Discord>();

    public void load() {
        DiscordManager.jda.addEventListener(this);
        commands.forEach(obj -> {DiscordManager.jda.addEventListener(obj);});
    }


    public static void addCommand(Object obj) {
        commands.add((Command_Discord) obj);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] message = event.getMessage().getContentRaw().trim().split(" ");
        String invoke = message[0];
        String call = invoke.replace(Config_Discord.INVOKE, "");
        String[] string = message;

        commands.forEach(cmd -> {
            if (invoke.startsWith(Config_Discord.INVOKE) && cmd.call().equalsIgnoreCase(call) && !event.getAuthor().isBot()) {
                cmd.execute(string, event);
            }
        });
    }
}
