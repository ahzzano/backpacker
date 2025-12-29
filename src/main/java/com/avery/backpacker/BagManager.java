package com.avery.backpacker;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class BagManager {
    private static BagManager instance = null;
    private HashMap<UUID, BackpackInventory> bags;
    
    private BagManager() {
        // ... constructor stuff
        this.bags = new HashMap<UUID, BackpackInventory>();

        // load inventories
        File dir = new File(Plugin.getInstance().getDataFolder(), "backpacks");
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            for (File file : dir.listFiles()) {
                if (!file.isFile()) continue;
                if (!file.getName().endsWith(".yml")) continue;

                UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                List<?> items = (List<?>) config.getList("contents");
                if (items != null) {
                    ItemStack[] itemArr = items.stream()
                        .map(obj -> obj instanceof ItemStack ? (ItemStack) obj : null)
                        .toArray(ItemStack[]::new);
                    
                    BackpackInventory inv = new BackpackInventory(Plugin.getInstance());
                    inv.getInventory().setContents(itemArr);

                    bags.put(uuid, inv);
                }
            }
        }

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

    public static void saveInventories() {
        for(Map.Entry<UUID, BackpackInventory> entry : getInstance().bags.entrySet()) {
            Plugin.log("Saving Inventory");
            String key = entry.getKey().toString();
            BackpackInventory inventory = entry.getValue();

            File dir = new File(Plugin.getInstance().getDataFolder(), "backpacks");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(dir, key + ".yml");

            YamlConfiguration config = new YamlConfiguration();
            config.set("contents", inventory.getInventory().getContents());
            try {
                config.save(file);
                Plugin.log("Finished");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
