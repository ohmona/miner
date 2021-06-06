package com.ohmona.miner.command;

import com.ohmona.miner.Items;
import com.ohmona.miner.event.Events;
import com.ohmona.miner.test.Message;
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

                                    if(Events.isNumeric(args[2])) {
                                        String args2 = args[2];
                                        int limit = Integer.parseInt(args2);
                                        if(limit <= 0) {
                                            Config.setDefaultHeightLimit();
                                            sendLimitChangeMessage(p, Config.getDefaultHeightLimit());
                                            break;
                                        }
                                        Config.setHeightLimit(limit);
                                        sendLimitChangeMessage(p, Integer.parseInt(args2));
                                        break;
                                    }
                                    else if(args[2].equals("default")) {
                                        if(args.length > 3) {
                                            if (Events.isNumeric(args[3])) {
                                                int limit = Integer.parseInt(args[3]);
                                                Config.changeDefaultHeightLimit(limit);
                                                sendDefaultLimitChangeMessage(p, limit);
                                                break;
                                            }
                                            else if(args[3].equals("<number>")) {
                                                sendHowToChangeDefaultLimit(p);
                                                break;
                                            }
                                        }
                                        Config.setDefaultHeightLimit();
                                        sendLimitChangeMessage(p, Config.getDefaultHeightLimit());
                                        break;
                                    }
                                    else if(args[2].equals("<number>")) {
                                        sendHowToChangeLimit(p);
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
                p.sendMessage(ChatColor.DARK_RED + "only operators are allowed to use this command!");
                return true;
            }
        }
        else {
            System.out.println("please use as player");
            return true;
        }
    }

    public void sendLimitChangeMessage(Player p, int newlimit) {
        p.sendMessage(ChatColor.GOLD + "Limit successfully changed!"
                + ChatColor.RED + " New limit : "
                + ChatColor.WHITE + newlimit);
    }
    public void sendDefaultLimitChangeMessage(Player p, int newlimit) {
        p.sendMessage(ChatColor.GOLD + "Default limit successfully changed!"
                + ChatColor.RED + " New default limit : "
                + ChatColor.WHITE + newlimit
                + "\n"
                + ChatColor.GOLD + "Please use command "
                + ChatColor.RED + "/min config height default "
                + "\n"
                + ChatColor.GOLD + "to change height limit into default");
    }
    public void sendHowToChangeLimit(Player p) {
        p.sendMessage(ChatColor.GOLD + "you can type Integer value which you would to set as limit" + "\n"
                + ChatColor.AQUA + "e.g. /min config height 30 "
                + ChatColor.GOLD + "Limit is now 30");
    }
    public void sendHowToChangeDefaultLimit(Player p) {
        p.sendMessage(Message.begin()
                .attach("you can type Integer value which you would \nto set as default limit, but you have to use\n", ChatColor.GOLD)
                .attach("/min config height default ", ChatColor.RED)
                .attach("again to change in game limit\n", ChatColor.GOLD)
                .attach("new value will be saved in config.yml file" + "\n")
                .attach("e.g. /min config height default 30 ", ChatColor.AQUA)
                .attach("Default Limit is now 30", ChatColor.GOLD).end());
    }

}
