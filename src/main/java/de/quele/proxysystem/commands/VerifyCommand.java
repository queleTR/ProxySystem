/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.proxy.hypedcbot.discord.DiscordManager;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.RangSQL;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Objects;

public class VerifyCommand extends Command {

    public VerifyCommand() {
        super("verify");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)commandSender;

            if (strings.length == 1) {
                RangSQL.setDiscordId(player.getUniqueId().toString(), strings[0]);
                Objects.requireNonNull(DiscordManager.jda.
                        getUserById(de.proxy.hypedcbot.discord.mysql.RangSQL.getDiscordId(player.getUniqueId().toString()))).openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Please write #verify").queue();
                });
            } else {
                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§c/verify <DiscordId>"));
            }
        }
    }
}
