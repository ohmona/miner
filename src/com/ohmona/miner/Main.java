package com.ohmona.miner;

import com.ohmona.miner.command.Commands;
import com.ohmona.miner.event.Events;
import com.ohmona.miner.event.HeightSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

    public ConsoleCommandSender consol = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 활성화 중 입니다.");
        Events.setPlugin(this);
        HeightSystem.setPlugin(this);

        getCommand("rule").setExecutor(new Commands());

        Events events = new Events();
        HeightSystem heightSystem = new HeightSystem();
        getServer().getPluginManager().registerEvents(events, this);
        getServer().getPluginManager().registerEvents(heightSystem, this);

        /*
        *
        *  RECIPE
        *
         */

        /*
        * HELMET
        * */
        ItemStack helmet = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta meta = helmet.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "helmet");
        meta.addEnchant(Enchantment.DURABILITY, 10,true);
        helmet.setItemMeta(meta);

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmet);
        helmetRecipe.shape("%*%","^M^","BHB");
        helmetRecipe.setIngredient('*', Material.DIAMOND, 1);
        helmetRecipe.setIngredient('%', Material.BLACK_STAINED_GLASS_PANE, 2);
        helmetRecipe.setIngredient('^', Material.BLACK_CONCRETE,2);
        helmetRecipe.setIngredient('B', Material.ORANGE_WOOL,2);
        helmetRecipe.setIngredient('M', Material.MILK_BUCKET,1);
        helmetRecipe.setIngredient('H', Material.HONEYCOMB_BLOCK,1);

        /*
        *
        * POTIONs
        *
         */
        // Grade normal
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta posMeta = (PotionMeta) potion.getItemMeta();

        posMeta.setDisplayName(ChatColor.GREEN + "special potion [A]");
        posMeta.addCustomEffect(PotionEffectType.LUCK.createEffect(1200,0), false);
        posMeta.setColor(Color.LIME);
        potion.setItemMeta(posMeta);

        ShapedRecipe potionRecipe = new ShapedRecipe(potion);
        potionRecipe.shape("hdb","raf","isg");
        potionRecipe.setIngredient('d', Material.DIAMOND);
        potionRecipe.setIngredient('h', Material.GUNPOWDER);
        potionRecipe.setIngredient('b', Material.BREAD);
        potionRecipe.setIngredient('r', Material.BLAZE_ROD);
        potionRecipe.setIngredient('a', Material.GLASS_BOTTLE);
        potionRecipe.setIngredient('f', Material.ROTTEN_FLESH);
        potionRecipe.setIngredient('i', Material.IRON_INGOT);
        potionRecipe.setIngredient('s', Material.STICK);
        potionRecipe.setIngredient('g', Material.GOLD_INGOT);



        // Grade special
        ItemStack potion1 = new ItemStack(Material.POTION);
        PotionMeta posMeta1 = (PotionMeta) potion1.getItemMeta();

        posMeta1.setDisplayName(ChatColor.GREEN + "special potion [S]");
        posMeta1.addCustomEffect(PotionEffectType.LUCK.createEffect(6000,0), false);
        posMeta1.setColor(Color.SILVER);
        potion1.setItemMeta(posMeta1);

        ShapedRecipe potionRecipe1 = new ShapedRecipe(potion1);
        potionRecipe1.shape("hdb","raf","isg");
        potionRecipe1.setIngredient('d', Material.DIAMOND);
        potionRecipe1.setIngredient('h', Material.GLOWSTONE_DUST);
        potionRecipe1.setIngredient('b', Material.COOKED_BEEF);
        potionRecipe1.setIngredient('r', Material.BLAZE_ROD);
        potionRecipe1.setIngredient('a', Material.GLASS_BOTTLE);
        potionRecipe1.setIngredient('f', Material.SUGAR_CANE);
        potionRecipe1.setIngredient('i', Material.IRON_INGOT);
        potionRecipe1.setIngredient('s', Material.EMERALD);
        potionRecipe1.setIngredient('g', Material.GOLD_INGOT);


        // Grade Legendary
        ItemStack potion2 = new ItemStack(Material.POTION);
        PotionMeta posMeta2 = (PotionMeta) potion2.getItemMeta();

        posMeta2.setDisplayName(ChatColor.GREEN + "special potion [L]");
        posMeta2.addCustomEffect(PotionEffectType.LUCK.createEffect(24000,0), false);
        posMeta2.setColor(Color.WHITE);
        potion2.setItemMeta(posMeta2);

        ShapedRecipe potionRecipe2 = new ShapedRecipe(potion2);
        potionRecipe2.shape("hdb","raf","isg");
        potionRecipe2.setIngredient('d', Material.DIAMOND);
        potionRecipe2.setIngredient('h', Material.DRIED_KELP);
        potionRecipe2.setIngredient('b', Material.RABBIT_STEW);
        potionRecipe2.setIngredient('r', Material.BLAZE_ROD);
        potionRecipe2.setIngredient('a', Material.HONEY_BOTTLE);
        potionRecipe2.setIngredient('f', Material.BAMBOO);
        potionRecipe2.setIngredient('i', Material.IRON_BLOCK);
        potionRecipe2.setIngredient('s', Material.ENDER_PEARL);
        potionRecipe2.setIngredient('g', Material.GOLD_BLOCK);

        /*
        *   Clean Zone
         */
        ItemStack cleanZone = new ItemStack(Material.LIME_CONCRETE);
        ItemMeta cleanZoneMeta = cleanZone.getItemMeta();

        cleanZoneMeta.setDisplayName(ChatColor.GREEN + "clean zone");
        cleanZoneMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        cleanZone.setItemMeta(cleanZoneMeta);

        ShapedRecipe cleanZoneRecipe = new ShapedRecipe(cleanZone);
        cleanZoneRecipe.shape("eod","ogo","mon");
        cleanZoneRecipe.setIngredient('o', Material.OBSIDIAN);
        cleanZoneRecipe.setIngredient('e', Material.ENDER_EYE);
        cleanZoneRecipe.setIngredient('d', Material.DIAMOND_ORE);
        cleanZoneRecipe.setIngredient('g', Material.GHAST_TEAR);
        cleanZoneRecipe.setIngredient('m', Material.MILK_BUCKET);
        cleanZoneRecipe.setIngredient('n', Material.NAUTILUS_SHELL);


        getServer().addRecipe(cleanZoneRecipe);
        getServer().addRecipe(potionRecipe);
        getServer().addRecipe(potionRecipe1);
        getServer().addRecipe(potionRecipe2);
        getServer().addRecipe(helmetRecipe);
    }
    @Override
    public void onDisable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 비활성화 중 입니다.");
    }
}
