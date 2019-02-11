package me.devsnox.custommobdrops.configuration;

import me.devsnox.custommobdrops.drops.Drop;
import me.devsnox.custommobdrops.drops.DropType;
import me.devsnox.custommobdrops.drops.ItemDrop;
import me.devsnox.custommobdrops.mob.MobManager;
import me.devsnox.custommobdrops.drops.MoneyDrop;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MobConfigurator {

    private MobConfiguration mobConfiguration;

    private YamlConfiguration yamlConfiguration;

    public MobConfigurator(MobManager mobManager) {
        this.yamlConfiguration = new UTF8YamlConfiguration();

        try {
            this.yamlConfiguration.load(new File(mobManager.getPlugin().getDataFolder() + File.separator + "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        HashMap<EntityType, ArrayList<Drop>> mobs = new HashMap<>();

        for(String entityType : this.yamlConfiguration.getConfigurationSection("mobs").getKeys(false)) {
            ConfigurationSection configurationSection = yamlConfiguration.getConfigurationSection("mobs." + entityType + ".drops");
            ArrayList<Drop> drops = new ArrayList<>();

            for(String drop : configurationSection.getKeys(false)) {
                if(drop.equalsIgnoreCase("MONEY")) {
                    drops.add(new MoneyDrop(configurationSection.getInt(drop + ".amount"), configurationSection.getDouble(drop + ".chance")));
                } else {
                    drops.add(new ItemDrop(Material.valueOf(configurationSection.getString(drop + ".material")), configurationSection.getInt(drop + ".amount"), configurationSection.getDouble(drop + ".chance")));
                }
            }

            mobs.put(EntityType.valueOf(entityType), drops);
        }

        this.mobConfiguration = new MobConfiguration(ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("money")),
                ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("money-received")),
                        DropType.valueOf(yamlConfiguration.getString("drop-type")), mobs);
    }

    public MobConfiguration getMobConfiguration() {
        return mobConfiguration;
    }
}
