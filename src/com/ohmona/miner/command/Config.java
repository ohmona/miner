package com.ohmona.miner.command;

import com.ohmona.miner.event.HeightSystem;
import com.ohmona.miner.Main;

import java.io.File;
import java.io.IOException;

public class Config {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) { plugin = MainPlugin; }

    public static File f;

    public Config() {
        plugin.saveConfig();

        f = new File(plugin.getDataFolder(), "config.yml");

        if(f.length() == 0) {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }

        if(!plugin.getConfig().contains("height")) {
            plugin.getConfig().set("height", 35);
        }
    }

    public static void setHeightLimit(int limit) {
        new HeightSystem().setHeightLimit(limit);
    }

    public static void setDefaultHeightLimit() {
        String dummyHeight = plugin.getConfig().getString("height");
        int newLimit = Integer.parseInt(dummyHeight);
        new HeightSystem().setHeightLimit(newLimit);
        plugin.saveConfig();
    }

    public static int getDefaultHeightLimit() {
        String dummyHeight = plugin.getConfig().getString("height");
        int newLimit = Integer.parseInt(dummyHeight);
        return newLimit;
    }

    public static void changeDefaultHeightLimit(int limit) {
        plugin.getConfig().options().configuration().set("height", limit);
        plugin.saveConfig();
    }


    public void makeFile(File f) {
        if (!f.exists() || !f.isFile()) {
            try {
                System.out.println("File generated");
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
