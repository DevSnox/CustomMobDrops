package me.devsnox.custommobdrops.mob;

import me.devsnox.custommobdrops.configuration.MobConfiguration;
import me.devsnox.custommobdrops.configuration.MobConfigurator;
import me.devsnox.custommobdrops.drops.Drop;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class MobManager {

    private Plugin plugin;

    private MobConfigurator mobConfigurator;

    private MobConfiguration mobConfiguration;

    private HashMap<EntityType, ArrayList<Drop>> drops;

    private Economy economy;

    public MobManager(Plugin plugin, Economy economy) {
        this.plugin = plugin;
        this.economy = economy;

        this.mobConfigurator = new MobConfigurator(this);
        this.mobConfiguration = this.mobConfigurator.getMobConfiguration();
        this.drops = mobConfiguration.getDrops();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public MobConfiguration getMobConfiguration() {
        return mobConfiguration;
    }

    public HashMap<EntityType, ArrayList<Drop>> getDrops() {
        return drops;
    }

    public Economy getEconomy() {
        return economy;
    }
}
