/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.api.HypeAPI;
import de.hype.perms.HypePermsBungee;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.Datas;
import de.quele.proxysystem.utils.ImageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JoinMeCommand extends Command {
    public JoinMeCommand() {
        super("joinme");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(RangSQL.getRangId(player.getUniqueId().toString()) > 3) {
                if (args.length == 0) {
                    if (player.getServer().getInfo().getName().contains("Lobby")) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Du darfst diesen Befehl §cnicht §7auf der §6Lobby §7ausführen§8."));
                        return;
                    }
                    if (Datas.joinme.containsKey(player)) {
                        if (Datas.joinme.get(player) < System.currentTimeMillis()) {
                            for (ProxiedPlayer allPlayers : ProxyServer.getInstance().getPlayers()) {
                                String rangprefix = RangSQL.getRang(player.getUniqueId().toString()).getPrefix();
                                String joinMeMessageDe = " §6" + rangprefix + player.getName() + " §7spielt auf §a" + player.getServer().getInfo().getName();
                                String joinMeMessageEn = " §6" + rangprefix + player.getName() + " §7plays on §a" + player.getServer().getInfo().getName();
                                TextComponent clickableJoinDe = new TextComponent(" §7Klicke §ahier §7um den §6Server §7zu §abetreten§8.");
                                TextComponent clickableJoinEn = new TextComponent(" §7Click §ahere §7to §ajoin §7the §6server§8.");
                                clickableJoinDe.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                                clickableJoinEn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                                try {
                                    if(HypeAPI.getInstance().getPlayerManager().getLanguage(allPlayers) == 0) {
                                        ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessageEn), clickableJoinEn);
                                    } else {
                                        ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessageDe), clickableJoinDe);
                                    }
                                    Datas.joinme.put(player, System.currentTimeMillis() + 1000 * 60 * 30);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            if(HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                                player.sendMessage(new TextComponent("§7You can't create your next §5JoinMe §7until §6" + formatSeconds(Datas.joinme.get(player)) + "§8."));
                            } else {
                                player.sendMessage(new TextComponent("§7Du kannst erst um §6" + formatSeconds(Datas.joinme.get(player)) + " Uhr §7wieder ein §5JoinMe §7erstellen§8."));
                            }
                        }
                    } else {
                        for (ProxiedPlayer allPlayers : ProxyServer.getInstance().getPlayers()) {
                            String rangprefix = RangSQL.getRang(player.getUniqueId().toString()).getPrefix();
                            String joinMeMessageDe = " §6" + rangprefix + player.getName() + " §7spielt auf §a" + player.getServer().getInfo().getName();
                            String joinMeMessageEn = " §6" + rangprefix + player.getName() + " §7plays on §a" + player.getServer().getInfo().getName();
                            TextComponent clickableJoinDe = new TextComponent(" §7Klicke §ahier §7um den §6Server §7zu §abetreten§8.");
                            TextComponent clickableJoinEn = new TextComponent(" §7Click §ahere §7to §ajoin §7the §6server§8.");
                            clickableJoinDe.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                            clickableJoinEn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hWeI7w42l " + player.getName()));
                            try {
                                if(HypeAPI.getInstance().getPlayerManager().getLanguage(allPlayers) == 0) {
                                    ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessageEn), clickableJoinEn);
                                } else {
                                    ImageUtil.createImageUtil().sendImageMessage(allPlayers, player.getUniqueId().toString(), new TextComponent(joinMeMessageDe), clickableJoinDe);
                                }
                                Datas.joinme.put(player, System.currentTimeMillis() + 1000 * 60 * 30);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else {
                    if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /joinme"));
                    } else {
                        player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /joinme"));
                    }
                }
            } else {
                ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
            }
        }
    }

    String formatSeconds(long millis)
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return "" + format.format(new Date(millis));
    }
}
