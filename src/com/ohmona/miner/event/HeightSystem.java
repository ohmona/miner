package com.ohmona.miner.event;

import com.ohmona.miner.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeightSystem implements Listener {

    public static Main plugin;
    public static void setPlugin(Main MainPlugin) {
        plugin = MainPlugin;
    }

    @EventHandler
    public void onPlayerIsOverY35(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = e.getPlayer().getLocation();

        // player is over 35 and in overworld
        if(loc.getY() > 35 && p.getWorld().getName().endsWith("world")) {
            defaultProtectionSystem(p);
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
            defaultProtectionSystem(p);
        }
    }

    public void defaultProtectionSystem(Player p) {
        {
            // helmet system
            // check pumpkin
            if (hasPlayerProtection(p)) {
                // remove debuff
                removeEffect(p);
                // send title
                p.sendTitle("", ChatColor.GRAY + "warning", 0, 7000, 0);

                if (getTypeOfProtection(p) == 1) {

                    ItemStack item = p.getInventory().getHelmet();
                    ItemMeta meta = item.getItemMeta();

                    // Default helmet
                    if (meta.getDisplayName().equals(ChatColor.AQUA + "default helmet")) {
                        if (!p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000, 1));
                        }
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 2));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000, 0));
                    }
                    // good helmet
                    else if (meta.getDisplayName().equals(ChatColor.GOLD + "helmet")) {
                        p.removePotionEffect(PotionEffectType.SLOW);
                        p.removePotionEffect(PotionEffectType.BLINDNESS);
                        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);

                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000, 3));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000, 3));
                    }
                } else if (getTypeOfProtection(p) == 2) {
                    removeEffect(p);
                }
                // 디버그, 원래는 아이템 설명에 쓰기
            } else if (!hasPlayerProtection(p)) {
                // effect
                removeEffect(p);
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 1));

                p.sendTitle(ChatColor.DARK_GREEN + "You're too high", ChatColor.GRAY + "warning", 0, 7000, 20);
                if (p.getHealth() <= 1) {
                    p.setHealth(0);
                }
            }
        }
    }

    public boolean hasPlayerProtection(Player p) {
        if(p.getInventory().getHelmet() != null) {
            if (p.getInventory().getHelmet().getType().equals(Material.CARVED_PUMPKIN)) {
                return true;
            }
            else if (p.hasPotionEffect(PotionEffectType.LUCK)) {
                return true;
            }
        }
        else if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            return true;
        }
        return false;
    }

    public void removeEffect(Player p) {
        p.removePotionEffect(PotionEffectType.WITHER);
        p.removePotionEffect(PotionEffectType.SLOW);
        p.removePotionEffect(PotionEffectType.BLINDNESS);
        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
    }

    public byte getTypeOfProtection(Player p) {
        // helmet
        if(p.getInventory().getHelmet() != null) {
            if (p.getInventory().getHelmet().getType().equals(Material.CARVED_PUMPKIN)) {
                return 1;
            }
            else if (p.hasPotionEffect(PotionEffectType.LUCK)) {
                return 2;
            }
        }
        else if (p.hasPotionEffect(PotionEffectType.LUCK)) {
            return 2;
        }
        return 0;
    }

}
