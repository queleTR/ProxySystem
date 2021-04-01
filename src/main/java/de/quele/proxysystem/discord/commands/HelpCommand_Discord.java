/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord.commands;

import de.quele.proxysystem.discord.core.Command_Discord;
import de.quele.proxysystem.discord.utils.Colors_Discord;
import de.quele.proxysystem.discord.utils.Config_Discord;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

public class HelpCommand_Discord extends Command_Discord {

    @Override
    public String call() {
        return "help";

    }


    @Override
    public String help() {
        return "help";
    }

    @Override
    public boolean execute(String[] strings, GuildMessageReceivedEvent event) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(event.getAuthor().getName(), null, event.getAuthor().getAvatarUrl())
                .setColor(Colors_Discord.blue)
                .setFooter("This message will automatically delete after 30 seconds.", event.getAuthor().getAvatarUrl());

        embedBuilder.addField("User Commands",
                MessageFormat.format(
                "{0}help » shows you the help\n"
                        +"{0}ping » Pong!", Config_Discord.INVOKE)
        , false);

        event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
        return false;
    }

}
