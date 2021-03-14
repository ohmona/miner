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
        w.setGameRule(GameRule.RANDOM_TICK_SPEED, 1);
        w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        w.setTime(13000);
        w.setDifficulty(Difficulty.HARD);

        if(!p.hasPlayedBefore()) {
            p.teleport(loc);
            e.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "new player joined");

            createSpawn.createDefaultspawnarea(overW);

            p.getInventory().addItem(new ItemStack(Material.OAK_SAPLING, 1));
            p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE, 1));
            p.getInventory().addItem(helmet);

            cmd.giveGuidBook(p);
        }
        else if(p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GOLD + "" + ChatColor.BOLD +"someone joined");

            createSpawn.createspawnarea(overW);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "someone quit");
    }

    @EventHandler
    public void onPlayerIsOverY35(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = e.getPlayer().getLocation();

        // player is over 35 and in overworld
        if(loc.getY() > 35 && p.getWorld().getName().endsWith("world")) {
            defaultHelmetSystem(p);
        }
        // player is under 35 and in overworld
        else if(loc.getY() <= 35 && p.getWorld().getName().endsWith("world")) {
            p.removePotionEffect(PotionEffectType.SLOW);
            p.removePotionEffect(PotionEffectType.BLINDNESS);
            p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

            p.sendTitle("", "", 0, 0,0);
        }
        // player is in nether
        else if(p.getWorld().getName().endsWith("_nether")) {
            // player in over 128
            if(loc.getY() > 128) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20,1));
            }
            // player in under 20
            if(loc.getY() <= 20) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 7000, 1));
            }
            // player is over 20 and under 128
            else {
                p.removePotionEffect(PotionEffectType.WITHER);
                p.removePotionEffect(PotionEffectType.SLOW);
            }
        }
        // player is in ender
        else if(p.getWorld().getName().endsWith("_the_end")) {
            defaultHelmetSystem(p);
        }
    }

    @EventHandler
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
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "someone died");
    }

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

    private int taskId;

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if(item.getType().equals(Material.POTION)) {
            if (item.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "special potion")) {
                p.addPotionEffect(PotionEffectType.LUCK.createEffect(6000, 0), false);
                taskId = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
                    int time = 0;
                    @Override
                    public void run() {
                        if(time < 600) {
                            p.removePotionEffect(PotionEffectType.WITHER);
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("time remain : " + tickToMin(time)));
                        }
                        else if (time >= 600) {
                            Bukkit.getScheduler().cancelTask(taskId);
                        }
                        time++;
                    }
                }, 1L,1L);
            }
        }
    }

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

    public String tickToMin(int second) {
        second /= 20;
        int min;
        int sec;
        min = second / 60;
        sec = second % 60;

        String result = "" + min + ":" + sec;

        return result;
    }

    public void defaultHelmetSystem(Player p) {
        try {
            // helmet system
            // check pumpkin
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
            // check not pumpkin
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
            // check has luck
            else if(p.hasPotionEffect(PotionEffectType.LUCK)) {
                p.removePotionEffect(PotionEffectType.WITHER);
                p.removePotionEffect(PotionEffectType.SLOW);
                p.removePotionEffect(PotionEffectType.BLINDNESS);
                p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                p.removePotionEffect(PotionEffectType.WATER_BREATHING);
            }
            // check hasn't luck
            else if(!p.hasPotionEffect(PotionEffectType.LUCK)) {
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
}
