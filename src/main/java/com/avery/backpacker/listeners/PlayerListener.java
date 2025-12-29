package com.avery.backpacker.listeners;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.avery.backpacker.BackpackInventory;
import com.avery.backpacker.BagManager;
import com.avery.backpacker.Plugin;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack currentItem = event.getItem();
        if (currentItem == null) {
            return;
        }

        boolean is_backpack = currentItem.getPersistentDataContainer().getOrDefault(Plugin.getInstance().backpack, PersistentDataType.BOOLEAN, false);
        if (!is_backpack) {
            return;
        }
        // Backpacking Time

        if (action != Action.RIGHT_CLICK_AIR) {
            return;
        }

        String bag_id = currentItem.getPersistentDataContainer().getOrDefault(Plugin.getInstance().backpack_id, PersistentDataType.STRING, null);
        if (bag_id != null) {
            // Open the bag
            BackpackInventory inv = BagManager.getInstance().getBackpackInventory(bag_id);
            event.getPlayer().openInventory(inv.getInventory());
        }


    }

    @EventHandler
    public void onBackpackCraft(CraftItemEvent event) {
        ItemStack item = event.getCurrentItem();
        boolean is_backpack = item.getPersistentDataContainer().getOrDefault(Plugin.getInstance().backpack, PersistentDataType.BOOLEAN, false);
        if (!is_backpack) {
            return;
        }
        String id = UUID.randomUUID().toString();

        event.getWhoClicked().sendPlainMessage("New Backpack Created: " + id);
        item.editPersistentDataContainer(pdc -> {
            pdc.set(Plugin.getInstance().backpack_id, PersistentDataType.STRING, id);
        });

        BagManager.getInstance().addBackpackInventory(id);
    }
    
}
