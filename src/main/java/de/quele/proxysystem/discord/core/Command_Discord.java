/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.discord.core;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class Command_Discord extends ListenerAdapter {

    public abstract String call();
    public abstract String help();
    public abstract boolean execute(String[] strings, GuildMessageReceivedEvent event);

}
