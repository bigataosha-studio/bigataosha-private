package com.abuke.bigataosha;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.abuke.bigataosha.Bigataosha.*;

public class com_setWorldSpawn implements @Nullable CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length < 3)
        {
            commandSender.sendMessage("§a没有对应的坐标");
            return false;
        }
        else{
            int x = Integer.parseInt(strings[0]);
            int y = Integer.parseInt(strings[1]);
            int z = Integer.parseInt(strings[2]);
            World_Spawn_x = x;
            World_Spawn_y = y;
            World_Spawn_z = z;
            pluginMain.getConfig().set("World_Spawn_x",x);
            pluginMain.getConfig().set("World_Spawn_y",y);
            pluginMain.getConfig().set("World_Spawn_z",z);
            pluginMain.saveConfig();
            commandSender.sendMessage("§a成功设置玩家出生点坐标为" + x + " " + y + " " + z);
            return true;
        }
    }
}
