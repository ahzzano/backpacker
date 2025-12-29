package com.avery.backpacker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.inventory.InventoryHolder;

public class BagManager {
    private static BagManager instance = null;
    private HashMap<UUID, BackpackInventory> bags;
    
    private BagManager() {
        // ... constructor stuff
        this.bags = new HashMap<UUID, BackpackInventory>();
    }

    public static synchronized BagManager getInstance() {
        if(instance == null) {
            instance = new BagManager();
        }

        return instance;
    }

    public BackpackInventory getBackpackInventory(String name) {
        UUID id = UUID.fromString(name);

        return this.bags.get(id);
    }

    public void addBackpackInventory(String name) {
        UUID id = UUID.fromString(name);
        this.bags.put(id, new BackpackInventory(Plugin.getInstance()));
    }

}
