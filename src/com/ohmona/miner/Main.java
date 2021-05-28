package com.ohmona.miner;

import com.ohmona.miner.command.Miner;
import com.ohmona.miner.event.Events;
import com.ohmona.miner.event.HeightSystem;
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
        HeightSystem.setPlugin(this);

        // getCommand("rule").setExecutor(new Commands());
        // getCommand("config").setExecutor(new Config());
        getCommand("min").setExecutor(new Miner());

        // Config config = new Config();
        // config.makeFile(config.f);

        Events events = new Events();
        HeightSystem heightSystem = new HeightSystem();
        getServer().getPluginManager().registerEvents(events, this);
        getServer().getPluginManager().registerEvents(heightSystem, this);

        Recipes recipes = new Recipes();

        getServer().addRecipe(recipes.cleanZoneRecipe);
        getServer().addRecipe(recipes.potionRecipe);
        getServer().addRecipe(recipes.potionRecipe1);
        getServer().addRecipe(recipes.potionRecipe2);
        getServer().addRecipe(recipes.helmetRecipe);
    }
    @Override
    public void onDisable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 비활성화 중 입니다.");
    }
}
