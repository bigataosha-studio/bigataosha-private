package com.abuke.bigataosha.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.abuke.bigataosha.Bigataosha.ifGameStart;
import static com.abuke.bigataosha.guis.GUI.openGUI;

public class com_cd implements @Nullable CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(ifGameStart){
            commandSender.sendMessage("§4游戏已经开始,你不能打开菜单了");
            return true;
        }
        try {
            openGUI((Player) commandSender);
            return true;
        }
        catch (Exception e){
            commandSender.sendMessage("§4你不是玩家");
        }
        return false;
    }
}
