package com.ohmona.miner.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        giveGuidBook(p);

        return true;
    }

    public void giveGuidBook(Player p) {
        String nl = "\n";

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        List<String> pages = new ArrayList<String>();

        bookMeta.setTitle(ChatColor.GOLD + "guide book");
        bookMeta.setAuthor("ohmona");

        pages.add(0, "Miner plugin"  +
                nl+"author : ohmona" +
                nl+
                nl+"after 1.17, Minecraft will be" +
                nl+"changed forever" +
                nl +
                nl + "we need to spend more time" +
                nl + "in cave" +
                nl +
                nl + "hope you enjoy :)");
        pages.add(1,"Basic System" +
                nl + "-------------------" +
                nl + nl + "If you go up over Y 35" +
                nl + "you'll get damages" +
                nl + "but when you have a helmet," +
                nl + "you won't get any damages" +
                nl +
                nl + "at nether :" +
                nl + "(under Y20) get slow" +
                nl + "(over Y128) get wither" +
                nl + "at end : " +
                nl + "get wither");

        pages.add(2,"Helmet System" +
                nl + "-------------------" +
                nl +
                nl + "Default Helmet :" +
                nl + "You'll get Default Helmet when" +
                nl + "you join in server first time" +
                nl +
                nl + "If you wear this over Y 35" +
                nl + "you'll get some debuffs" +
                nl +
                nl + "Normal Helmet :" +
                nl + "this is just a carved pumpkin" +
                nl + "which you can find it in many" +
                nl + "way" );
        pages.add(3, "you won't get any debuff" +
                nl +
                nl + "Helmet :" +
                nl + "you can craft this helmet" +
                nl +
                nl + "you'll get some buffs");
        pages.add(4,"Recipes" +
                nl + "--------------------" +
                nl +
                nl + "Helmet :" +
                nl + "   1     2     3" +
                nl + "1 BSGP   D    BSGP     " +
                nl + "2  BC    MB    BC    " +
                nl + "3  OW   HCB    OW     " +
                nl +
                nl + "BSGP : Black_Stainded_Glass_Pane" +
                nl + "D : Diamond" +
                nl + "BC : Black_Concrete" +
                nl + "MB : Milk_bucket");
        pages.add(5, "" +
                nl + "OW : Orange_Wool" +
                nl + "HCB : Honeycomb_Block" +
                nl);
        bookMeta.setPages(pages);

        book.setItemMeta(bookMeta);

        p.getInventory().addItem(book);
    }

}
