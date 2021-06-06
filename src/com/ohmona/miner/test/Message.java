package com.ohmona.miner.test;

import org.bukkit.ChatColor;

public class Message {

    private ChatColor color;
    private String text;

    private static String list = "";

    // Default constructor. Use for short message
    public Message(String text, ChatColor color) {
        this.text = text;
        this.color = color;

        list = list + colorMsg(this.text, this.color);
    }

    // constructor for bold message at the beginning. Use for short message
    public Message(String text, ChatColor color, boolean bold) {
        if(bold) {
            this.text = text;
            this.color = color;

            list = list + colorBoldMsg(this.text, this.color);
        }
        else {
            this.text = text;
            this.color = color;

            list = list + colorMsg(this.text, this.color);
        }
    }

    // constructor for short message. this message is white
    public Message(String text) {
        this.text = text;
        this.color = ChatColor.WHITE;

        list = list + colorMsg(this.text, this.color);
    }

    // constructor for long message. use with begin() method
    public Message() {
        this.text = "";
        this.color = ChatColor.WHITE;
    }

    /*
    *  begin. use for long message
    *  e. g. Message.begin().attach(
    *
    */
    public static Message begin() {
        Message msg = new Message();
        return msg;
    }

    // add message with new color
    public Message attach(String text, ChatColor color) {
        list = list + colorMsg(text, color);
        this.text = text;
        this.color = color;
        return this;
    }

    // add message with new color and bold
    public Message attach(String text, ChatColor color, boolean bold) {
        if(bold) {
            list = list + colorBoldMsg(text, color);
            this.text = text;
            this.color = color;
            return this;
        }
        else {
            list = list + colorMsg(text, color);
            this.text = text;
            this.color = color;
            return this;
        }
    }

    public Message attach(String text) {
        list = list + colorMsg(text, this.color);
        return this;
    }

    // convert into String from Message and clear list. Use at the end of Message
    public String end() {
        String message = "";
        message = list;
        list = "";
        return message;
    }

    // returns Message with color
    public static String colorMsg(String msg, ChatColor color) {
        return color + msg;
    }

    // returns Message with color and bold
    public static String colorBoldMsg(String msg, ChatColor color) {
        return color + "" + ChatColor.BOLD + msg;
    }
}
