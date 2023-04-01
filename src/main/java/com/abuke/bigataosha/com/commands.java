package com.abuke.bigataosha.com;

import org.bukkit.*;
import org.bukkit.boss.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static com.abuke.bigataosha.Bigataosha.*;
import static com.abuke.bigataosha.Plugin_init.*;

public class commands implements CommandExecutor {



    public Player choosePlayer(@NotNull Collection<? extends Player> players){

        int length = players.size();
        Random random = new Random();
        int tianxuanxihao = random.nextInt(length+1);
        Iterator it = players.iterator();
        Player tianxuanzhizi = null;
        for(int i = 1 ; i <= length ; i++)
        {
            if(i == tianxuanxihao - 1)
            {
                tianxuanzhizi = (Player) it.next();
                break;
            }
            Object obj = it.next();
        }
        return tianxuanzhizi;
    }


    @Contract(pure = true)
    public static @NotNull KeyedBossBar createBossBar(@NotNull NamespacedKey key , @NotNull BarColor color , @NotNull String title , @NotNull BarStyle style, @NotNull BarFlag flags)
    {
        commands server = null;
        return server.createBossBar(key,color,title,style,flags);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        //sender = 指令的执行者
        //command = 指令本身
        //s = 标签
        //strings = 参数
        Collection<? extends Player> players =  Bukkit.getOnlinePlayers();
        if(Bukkit.getOnlinePlayers().size() < 8 && !ifDebugger)
        {
            System.out.println("开始游戏失败，人数不足");
            commandSender.sendMessage("§a开始游戏失败，人数不足");
            if(startgameauto){
                //创建一个BossBar，用于显示人数的百分比
                BossBar bossBar = Bukkit.createBossBar("§b开始游戏", BarColor.GREEN, BarStyle.SOLID);
                bossBar.setProgress(8.0 / Bukkit.getOnlinePlayers().size());
                //新建一个线程，用于更新BossBar的进度
                Thread thread = new Thread(() -> {
                    while (Bukkit.getOnlinePlayers().size() < 8) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        bossBar.setProgress(8.0 / Bukkit.getOnlinePlayers().size());
                    }
                    //当人数达到要求时，执行指令
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "startgame");
                    //销毁BossBar
                    bossBar.removeAll();
                    //结束线程
                    Thread.currentThread().interrupt();
                });
                thread.start();
                //将BossBar添加给所有玩家
                for (Player player : Bukkit.getOnlinePlayers()) {
                    bossBar.addPlayer(player);
                }
                return true;
            }
            return false;
        }
        else if(ifGameStart)
        {
            EndGame("重新开始游戏","");
            onCommand(commandSender,command,s,strings);
            return true;
        }
        else {
            for(Player obj : Bukkit.getOnlinePlayers())
            {
                obj.setGameMode(GameMode.SURVIVAL);
                obj.setHealth(20);
                obj.setFoodLevel(20);
                obj.setExp(0);
                obj.setLevel(0);
                obj.setSaturation(20);
                obj.setExhaustion(0);
                obj.setFireTicks(0);
                obj.setFallDistance(0);
                obj.setRemainingAir(20);
            }
            teamred = scoreboard.registerNewTeam("红队");
            teamred.setPrefix("§c[子洋]");
            teamblue = scoreboard.registerNewTeam("蓝队");
            teamblue.setPrefix("§8[逃脱者]");
            teamTeacher = scoreboard.registerNewTeam("老师");
            teamTeacher.setPrefix("§b[老师]");
            speciallTeam = scoreboard.registerNewTeam("观察者");
            speciallTeam.setPrefix("§e[观察者]");
            ifGameStart = true;
            LastMinute = 30;
            Collection<Player> catcher = new ArrayList<>(); // 初始化catcher集合
            int ChooseTimes = players.size() / 4 - 1;
            for(int index = 1 ; index <= ChooseTimes ; index++)
            {
                Player addPlayer = choosePlayer(players);
                catcher.add(addPlayer);
                try { // 使用try-catch块来处理异常
                    teamred.addEntry(addPlayer.getName());
                    Catchers.add(Bukkit.createBossBar(addPlayer.getName(),BarColor.RED,BarStyle.SOLID));
                    Catcher.add(addPlayer);
                    addPlayer.giveExp(114514);
                    addPlayer.sendMessage("嘘~~~~，你是子洋，你会在20s后出动");
                    players.removeIf(i -> i.equals(addPlayer)); // 使用equals方法来比较对象的内容
                } catch (IllegalArgumentException | IllegalStateException e) {
                    e.printStackTrace();
                }

            }
            for (Player obj : players) {
                try { // 使用try-catch块来处理异常
                    teamblue.addEntry(obj.getName());
                } catch (IllegalArgumentException | IllegalStateException e) {
                    e.printStackTrace();
                }
                for(BossBar i : Catchers){
                    i.addPlayer(obj);
                }

            }
            //在抽选红队和蓝队中间，抽选老师
            Random random = new Random();
            Player teacher = null;
            if(random.nextInt() % 2 == 0)
            {
                teacher = choosePlayer(players);
            }
            else
            {
                teacher = choosePlayer(catcher);
            }
            //让老师离开当前队伍
            if(teacher != null) {
                if (teamblue.hasEntry(teacher.getName())) {
                    teamblue.removeEntry(teacher.getName());
                }
                if (teamred.hasEntry(teacher.getName())) {
                    teamred.removeEntry(teacher.getName());
                }
            }
            if(teacher != null) {
                System.out.println("老师抽选成功");
                teamTeacher.addEntry(teacher.getName());
                teacher.sendMessage("你被招安了，你是老师");
                //清除老师身上的所有物品
                teacher.getInventory().clear();
                //给予老师Key
                teacher.getInventory().addItem(Key);
            }
            else {
                System.out.println("老师抽选失败");
                com.abuke.bigataosha.radio_commands.TitleShow("§c老师抽选失败");
            }
            teamblue.setColor(ChatColor.DARK_BLUE);
            teamred.setColor(ChatColor.DARK_RED);
            teamred.setCanSeeFriendlyInvisibles(true);
            teamblue.setCanSeeFriendlyInvisibles(true);
            teamred.setAllowFriendlyFire(false);
            teamblue.setAllowFriendlyFire(false);

            teamblue.setOption(Team.Option.NAME_TAG_VISIBILITY,Team.OptionStatus.FOR_OWN_TEAM);
            teamred.setOption(Team.Option.NAME_TAG_VISIBILITY,Team.OptionStatus.FOR_OWN_TEAM);
            teamblue.setOption(Team.Option.COLLISION_RULE,Team.OptionStatus.FOR_OTHER_TEAMS);
            teamred.setOption(Team.Option.COLLISION_RULE,Team.OptionStatus.FOR_OTHER_TEAMS);

            teleportPlayers(Player_Spawn_x,Player_Spawn_y,Player_Spawn_z,"§a正在把你传送到鼻嘎游戏场地",commandSender);
            //给予红队所有玩家眩晕，反胃，失明，虚弱效果20s
            for(Player obj : Catcher){
                obj.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,400,114514));
                obj.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,400,114514));
                obj.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,400,114514));
                obj.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,400,114514));
            }
            //给予红队所有玩家物品
            for(Player obj : Catcher){
                obj.getInventory().addItem(Booger);
                obj.getInventory().addItem(BoogerWater);
            }
            //给予蓝队玩家物品
            for(Player obj : players){
                obj.getInventory().addItem(FoundItem);
                obj.getInventory().addItem(CrossBow);
            }
            //扫描从x1，y1，z1到x2，y2，z2的区域，检测是否有箱子，如果有则随机生成定位器和弩
            for(int x = GameArea_x1 ; x <= GameArea_x2 ; x++)
            {
                for(int y = GameArea_y1 ; y <= GameArea_y2 ; y++)
                {
                    for(int z = GameArea_z1 ; z <= GameArea_z2 ; z++)
                    {
                        Location location = new Location(Bukkit.getWorld("world"),x,y,z);
                        if(location.getBlock().getType() == Material.CHEST)
                        {
                            location.getBlock().setType(Material.CHEST);
                            //随机生成定位器和弩，概率为0.2和0.1
                            random = new Random();
                            int tianxuanxihao = random.nextInt(10);
                            if(tianxuanxihao == 1 || tianxuanxihao == 3)
                            {
                                location.getBlock().getWorld().dropItem(location,FoundItem);
                                System.out.println("生成了定位器在" + x + "," + y + "," + z );
                            }
                            else if(tianxuanxihao == 2)
                            {
                                location.getBlock().getWorld().dropItem(location,CrossBow);
                                System.out.println("生成了弩在" + x + "," + y + "," + z );
                            }
                            else if(tianxuanxihao == 4 || tianxuanxihao == 5){
                                location.getBlock().getWorld().dropItem(location,HealthyWater);
                                System.out.println("生成了健康水在" + x + "," + y + "," + z );
                            }
                        }
                        else if(location.getBlock().getType() == Material.BARREL)
                        {
                            location.getBlock().setType(Material.BARREL);
                            //随机生成定位器和弩，概率为0.2和0.1
                            random = new Random();
                            int tianxuanxihao = random.nextInt(10);
                            if(tianxuanxihao == 1 || tianxuanxihao == 3)
                            {
                                location.getBlock().getWorld().dropItem(location,FoundItem);
                                System.out.println("生成了定位器在" + x + "," + y + "," + z );
                            }
                            else if(tianxuanxihao == 2)
                            {
                                location.getBlock().getWorld().dropItem(location,CrossBow);
                                System.out.println("生成了弩在" + x + "," + y + "," + z );
                            }
                            else if(tianxuanxihao == 4 || tianxuanxihao == 5){
                                location.getBlock().getWorld().dropItem(location,HealthyWater);
                                System.out.println("生成了健康水在" + x + "," + y + "," + z );
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
