package com.ohmona.miner.event;

import com.ohmona.miner.Main;
import com.ohmona.miner.command.Commands;
import com.ohmona.miner.event.spawn.CreateSpawn;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
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

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        World w = e.getPlayer().getWorld();
        World world = Bukkit.getServer().getWorld("world");
        Location spawn = w.getSpawnLocation();
        Location loc = new Location(world, 0.5, 20, 0.5);

        if(!e.isBedSpawn() && !e.isAnchorSpawn()) {
            e.setRespawnLocation(loc);
            p.teleport(loc);
        }
        else if(e.isBedSpawn()) {
        }
        else if(e.isAnchorSpawn()) {
        }
    }

    @EventHandler
    public void disableEquipmentOfCleanZoneArmorStand(PlayerArmorStandManipulateEvent e) {
        ArmorStand armorStand = e.getRightClicked();

        if(armorStand.getName().equals("CleanZoneArmorStand")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void disableCleanZoneBlockRecipe(CraftItemEvent e) {
        ItemStack item = e.getCurrentItem();
        if(item.getType().equals(Material.LIME_CONCRETE_POWDER)) {
            if(!item.hasItemMeta()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onHelmetPlace(BlockPlaceEvent e) {
        Block b = e.getBlockPlaced();
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
