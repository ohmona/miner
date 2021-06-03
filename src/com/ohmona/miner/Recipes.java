package com.ohmona.miner;

import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes {
    Items items = new Items();

    // Recipe
    public ShapedRecipe helmetRecipe;
    public ShapedRecipe potionRecipe;
    public ShapedRecipe potionRecipe1;
    public ShapedRecipe potionRecipe2;
    public ShapedRecipe cleanZoneRecipe;

    public Recipes() {
        //init Recipe

        // helmet
        helmetRecipe = new ShapedRecipe(items.helmet);
        helmetRecipe.shape("%*%","^M^","BHB");
        helmetRecipe.setIngredient('*', Material.DIAMOND);
        helmetRecipe.setIngredient('%', Material.BLACK_STAINED_GLASS);
        helmetRecipe.setIngredient('^', Material.OBSIDIAN);
        helmetRecipe.setIngredient('B', Material.ORANGE_WOOL);
        helmetRecipe.setIngredient('M', Material.MILK_BUCKET);
        helmetRecipe.setIngredient('H', Material.HONEYCOMB);

        // potion
        potionRecipe = new ShapedRecipe(items.potion);
        potionRecipe.shape("hdb","raf","isg");
        potionRecipe.setIngredient('d', Material.DIAMOND);
        potionRecipe.setIngredient('h', Material.GUNPOWDER);
        potionRecipe.setIngredient('b', Material.GOLDEN_APPLE);
        potionRecipe.setIngredient('r', Material.BLAZE_ROD);
        potionRecipe.setIngredient('a', Material.GLASS_BOTTLE);
        potionRecipe.setIngredient('f', Material.ROTTEN_FLESH);
        potionRecipe.setIngredient('i', Material.IRON_INGOT);
        potionRecipe.setIngredient('s', Material.OAK_SAPLING);
        potionRecipe.setIngredient('g', Material.GOLD_INGOT);

        //
        potionRecipe1 = new ShapedRecipe(items.potion1);
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

        //
        potionRecipe2 = new ShapedRecipe(items.potion2);
        potionRecipe2.shape("hdb","raf","isg");
        potionRecipe2.setIngredient('d', Material.DIAMOND);
        potionRecipe2.setIngredient('h', Material.DRIED_KELP);
        potionRecipe2.setIngredient('b', Material.MUSHROOM_STEW);
        potionRecipe2.setIngredient('r', Material.BLAZE_ROD);
        potionRecipe2.setIngredient('a', Material.HONEY_BOTTLE);
        potionRecipe2.setIngredient('f', Material.BAMBOO);
        potionRecipe2.setIngredient('i', Material.IRON_BLOCK);
        potionRecipe2.setIngredient('s', Material.ENDER_PEARL);
        potionRecipe2.setIngredient('g', Material.GOLD_BLOCK);

        //
        cleanZoneRecipe = new ShapedRecipe(items.cleanZone);
        cleanZoneRecipe.shape("eod","bgb","mon");
        cleanZoneRecipe.setIngredient('o', Material.OBSIDIAN);
        cleanZoneRecipe.setIngredient('e', Material.ENDER_EYE);
        cleanZoneRecipe.setIngredient('d', Material.EMERALD);
        cleanZoneRecipe.setIngredient('g', Material.LIME_STAINED_GLASS);
        cleanZoneRecipe.setIngredient('m', Material.MILK_BUCKET);
        cleanZoneRecipe.setIngredient('n', Material.NAUTILUS_SHELL);
        cleanZoneRecipe.setIngredient('b', Material.DIAMOND_BLOCK);
    }

}
