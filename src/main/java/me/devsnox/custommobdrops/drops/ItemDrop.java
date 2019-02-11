package me.devsnox.custommobdrops.drops;

import org.bukkit.Material;

public class ItemDrop implements Drop {

    private Material material;
    private int amount;
    private double chance;

    public ItemDrop(Material material, int amount, double chance) {
        this.material = material;
        this.amount = amount;
        this.chance = chance;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public double getChance() {
        return chance;
    }
}
