package com.avery.backpacker.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.avery.backpacker.Plugin;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

    }

    @EventHandler
    public void onBackpackCraft(CraftItemEvent event) {
        ItemStack item = event.getCurrentItem();
        Plugin.log(item.displayName().toString());
    }
    
}
