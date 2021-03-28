/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "command.use.help");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage("HELP");
        }else {
            commandSender.sendMessage("§4§klk §7Bitte schreibe §e/help");
        }
    }
}
