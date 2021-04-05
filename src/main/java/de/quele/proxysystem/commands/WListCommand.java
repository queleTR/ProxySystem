/*
 * Created by quele | Muhammed
 * Copyright (C) all rights reserved.
 * Website: http://quele.live
 */

package de.quele.proxysystem.commands;

import de.hype.api.HypeAPI;
import de.hype.perms.utils.RangSQL;
import de.quele.proxysystem.ProxySystem;
import de.quele.proxysystem.utils.Constans;
import de.quele.proxysystem.utils.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class WListCommand extends Command {

    public WListCommand() {
        super("wlist", null, "whitelist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 2) {
            if(sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
                if(RangSQL.getRangId(player.getUniqueId().toString()) >= 7) {
                    if (target != null) {
                        if (args[0].equalsIgnoreCase("add")) {
                            if (Constans.getWhitelist().contains(target.getUniqueId().toString())) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cbereits §7auf der §aWhitelist§8!"));
                                return;
                            }
                            Constans.addPlayer(target.getUniqueId().toString());
                            player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + target.getName() + " §7wurde zur §aWhitelist §6hinzugefügt§8."));
                        } else if (args[0].equalsIgnoreCase("rem")) {
                            if (!Constans.getWhitelist().contains(target.getUniqueId().toString())) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cnicht §7auf der §aWhitelist§8!"));
                                return;
                            }
                            Constans.removePlayer(target.getUniqueId().toString());
                            player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + target.getName() + " §7wurde von der §aWhitelist §6entfernt§8."));
                        } else {
                            if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
                            } else {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /wlist add/rem <Name>"));
                            }
                        }
                    } else {
                        UUID uuid = UUIDFetcher.getUUID(args[1]);
                        String playername = UUIDFetcher.getName(uuid);
                        if (args[0].equalsIgnoreCase("add")) {
                            if (Constans.getWhitelist().contains(uuid.toString())) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cbereits §7auf der §aWhitelist§8!"));
                                return;
                            }
                            Constans.addPlayer(uuid.toString());
                            player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + playername + " §7wurde zur §aWhitelist §6hinzugefügt§8."));
                        } else if (args[0].equalsIgnoreCase("rem")) {
                            if (!Constans.getWhitelist().contains(uuid.toString())) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cnicht §7auf der §aWhitelist§8!"));
                                return;
                            }
                            player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + playername + " §7wurde von der §aWhitelist §6entfernt§8."));
                        } else {
                            if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
                            } else {
                                player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /wlist add/rem <Name>"));
                            }
                        }
                    }
                } else {
                    ProxySystem.getProxySystem().getLanguageManager().sendMessage(player, "noperms");
                }
            } else {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[1]);
                if(target != null) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if(Constans.getWhitelist().contains(target.getUniqueId().toString())) {
                            sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cbereits §7auf der §aWhitelist§8!"));
                            return;
                        }
                        Constans.addPlayer(target.getUniqueId().toString());
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + target.getName() + " §7wurde zur §aWhitelist §6hinzugefügt§8."));
                    } else if (args[0].equalsIgnoreCase("rem")) {
                        if(!Constans.getWhitelist().contains(target.getUniqueId().toString())) {
                            sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cnicht §7auf der §aWhitelist§8!"));
                            return;
                        }
                        Constans.removePlayer(target.getUniqueId().toString());
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + target.getName() + " §7wurde von der §aWhitelist §6entfernt§8."));
                    } else {
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
                    }
                } else {
                    UUID uuid = UUIDFetcher.getUUID(args[1]);
                    String playername = UUIDFetcher.getName(uuid);
                    if (args[0].equalsIgnoreCase("add")) {
                        if(Constans.getWhitelist().contains(uuid.toString())) {
                            sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cbereits §7auf der §aWhitelist§8!"));
                            return;
                        }
                        Constans.addPlayer(uuid.toString());
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + playername + " §7wurde zur §aWhitelist §6hinzugefügt§8."));
                    } else if (args[0].equalsIgnoreCase("rem")) {
                        if(!Constans.getWhitelist().contains(uuid.toString())) {
                            sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler ist §cnicht §7auf der §aWhitelist§8!"));
                            return;
                        }
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§7Der Spieler §6" + playername + " §7wurde von der §aWhitelist §6entfernt§8."));
                    } else {
                        sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
                    }
                }
            }
        } else {
            if(sender instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) sender;

                if (HypeAPI.getInstance().getPlayerManager().getLanguage(player) == 0) {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
                } else {
                    player.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cNutze /wlist add/rem <Name>"));
                }
            } else {
                sender.sendMessage(new TextComponent(ProxySystem.getProxySystem().getPrefix() + "§cUse /wlist add/rem <name>"));
            }
        }
    }


}
