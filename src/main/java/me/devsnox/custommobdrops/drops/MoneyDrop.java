package me.devsnox.custommobdrops.drops;

public class MoneyDrop implements Drop {

    private int amount;
    private double chance;

    public MoneyDrop(int amount, double chance) {
        this.amount = amount;
        this.chance = chance;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public double getChance() {
        return this.chance;
    }
}
