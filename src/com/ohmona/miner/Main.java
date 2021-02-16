package com.ohmona.miner;

import com.ohmona.miner.event.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public ConsoleCommandSender consol = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 활성화 중 입니다.");
        Events.setPlugin(this);

        Events events = new Events();
        getServer().getPluginManager().registerEvents(events, this);
    }
    @Override
    public void onDisable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 비활성화 중 입니다.");
    }
}
