package com.ohmona.miner;

import com.ohmona.miner.command.Commands;
import com.ohmona.miner.event.Events;
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
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

    public ConsoleCommandSender consol = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 활성화 중 입니다.");
        Events.setPlugin(this);

        getCommand("rule").setExecutor(new Commands());

        Events events = new Events();
        getServer().getPluginManager().registerEvents(events, this);

        ItemStack helmet = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta meta = helmet.getItemMeta();
        ItemStack potion = new ItemStack(Material.POTION);
        PotionMeta posMeta = (PotionMeta) potion.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "helmet");
        meta.addEnchant(Enchantment.DURABILITY, 10,true);
        helmet.setItemMeta(meta);

        posMeta.setDisplayName(ChatColor.GREEN + "special potion");
        posMeta.addCustomEffect(PotionEffectType.LUCK.createEffect(6000,0), false);
        posMeta.setColor(Color.LIME);
        potion.setItemMeta(posMeta);

        ShapedRecipe helmetRecipe = new ShapedRecipe(helmet);
        helmetRecipe.shape("%*%","^M^","BHB");
        helmetRecipe.setIngredient('*', Material.DIAMOND, 1);
        helmetRecipe.setIngredient('%', Material.BLACK_STAINED_GLASS_PANE, 3);
        helmetRecipe.setIngredient('^', Material.BLACK_CONCRETE,2);
        helmetRecipe.setIngredient('B', Material.ORANGE_WOOL,2);
        helmetRecipe.setIngredient('M', Material.MILK_BUCKET,1);
        helmetRecipe.setIngredient('H', Material.HONEYCOMB_BLOCK,1);

        ShapedRecipe potionRecipe = new ShapedRecipe(potion);
        potionRecipe.shape("aaa","aaa","aaa");
        potionRecipe.setIngredient('a', Material.GLASS_BOTTLE);

        getServer().addRecipe(potionRecipe);
        getServer().addRecipe(helmetRecipe);
    }
    @Override
    public void onDisable() {
        consol.sendMessage(ChatColor.AQUA + "[Miner]플러그인 비활성화 중 입니다.");
    }
}
