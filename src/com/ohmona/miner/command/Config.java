package com.ohmona.miner.command;

import com.ohmona.miner.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class Config implements CommandExecutor {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) { plugin = MainPlugin; }
    public final File f  = new File(plugin.getDataFolder(), "config.txt");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 0) {
                p.sendMessage(ChatColor.RED + "command list : ...");
            }
            switch (args[0]) {
                case "height": {

                    try {
                        plugin.consol.sendMessage(ChatColor.GREEN + "config saved");
                        FileWriter writer = new FileWriter(f, false); // Map to File
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default: {
                    p.sendMessage(ChatColor.RED + "command list : ...");
                }
            }
        }
        return false;
    }

    public void makeFile(File f) {
        if (!f.exists() || !f.isFile()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
