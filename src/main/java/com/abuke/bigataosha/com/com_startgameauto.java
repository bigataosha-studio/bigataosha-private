package com.abuke.bigataosha.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static com.abuke.bigataosha.Bigataosha.startgameauto;

public class com_startgameauto implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(startgameauto == false){
            startgameauto = true;
            commandSender.sendMessage("§a[Bigataosha] §b自动开始游戏已开启");
        }else{
            startgameauto = false;
            commandSender.sendMessage("§a[Bigataosha] §b自动开始游戏已关闭");
        }
        return true;
    }
}
