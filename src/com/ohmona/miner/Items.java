package com.ohmona.miner;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

public class Items {

    // Item
    public ItemStack helmet;
    public ItemStack potion;
    public ItemStack potion1;
    public ItemStack potion2;
    public ItemStack cleanZone;

    public ItemStack defaultPickaxe;

    public Items() {
        //init Item
        helmet = new ItemStack(Material.CARVED_PUMPKIN);
        potion = new ItemStack(Material.POTION);
        potion1 = new ItemStack(Material.POTION);
        potion2 = new ItemStack(Material.POTION);
        cleanZone = new ItemStack(Material.LIME_CONCRETE);
        defaultPickaxe = new ItemStack(Material.IRON_PICKAXE);

        // get meta
        ItemMeta meta = helmet.getItemMeta();
        PotionMeta posMeta = (PotionMeta) potion.getItemMeta();
        PotionMeta posMeta1 = (PotionMeta) potion1.getItemMeta();
        PotionMeta posMeta2 = (PotionMeta) potion2.getItemMeta();
        ItemMeta cleanZoneMeta = cleanZone.getItemMeta();
        ItemMeta defaultPickaxeMeta = defaultPickaxe.getItemMeta();

        // config meta
        meta.setDisplayName(ChatColor.GOLD + "helmet");
        meta.addEnchant(Enchantment.DURABILITY, 10,true);

        posMeta.setDisplayName(ChatColor.GREEN + "special potion [A]");
        posMeta.addCustomEffect(PotionEffectType.LUCK.createEffect(6000,0), false);
        posMeta.setColor(Color.LIME);

        posMeta1.setDisplayName(ChatColor.GREEN + "special potion [S]");
        posMeta1.addCustomEffect(PotionEffectType.LUCK.createEffect(12000,0), false);
        posMeta1.setColor(Color.SILVER);

        posMeta2.setDisplayName(ChatColor.GREEN + "special potion [L]");
        posMeta2.addCustomEffect(PotionEffectType.LUCK.createEffect(24000,0), false);
        posMeta2.setColor(Color.WHITE);

        cleanZoneMeta.setDisplayName(ChatColor.GREEN + "clean zone");
        cleanZoneMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);

        defaultPickaxeMeta.setDisplayName(ChatColor.AQUA + "welcome pickaxe");
        defaultPickaxeMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
        defaultPickaxeMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);

        // set meta
        helmet.setItemMeta(meta);
        potion.setItemMeta(posMeta);
        potion1.setItemMeta(posMeta1);
        potion2.setItemMeta(posMeta2);
        cleanZone.setItemMeta(cleanZoneMeta);
        defaultPickaxe.setItemMeta(defaultPickaxeMeta);
    }

}
