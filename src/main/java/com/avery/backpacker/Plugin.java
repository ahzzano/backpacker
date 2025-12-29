package com.avery.backpacker;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
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

  public void onEnable()
  {
    NamespacedKey key = new NamespacedKey(this, "backpack");
    ItemStack item = ItemStack.of(Material.CHEST);
    item.setData(DataComponentTypes.ITEM_NAME, Component.text("Backpack"));

    ShapedRecipe recipe = new ShapedRecipe(key, item);
    recipe.shape("AAA", "ABA", "AAA");
    recipe.setIngredient('A', Material.LEATHER);

    getServer().addRecipe(recipe);
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    LOGGER.info("backpacker enabled");
  }

  public void onDisable()
  {
    LOGGER.info("backpacker disabled");
  }

  public static void log(String message) {
    LOGGER.info(message);
  }
}
