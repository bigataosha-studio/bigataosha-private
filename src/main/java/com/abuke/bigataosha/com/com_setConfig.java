package com.abuke.bigataosha.com;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.abuke.bigataosha.Bigataosha.LastMinute;
import static com.abuke.bigataosha.Bigataosha.ifDebugger;

public class com_setConfig implements @Nullable CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length < 2){
            commandSender.sendMessage("§4没有参数");
            return false;
        }
        else {
            if(strings[0].equals("LastMinute") && strings[1].length() > 0){
                LastMinute = Integer.parseInt(strings[1]);
                commandSender.sendMessage("§a成功的把LastMinute设置为" + Integer.parseInt(strings[1]));
                return true;
            }
            else if(strings[0].equals("ifDebugger") && strings[1].length() > 0){
                ifDebugger = Boolean.parseBoolean(strings[1]);
                commandSender.sendMessage("§a成功的把ifDebugger设置为" + Boolean.parseBoolean(strings[1]));
                return true;
            }
            else{
                commandSender.sendMessage("§4key错误");
                return false;
            }
        }
    }
}
