package me.devsnox.custommobdrops.listeners;

import de.tr7zw.itemnbtapi.NBTItem;
import me.devsnox.custommobdrops.drops.Drop;
import me.devsnox.custommobdrops.drops.DropType;
import me.devsnox.custommobdrops.drops.ItemDrop;
import me.devsnox.custommobdrops.drops.MoneyDrop;
import me.devsnox.custommobdrops.mob.MobManager;
import me.devsnox.custommobdrops.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DeathListener implements Listener {

    private MobManager mobManager;

    public DeathListener(MobManager mobManager) {
        this.mobManager = mobManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(this.mobManager.getDrops().containsKey(event.getEntityType())) {

            Random random = new Random();

            for (Drop drop : this.mobManager.getDrops().get(event.getEntityType())) {
                if (drop.getChance() > random.nextDouble() * 100) {
                    if(drop instanceof MoneyDrop) {
                        NBTItem nbtItem = new NBTItem(new ItemBuilder(Material.GOLD_NUGGET, 1, (byte) 0,
                                ChatColor.translateAlternateColorCodes('&', this.mobManager.getMobConfiguration().getMoney().replaceAll("%amount%", String.valueOf(drop.getAmount()))), false).build());

                        nbtItem.setInteger("money", drop.getAmount());

                        event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), nbtItem.getItem());
                    } else {
                        event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(((ItemDrop)drop).getMaterial(), drop.getAmount()));
                    }

                    if(this.mobManager.getMobConfiguration().getDropType() == DropType.ONE) {
                        return;
                    }
                }
            }
        }
    }
}
