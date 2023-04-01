package com.abuke.bigataosha.com;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static com.abuke.bigataosha.Bigataosha.*;

public class com_setCalled implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 2){
            if(strings[0].equals("OP")){
                OPs.add(Bukkit.getPlayer(strings[1]));
                pluginMain.getConfig().set("CalledOP",OPs_String);
            }
            else if(strings[0].equals("VIP")){
                VIPs.add(Bukkit.getPlayer(strings[1]));
                pluginMain.getConfig().set("CalledVIP",VIPs_String);
            }
            else if(strings[0].equals("VIPPRO")){
                VIPPROs.add(Bukkit.getPlayer(strings[1]));
                pluginMain.getConfig().set("CalledVIPPRO",VIPPROs_String);
            }
            else if(strings[0].equals("VIPULTRA")){
                VIPULTRAs.add(Bukkit.getPlayer(strings[1]));
                pluginMain.getConfig().set("CalledVIPULTRA",VIPULTRAs_String);
            }
            else if(strings[0].equals("PLUGINMAKERS")){
                PLUGINMAKERs.add(Bukkit.getPlayer(strings[1]));
                pluginMain.getConfig().set("CalledPLUGINMAKERS",PLUGINMAKERs_String);
            }
            else{
                commandSender.sendMessage("参数错误");
                return false;
            }
            pluginMain.saveConfig();
            commandSender.sendMessage("设置成功");
        }
        else{
            commandSender.sendMessage("参数错误");
            return false;
        }
        return true;
    }
}
