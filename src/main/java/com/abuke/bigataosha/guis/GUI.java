package com.abuke.bigataosha.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.abuke.bigataosha.Bigataosha.pluginMain;
import static com.abuke.bigataosha.com.com_returnConfig.returnConfig;

public class GUI implements InventoryHolder {

    public static Inventory GUI = Bukkit.createInventory(null, 27, "§a§l菜单");
    public static void loadGUI(){
        ItemStack item = null;
        ItemMeta meta = null;
        item = new ItemStack(Material.BARREL);
        meta = item.getItemMeta();
        meta.setDisplayName("§a§l退出");
        item.setItemMeta(meta);
        GUI.setItem(0, item);

        item = new ItemStack(Material.DIAMOND_AXE);
        meta = item.getItemMeta();
        meta.setDisplayName("§a§l紫砂");
        item.setItemMeta(meta);
        GUI.setItem(1, item);

        item = new ItemStack(Material.GLASS_PANE);
        meta = item.getItemMeta();
        meta.setDisplayName("§a§l空白");
        item.setItemMeta(meta);
        GUI.setItem(2, item);
        GUI.setItem(3, item);
        GUI.setItem(4, item);
        GUI.setItem(5, item);
        GUI.setItem(6, item);
        GUI.setItem(7, item);
        GUI.setItem(8, item);

        item = new ItemStack(Material.GOLDEN_APPLE);
        meta = item.getItemMeta();
        meta.setDisplayName("服务器TPS:" + String.format("%.2f",Bukkit.getTPS()[0]) +
                "内存占用:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024  + "MB");
        item.setItemMeta(meta);
        GUI.setItem(9, item);

        item = new ItemStack(Material.GOLD_BLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName("§a§l返回数据");
        item.setItemMeta(meta);
        GUI.setItem(10, item);

    }
    public static void openGUI(Player player){
        loadGUI();
        player.openInventory(GUI);
        //检测玩家是否在GUI中点击了物品
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (event.getInventory().getHolder() != GUI.getHolder()) return;
                event.setCancelled(true);
                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getItemMeta() == null) return;
                if (event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§l退出")) {
                    player.closeInventory();
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§l紫砂")) {
                    player.closeInventory();
                    player.setHealth(0);
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a§l返回数据")) {
                    player.closeInventory();
                    returnConfig(player);
                }
            }
            @EventHandler
            public void onInventoryDrag(InventoryDragEvent event) {
                if (event.getInventory().getHolder() != GUI.getHolder()) return;
                event.setCancelled(true);
            }
            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (event.getInventory().getHolder() != GUI.getHolder()) return;
                HandlerList.unregisterAll(this);
            }
        }, pluginMain);
    }
    @Override
    public Inventory getInventory() {
        return GUI;
    }
}
