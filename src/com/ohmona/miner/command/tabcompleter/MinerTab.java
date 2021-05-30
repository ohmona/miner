package com.ohmona.miner.command.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinerTab implements TabCompleter {

    private static final String[] COMMANDS = { "item", "config" };
    private static final String[] COMMANDS_ITEM = { "potionA", "potionS", "potionL", "helmet", "cleanzone" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 1) {
                // create new array
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS));
                Collections.sort(completions);
                return completions;
            }
            else if(args.length == 2 && args[0].equals("item")) {
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS_ITEM));
                Collections.sort(completions);
                return completions;
            }
            else {
                return null;
            }
        }
        return null;
    }
}
