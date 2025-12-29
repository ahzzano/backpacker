package com.avery.backpacker;

import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import com.avery.backpacker.listeners.PlayerListener;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.Component;

/*
 * backpacker java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("backpacker");
  private static Plugin instance;

  public NamespacedKey backpack = null;
  public NamespacedKey backpack_id = null;

  public void onEnable()
  {
    NamespacedKey key = new NamespacedKey(this, "backpack");
    NamespacedKey backpack_id = new NamespacedKey(this, "backpack-id");
    this.backpack = key;
    this.backpack_id = backpack_id;

    ItemStack item = ItemStack.of(Material.CHEST);
    item.setData(DataComponentTypes.ITEM_NAME, Component.text("Backpack"));
    item.setData(DataComponentTypes.MAX_STACK_SIZE, 1);
    item.editPersistentDataContainer(pdc -> {
      // pdc.set(backpack_id, PersistentDataType.STRING, UUID.randomUUID().toString());
      pdc.set(key, PersistentDataType.BOOLEAN, true);
    });

    ShapedRecipe recipe = new ShapedRecipe(key, item);
    recipe.shape("AAA", "ABA", "AAA");
    recipe.setIngredient('A', Material.LEATHER);

    getServer().addRecipe(recipe);
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    LOGGER.info("backpacker enabled");

    instance = this;
  }

  public static Plugin getInstance(){ 
    return instance;
  }

  public void onDisable()
  {
    BagManager.saveInventories();
    LOGGER.info("backpacker disabled");
  }

  public static void log(String message) {
    LOGGER.info(message);
  }
}
