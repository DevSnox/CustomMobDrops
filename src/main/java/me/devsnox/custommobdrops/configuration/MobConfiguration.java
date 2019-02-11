package me.devsnox.custommobdrops.configuration;

import me.devsnox.custommobdrops.drops.Drop;
import me.devsnox.custommobdrops.drops.DropType;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class MobConfiguration {

    private String money;
    private String moneyReceived;

    private DropType dropType;

    private HashMap<EntityType, ArrayList<Drop>> drops;

    public MobConfiguration(String money, String moneyReceived, DropType dropType, HashMap<EntityType, ArrayList<Drop>> drops) {
        this.money = money;
        this.moneyReceived = moneyReceived;
        this.dropType = dropType;
        this.drops = drops;
    }

    public String getMoney() {
        return money;
    }

    public String getMoneyReceived() {
        return moneyReceived;
    }

    public DropType getDropType() {
        return dropType;
    }

    public HashMap<EntityType, ArrayList<Drop>> getDrops() {
        return drops;
    }
}
