package me.devsnox.custommobdrops.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Copyright by DevSnox
 * E-Mail: me.devsnox@gmail.com
 * Skype: DevSnox
 */
public class ItemBuilder {

    private Material material;
    private int amount;
    private byte durability;
    private String displayName;
    private ArrayList<String> lore;
    //private String url;
    private boolean unbreakable;

    public ItemBuilder(Material material, int amount, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.unbreakable = unbreakable;
    }

    public ItemBuilder(Material material, int amount, byte durability, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
        this.unbreakable = unbreakable;
    }

    public ItemBuilder(Material material, int amount, byte durability, String displayName, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
        this.displayName = displayName;
        this.unbreakable = unbreakable;
    }

    public ItemBuilder(Material material, int amount, byte durability, String displayName, ArrayList<String> lore, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.durability = durability;
        this.displayName = displayName;
        this.lore = lore;
        this.unbreakable = unbreakable;
    }

    /*public ItemBuilder(String url, String displayName, ArrayList<String> lore, boolean unbreakable) {
        this.material = Material.SKULL;
        this.amount = 1;
        //this.url = url;
        this.displayName = displayName;
        this.lore = lore;
        this.unbreakable = unbreakable;
    }*/

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(this.material, this.amount, durability);
        ItemMeta itemMeta = null;

        /*if(url != null) {
            itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

            ItemMeta headMeta = itemStack.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", this.url).getBytes());
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            Field profileField = null;
            try {
                profileField = headMeta.getClass().getDeclaredField("profile");
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            profileField.setAccessible(true);
            try {
                profileField.set(headMeta, profile);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            itemStack.setItemMeta(headMeta);
        }*/

        if(displayName != null) {
            itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.displayName);
        }

        if(lore != null) {
            itemMeta.setLore(this.lore);
        }

        if(this.unbreakable) {
            itemMeta.spigot().setUnbreakable(true);
        }

        if(itemMeta != null) {
            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }
}
