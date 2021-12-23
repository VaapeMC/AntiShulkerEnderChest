package me.vaape.antishulkerenderchest;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiShulkerEnderchest extends JavaPlugin implements Listener {

    public static AntiShulkerEnderchest plugin;

    public void onEnable() {
        plugin = this;
        getLogger().info(ChatColor.GREEN + "AntiShulkerEnderchest has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        plugin = null;
    }

    public static AntiShulkerEnderchest getInstance() {
        return plugin;
    }

    @EventHandler
    public void onHit(InventoryClickEvent event) {

        // || event.getView().getTitle().startsWith("&9&l")

        if (event.getInventory().getType() == InventoryType.ENDER_CHEST) {

            ItemStack cursor = event.getCursor();
            ItemStack currentItem = event.getCurrentItem();

            if (currentItem != null) {
                if (currentItem.getI18NDisplayName().toLowerCase().contains("shulker box")) {
                    event.getWhoClicked().sendMessage(ChatColor.RED + "You cannot keep shulker boxes in your " +
                                                              "enderchest.");
                    event.setCancelled(true);
                }
            }

            if (cursor != null) {
                if (cursor.getI18NDisplayName().toLowerCase().contains("shulker box")) {
                    event.getWhoClicked().sendMessage(ChatColor.RED + "You cannot keep shulker boxes in your enderchest.");
                    event.setCancelled(true);
                }
            }

            if (event.getClick().equals(ClickType.NUMBER_KEY)) {
                event.setCancelled(true);
            }
        }
    }
}
