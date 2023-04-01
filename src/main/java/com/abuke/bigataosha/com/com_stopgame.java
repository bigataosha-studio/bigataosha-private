package com.abuke.bigataosha.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.abuke.bigataosha.Bigataosha.*;

public class com_stopgame implements @Nullable CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!ifGameStart){
            commandSender.sendMessage("游戏未开始");
            return true;
        }
        LastMinute = 0;
        CauseReturn = "强制结束游戏";
        EndGame(CauseReturn,"");
        return true;
    }
}
