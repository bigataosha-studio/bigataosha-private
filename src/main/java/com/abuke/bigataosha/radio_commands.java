package com.abuke.bigataosha;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class radio_commands {
    public static void TitleShow(String msg)
    {
        for(Player player : Bukkit.getOnlinePlayers())
        {
            player.sendTitle(msg,"来自全服广播的消息",1,50,30);
            System.out.println("向" + player.getName() + "广播了" + msg);
        }

    }
}
