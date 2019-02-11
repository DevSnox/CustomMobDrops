package me.devsnox.custommobdrops.drops;

public class ExpDrop implements Drop {

    private int amount;
    private double chance;

    public ExpDrop(int amount, double chance) {
        this.amount = amount;
        this.chance = chance;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public double getChance() {
        return 0;
    }
}
