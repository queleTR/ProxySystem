/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord.commands;

import de.quele.proxysystem.discord.core.Command_Discord;
import de.quele.proxysystem.discord.utils.Config_Discord;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PingCommand_Discord extends Command_Discord {

    @Override
    public String call() {
        return "ping";
    }


    @Override
    public String help() {
        return Config_Discord.INVOKE + "ping";
    }


    @Override
    public boolean execute(String[] strings, GuildMessageReceivedEvent event) {

        event.getChannel().sendMessage("Pong! " + event.getAuthor().getAsMention()).queue();
        return false;
    }
}
