package com.avery.backpacker;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BackpackInventory implements InventoryHolder{
    private final Inventory inventory;

    public BackpackInventory(Plugin plugin) {
        this.inventory = plugin.getServer().createInventory(this, 9);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    
}
