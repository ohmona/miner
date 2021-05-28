package com.ohmona.miner.command;

import com.ohmona.miner.Items;
import com.ohmona.miner.Main;
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
                        default: {
                            p.sendMessage(ChatColor.RED + "/min ...");
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
