package me.devsnox.custommobdrops;

import me.devsnox.custommobdrops.listeners.DeathListener;
import me.devsnox.custommobdrops.listeners.PlayerListener;
import me.devsnox.custommobdrops.mob.MobManager;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMobDrops extends JavaPlugin {

    private MobManager mobManager;

    private Economy economy;

    @Override
    public void onEnable() {

        System.out.println("- Hooking into Vault");

        if (setupEconomy()) {
            System.out.println(ChatColor.RED + "- Sucessfully hooked into Vault!");
        } else {
            System.out.println(ChatColor.RED + "- Failed to hook into Vault!");
        }

        this.saveResource("config.yml", false);

        mobManager = new MobManager(this, economy);

        Bukkit.getPluginManager().registerEvents(new DeathListener(this.mobManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this.mobManager), this);

        new Metrics(this, 2295);
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        economy = rsp.getProvider();
        return economy != null;
    }
}
