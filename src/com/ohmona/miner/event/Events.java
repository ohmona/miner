package com.ohmona.miner.event;

import com.ohmona.miner.Main;
import com.ohmona.miner.event.spawn.CreateSpawn;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) {
        plugin = MainPlugin;
    }

    final int durability = 1000;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        World w = p.getWorld();
        Location loc = new Location(w, 0.5, 20, 0.5);
        Location wSpawn = w.getSpawnLocation();

        CreateSpawn createSpawn = new CreateSpawn();

        ItemStack helmet = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta helmetmeta = helmet.getItemMeta();

        //debug
        helmetmeta.setDisplayName(ChatColor.GOLD + "helmet");
        helmet.setItemMeta(helmetmeta);

        wSpawn.add(0, 20, 0);

        if(!p.hasPlayedBefore()) {
            p.teleport(loc);
            e.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "new player joined");

            createSpawn.createDefaultspawnarea(w);

            p.getInventory().addItem(new ItemStack(Material.OAK_SAPLING, 1));
            p.getInventory().addItem(helmet);
        }
        else if(p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD +"someone joined");
            p.getInventory().addItem(helmet);

            createSpawn.createspawnarea(w);
            // DEBUG ONLY
            // p.getInventory().addItem(new ItemStack(Material.OAK_SAPLING, 1));
            // p.getInventory().addItem(new ItemStack(Material.CARVED_PUMPKIN, 20));
        }
    }

    @EventHandler
    public void onPlayerIsOverY35(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = e.getPlayer().getLocation();

        if(loc.getY() > 35) {
            try {
                // helmet system
                if (p.getInventory().getHelmet().getType().equals(Material.CARVED_PUMPKIN)) {
                    ItemStack item = p.getInventory().getHelmet();
                    ItemMeta meta = item.getItemMeta();
                    //헬멧과 관련한 이벤트에서 이름을 정해야함
                    //meta.setLocalizedName("100");

                    // remove debuff
                    p.removePotionEffect(PotionEffectType.WITHER);

                    p.removePotionEffect(PotionEffectType.SLOW);
                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                    p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
                    // title
                    p.sendTitle("", ChatColor.GRAY + "warning", 0, 7000, 0);

                    // Default helmet
                    if(meta.getDisplayName().equals(ChatColor.AQUA + "default helmet")) {
                        if(!p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
                        }
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 2));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000, 0));
                    }
                    // good helmet
                    else if(meta.getDisplayName().equals(ChatColor.GOLD + "helmet")) {
                        p.removePotionEffect(PotionEffectType.SLOW);
                        p.removePotionEffect(PotionEffectType.BLINDNESS);
                        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000,3));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000,3));
                    }
                    // 디버그, 원래는 아이템 설명에 쓰기
                }
                else if(!p.getInventory().getHelmet().getType().equals(Material.CARVED_PUMPKIN)) {
                    // effect
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 1));
                    p.removePotionEffect(PotionEffectType.SLOW);
                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                    p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                    p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    p.removePotionEffect(PotionEffectType.WATER_BREATHING);

                    p.sendTitle(ChatColor.DARK_GREEN + "You're too high", ChatColor.GRAY + "warning", 0, 7000, 20);
                    if (p.getHealth() <= 1) {
                        p.setHealth(0);
                    }
                    // p.sendMessage("damage");
                }
            } catch(NullPointerException e1) {
                // p.sendMessage("error");
                // effect
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 1));
                p.sendTitle(ChatColor.DARK_GREEN + "You're too high", ChatColor.GRAY + "warning", 0, 7000, 20);
                if (p.getHealth() <= 1) {
                    p.setHealth(0);
                }
            }
        }
        else if(loc.getY() <= 35) {
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

            p.sendTitle("", "", 0, 0,0);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
        Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "someone died");
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        World w = e.getPlayer().getWorld();
        Location loc = new Location(w, 0.5, 20, 0.5);

        if(!e.isBedSpawn() && !e.isAnchorSpawn()) {
            //p.sendMessage("respawn");
            e.setRespawnLocation(loc);
            p.teleport(loc);
        }
        else if(e.isBedSpawn()) {
            //System.out.println(p.getName() + " bedspawn");
        }
        else if(e.isAnchorSpawn()) {
            //System.out.println(p.getName() + " anchorspawn");
        }
    }
/*
    @EventHandler
    public void onHelmetWear(InventoryClickEvent e) {
        try {
            Player p = (Player) e.getWhoClicked();
            InventoryType.SlotType slot = e.getSlotType();

            if (slot == InventoryType.SlotType.ARMOR) {
                p.sendMessage(e.getCursor().getType() + ""); // carved pumpkin
                p.sendMessage("" + p.getInventory().getItem(EquipmentSlot.HEAD).getType());
                if (e.getCursor().getType().equals(Material.CARVED_PUMPKIN)) {
                    //ItemMeta meta = p.getInventory().getHelmet().getItemMeta();
                    //String locname = meta.getDisplayName();

                /*if (isNumeric(locname)) {

                    meta.setDisplayName(String.valueOf(durability));
                    item.setItemMeta(meta);
                }*/
/*                }
            } else if (e.isShiftClick()) {
                if (p.getInventory().getHelmet().getType() == Material.CARVED_PUMPKIN) {
                    if (p.getInventory().getHelmet().getType().equals(Material.CARVED_PUMPKIN)) {

                    }
                }
            }
        } catch(NullPointerException e1) {
            System.out.print("");
        }
    }*/

    // 디스펜서로 헬멧 쓰는거 방지
    
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
}
