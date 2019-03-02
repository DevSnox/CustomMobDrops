package me.devsnox.custommobdrops.listeners;

import de.tr7zw.itemnbtapi.NBTItem;
import me.devsnox.custommobdrops.mob.MobManager;
import me.devsnox.custommobdrops.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener
{
	private final MobManager mobManager;
	
	public PlayerListener(MobManager mobManager)
	{
		this.mobManager = mobManager;
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		ItemStack itemStack = event.getItem().getItemStack();
		if (itemStack.getType() == Material.GOLD_NUGGET)
		{
			if (itemStack.hasItemMeta())
			{
				if (itemStack.getItemMeta().hasDisplayName())
				{
					if (itemStack.getItemMeta().getDisplayName().endsWith("§6§l$"))
					{
						NBTItem nbtItem = new NBTItem(itemStack);
						if (nbtItem.hasKey("money"))
						{
							event.setCancelled(true);
							event.getItem().remove();
							int amount = nbtItem.getInteger("money");
							this.mobManager.getEconomy().depositPlayer(player, amount * itemStack.getAmount());
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.mobManager.getMobConfiguration().getMoneyReceived().replaceAll("%amount%", String.valueOf(amount))));
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event)
	{
		if (event.getWhoClicked() instanceof Player)
		{
			Player player = ((Player) event.getWhoClicked()).getPlayer();
			ItemStack itemStack = event.getCurrentItem();
			if (itemStack == null)
			{
				return;
			}
			if (itemStack.getType() == Material.GOLD_NUGGET)
			{
				if (itemStack.hasItemMeta())
				{
					if (itemStack.getItemMeta().hasDisplayName())
					{
						if (itemStack.getItemMeta().getDisplayName().endsWith("§6§l$"))
						{
							NBTItem nbtItem = new NBTItem(itemStack);
							if (nbtItem.hasKey("money"))
							{
								event.setCancelled(true);
								event.setCurrentItem(new ItemBuilder(Material.AIR, 1, false).build());
								int amount = nbtItem.getInteger("money");
								this.mobManager.getEconomy().depositPlayer(player, amount * itemStack.getAmount());
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.mobManager.getMobConfiguration().getMoneyReceived().replaceAll("%amount%", String.valueOf(amount))));
							}
						}
					}
				}
			}
		}
	}
}
