package com.ohmona.miner.command;

import com.ohmona.miner.Main;
import com.ohmona.miner.event.Events;
import com.ohmona.miner.event.HeightSystem;

import java.io.File;
import java.io.IOException;

public class Config {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) { plugin = MainPlugin; }

    public File f;

    public Config() {
        plugin.saveConfig();

        f = new File(plugin.getDataFolder(), "config.yml");

        if(f.length() == 0) {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
    }

    public void setHeightLimit(int limit) {
        new HeightSystem().setHeightLimit(limit);
    }

    public void setDefaultHeightLimit() {
        String dummyHeight = plugin.getConfig().getString("height");
        int newLimit = Integer.parseInt(dummyHeight);
        new HeightSystem().setHeightLimit(newLimit);
    }
    public int getDefaultHeightLimit() {
        String dummyHeight = plugin.getConfig().getString("height");
        int newLimit = Integer.parseInt(dummyHeight);
        return newLimit;
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
