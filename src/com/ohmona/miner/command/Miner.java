package com.ohmona.miner.command;

import com.ohmona.miner.Items;
import com.ohmona.miner.Main;
import com.ohmona.miner.event.Events;
import com.ohmona.miner.event.HeightSystem;
import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Miner implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Items items = new Items();

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.isOp()) {
                if (args.length > 1) {
                    switch (args[0]) {
                        case "item": {
                            switch (args[1]) {
                                case "potionA": {
                                    p.getInventory().addItem(items.potion);
                                    break;
                                }
                                case "potionS": {
                                    p.getInventory().addItem(items.potion1);
                                    break;
                                }
                                case "potionL": {
                                    p.getInventory().addItem(items.potion2);
                                    break;
                                }
                                case "helmet": {
                                    p.getInventory().addItem(items.helmet);
                                    break;
                                }
                                case "cleanzone": {
                                    p.getInventory().addItem(items.cleanZone);
                                    break;
                                }
                                default: {
                                    p.sendMessage(ChatColor.RED + "type any item which you want to get");
                                    break;
                                }
                            }
                            break;
                        }
                        case "config": {
                            switch (args[1]) {
                                case "height": {

                                    if(new Events().isNumeric(args[2])) {
                                        String args2 = args[2];
                                        int limit = Integer.parseInt(args2);
                                        if(limit <= 0) {
                                            new Config().setDefaultHeightLimit();
                                            p.sendMessage(ChatColor.GOLD + "Limit successfully changed!" + ChatColor.RED + " New limit : " + ChatColor.WHITE + new Config().getDefaultHeightLimit());
                                            break;
                                        }
                                        new Config().setHeightLimit(limit);
                                        p.sendMessage(ChatColor.GOLD + "Limit successfully changed!" + ChatColor.RED + " New limit : " + ChatColor.WHITE + args2);
                                        break;
                                    }
                                    else if(args[2].equals("default")) {
                                        new Config().setDefaultHeightLimit();
                                        p.sendMessage(ChatColor.GOLD + "Limit successfully changed!" + ChatColor.RED + " New limit : " + ChatColor.WHITE + new Config().getDefaultHeightLimit());
                                        break;
                                    }
                                    System.out.println("Somthing went wrong");
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }
                            break;
                        }
                        default: {
                            p.sendMessage(ChatColor.RED + "/min {min, config, ...} ...");
                            break;
                        }
                    }
                    return true;
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        else {
            System.out.println("please use as player");
            return true;
        }
    }
}
