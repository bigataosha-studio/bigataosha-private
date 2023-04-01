package com.abuke.bigataosha;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Arrays;
import java.util.Objects;

import static com.abuke.bigataosha.LangHelper.*;

public class Plugin_init {
    public static ItemStack SpeedPlusApple = null;
    public static ItemMeta SpeedPlusAppleMeta = null;
    public static ItemStack Booger = null;
    public static ItemMeta BoogerMeta = null;
    public static ItemStack BoogerWater = null;
    public static ItemMeta BoogerWaterMeta = null;
    public static ItemStack CrossBow = null;
    public static ItemMeta CrossBowMeta = null;
    public static ItemStack FoundItem = null;
    public static ItemMeta FoundItemMeta = null;
    public static ItemStack Key = null;
    public static ItemMeta KeyMeta = null;
    public static ItemStack HealthyWater = null;
    public static ItemMeta HealthyWaterMeta = null;
    public static ItemStack HomeWork = null;
    public static ItemMeta HomeWorkMeta = null;
    public static void Init_Value()
    {
        //加速苹果
        SpeedPlusApple = new ItemStack(Material.APPLE);
        SpeedPlusAppleMeta = SpeedPlusApple.getItemMeta();
        SpeedPlusAppleMeta.setDisplayName(String_Item_SpeedPlusApple);
        SpeedPlusAppleMeta.setLore(Arrays.asList(String_Item_SpeedPlusApple_Description));
        SpeedPlusApple.setItemMeta(SpeedPlusAppleMeta);
        //弩
        CrossBow = new ItemStack(Material.CROSSBOW);
        CrossBowMeta = CrossBow.getItemMeta();
        CrossBowMeta.setDisplayName(String_Name_Catcher);
        CrossBowMeta.setLore(Arrays.asList(String_Item_CrossBow_Description));
        CrossBow.setItemMeta(CrossBowMeta);
        //鼻涕
        Booger = new ItemStack(Material.SLIME_BALL);
        BoogerMeta = Booger.getItemMeta();
        BoogerMeta.setDisplayName(String_Item_Booger);
        BoogerMeta.setLore(Arrays.asList(String_Item_Booger_Description));
        Booger.setItemMeta(BoogerMeta);
        //鼻涕水
        BoogerWater = new ItemStack(Material.POTION);
        BoogerWaterMeta = BoogerWater.getItemMeta();
        BoogerWaterMeta.setDisplayName(String_Item_BoogerWater);
        BoogerWaterMeta.setLore(Arrays.asList(String_Item_BoogerWater_Description));
        BoogerWater.setItemMeta(BoogerWaterMeta);
        //搜寻器
        FoundItem = new ItemStack(Material.COMPASS);
        FoundItemMeta = FoundItem.getItemMeta();
        FoundItemMeta.setDisplayName(String_Item_FoundItem);
        FoundItemMeta.setLore(Arrays.asList(String_Item_FoundItem_Description));
        FoundItem.setItemMeta(FoundItemMeta);
        //钥匙
        Key = new ItemStack(Material.TRIPWIRE_HOOK);
        KeyMeta = Key.getItemMeta();
        KeyMeta.setDisplayName(String_Item_Key);
        KeyMeta.setLore(Arrays.asList(String_Item_Key_Description));
        Key.setItemMeta(KeyMeta);
        //把钥匙设置成不可放置
        Key.setAmount(0);
        //健康水
        HealthyWater = new ItemStack(Material.POTION);
        HealthyWaterMeta = HealthyWater.getItemMeta();
        HealthyWaterMeta.setDisplayName(String_Item_HealthyWater);
        HealthyWaterMeta.setLore(Arrays.asList(String_Item_HealthyWater_Description));
        HealthyWater.setItemMeta(HealthyWaterMeta);
        //作业
        HomeWork = new ItemStack(Material.PAPER);
        HomeWorkMeta = HomeWork.getItemMeta();
        HomeWorkMeta.setDisplayName(String_Item_HomeWork);
        HomeWorkMeta.setLore(Arrays.asList(String_Item_HomeWork_Description));
        HomeWork.setItemMeta(HomeWorkMeta);
    }
}
