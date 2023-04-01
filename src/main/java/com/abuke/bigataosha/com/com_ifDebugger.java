package com.abuke.bigataosha;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import static com.abuke.bigataosha.Bigataosha.ifDebugger;

public class com_ifDebugger implements @Nullable CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ifDebugger = true;
        commandSender.sendMessage("§a已开启调试模式");
        return true;
    }
}
