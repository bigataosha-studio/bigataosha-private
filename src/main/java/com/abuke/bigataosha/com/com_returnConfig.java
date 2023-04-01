package com.abuke.bigataosha.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.abuke.bigataosha.Bigataosha.*;

public class com_returnConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage("玩家出生点信息:");
        commandSender.sendMessage("PlayerSpawn_x(出生点x):" + Player_Spawn_x);
        commandSender.sendMessage("PlayerSpawn_y(出生点y):" + Player_Spawn_y);
        commandSender.sendMessage("PlayerSpawn_z(出生点z):" + Player_Spawn_z);
        commandSender.sendMessage("世界出生点信息:");
        commandSender.sendMessage("WorldSpawn_x(出生点x):" + World_Spawn_x);
        commandSender.sendMessage("WorldSpawn_y(出生点y):" + World_Spawn_y);
        commandSender.sendMessage("WorldSpawn_z(出生点z):" + World_Spawn_z);
        commandSender.sendMessage("游戏剩余时间:");
        commandSender.sendMessage(format0p2(LastMinute));
        return true;
    }
    public static void returnConfig(Player player){
        player.sendMessage("玩家出生点信息:");
        player.sendMessage("PlayerSpawn_x(出生点x):" + Player_Spawn_x);
        player.sendMessage("PlayerSpawn_y(出生点y):" + Player_Spawn_y);
        player.sendMessage("PlayerSpawn_z(出生点z):" + Player_Spawn_z);
        player.sendMessage("世界出生点信息:");
        player.sendMessage("WorldSpawn_x(出生点x):" + World_Spawn_x);
        player.sendMessage("WorldSpawn_y(出生点y):" + World_Spawn_y);
        player.sendMessage("WorldSpawn_z(出生点z):" + World_Spawn_z);
        player.sendMessage("游戏剩余时间:");
        player.sendMessage(format0p2(LastMinute));
    }
}
