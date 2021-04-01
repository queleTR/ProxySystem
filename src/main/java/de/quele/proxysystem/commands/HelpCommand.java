/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super( "help");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (RangSQL.getRangId(player.getUniqueId().toString()) < 0) {
            if (strings.length == 0) {
                commandSender.sendMessage("HELP");
            } else {
                commandSender.sendMessage("§4§klk §7Bitte schreibe §e/help");
            }
        } else {
            player.sendMessage(HypePermsBungee.getInstance().getPrefix() + "§7Nicht genug §cRechte§8.");
        }
    }
}
