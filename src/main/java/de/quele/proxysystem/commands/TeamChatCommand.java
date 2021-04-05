/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import de.hype.api.HypeAPI;
import de.hype.perms.commands.RangCommand;
import de.hype.perms.utils.Rang;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.Translator;
import lombok.SneakyThrows;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;


public class TeamChatCommand extends Command {


    public TeamChatCommand() {
        super("teamchat", null, "tc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;

            if (!(RangSQL.getRangId(player.getUniqueId().toString()) < 7)) {
                if (strings.length >= 1) {
                    StringBuilder msg = new StringBuilder();
                    for (String string : strings) msg.append(string).append(" ");
                    msg = new StringBuilder(ChatColor.translateAlternateColorCodes('&', msg.toString()));
                    for (ProxiedPlayer player2 : ProxyServer.getInstance().getPlayers()) {
                        try {
                            player2.sendMessage(new TextComponent("§cTeamChat §8| §7" + RangSQL.getRang((player).getUniqueId().toString()).getPrefix() + " " + player.getName()
                                    + " §8» §7" + Translator.translate("de", "en", msg.toString())));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /teamchat <message> or /tc <message>"));
                    } else {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /teamchat <Nachricht> oder /tc <Nachricht>"));
                    }
                }
            }else {
                ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
            }
        }
    }
}