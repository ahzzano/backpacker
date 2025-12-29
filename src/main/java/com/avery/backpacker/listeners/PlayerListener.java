package com.avery.backpacker.listeners;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.avery.backpacker.Plugin;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

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

    }
    
}
