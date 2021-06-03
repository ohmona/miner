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
    private static final String[] COMMANDS_CONFIG = { "height" };
    private static final String[] COMMANDS_CONFIG_HEIGHT = { "default", "<number>" };
    private static final String[] COMMANDS_CONFIG_HEIGHT_DEFAULT = { "<number>" };
    private static final String[] EMPTY = { "" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 1) {
                // create new array
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS));
                Collections.sort(completions);
                return completions;
            }
            else if(args.length == 2
                    && args[0].equals("item")) {
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS_ITEM));
                Collections.sort(completions);
                return completions;
            }
            else if(args.length == 2
                    && args[0].equals("config")) {
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS_CONFIG));
                Collections.sort(completions);
                return completions;
            }
            else if(args.length == 3
                    && args[0].equals("config") && args[1].equals("height")) {
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS_CONFIG_HEIGHT));
                Collections.sort(completions);
                return completions;
            }
            else if(args.length == 4
                && args[0].equals("config") && args[1].equals("height") && args[2].equals("default")) {
                final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS_CONFIG_HEIGHT_DEFAULT));
                Collections.sort(completions);
                return completions;
            }
            else {
                final List<String> completions = new ArrayList<>(Arrays.asList(EMPTY));
                Collections.sort(completions);
                return completions;
            }
        }
        return null;
    }
}
