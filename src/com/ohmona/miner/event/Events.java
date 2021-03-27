package com.ohmona.miner.event;

import com.ohmona.miner.Main;
import com.ohmona.miner.command.Commands;
import com.ohmona.miner.event.spawn.CreateSpawn;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.UUID;

public class Events implements Listener {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) {
        plugin = MainPlugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World w = p.getWorld();
        World overW = Bukkit.getServer().getWorld("world");
        Location loc = new Location(w, 0.5, 20, 0.5);
        Location wSpawn = w.getSpawnLocation();

        CreateSpawn createSpawn = new CreateSpawn();
        Commands cmd = new Commands();

        ItemStack helmet = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta helmetmeta = helmet.getItemMeta();
        helmetmeta.setDisplayName(ChatColor.AQUA + "default helmet");
        helmet.setItemMeta(helmetmeta);

        wSpawn.add(0, 20, 0);
        w.setGameRule(GameRule.RANDOM_TICK_SPEED, 5);
        w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        w.setTime(20000);
        w.setDifficulty(Difficulty.HARD);

        if(!p.hasPlayedBefore()) {
            p.teleport(loc);

            createSpawn.createDefaultspawnarea(overW);

            p.getInventory().addItem(new ItemStack(Material.OAK_SAPLING, 1));
            p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
            p.getInventory().addItem(helmet);

            cmd.giveGuidBook(p);
        }
        else if(p.hasPlayedBefore()) {

            createSpawn.createspawnarea(overW);
        }
    }
    // compass
    /*@EventHandler
    public void onItemHeld(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        Inventory inv = p.getInventory();
        ItemStack item = inv.getItem(e.getNewSlot());
        int Xaxis = (int)loc.getX();
        int Yaxis = (int)loc.getY();
        int Zaxis = (int)loc.getZ();

        if(item.getType().equals(Material.COMPASS)) {
            String message = ChatColor.RED + "" + ChatColor.BOLD + "X : " + Xaxis + ChatColor.YELLOW + "" + ChatColor.BOLD + " Y : " + Yaxis + ChatColor.BLUE + "" + ChatColor.BOLD +" Z : " + Zaxis;
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
        else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(""));
        }
    }*/

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        World w = e.getPlayer().getWorld();
        World world = Bukkit.getServer().getWorld("world");
        Location spawn = w.getSpawnLocation();
        Location loc = new Location(world, 0.5, 20, 0.5);

        if(!e.isBedSpawn() && !e.isAnchorSpawn()) {
            //p.sendMessage("respawn");
            e.setRespawnLocation(loc);
            p.teleport(loc);
        }
        else if(e.isBedSpawn()) {
        }
        else if(e.isAnchorSpawn()) {
        }
    }

    @EventHandler
    public void onHelmetPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();
        //p.sendMessage("hi");
        if(b.getType().equals(Material.CARVED_PUMPKIN)) {
            e.setCancelled(true);
        }
    }

    // 문자열이 숫자로 이루어져 있는지 확인
    public boolean isNumeric(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public String tickToMin(int second) {
        second /= 20;
        int min;
        int sec;
        min = second / 60;
        sec = second % 60;

        String result = "" + min + ":" + sec;

        return result;
    }
}
