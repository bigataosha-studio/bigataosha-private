package com.abuke.bigataosha;

import com.abuke.bigataosha.com.commands;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.abuke.bigataosha.LangHelper.*;
import static com.abuke.bigataosha.radio_commands.TitleShow;

//颜色用法
//§0黑色
//§1深蓝
//§2深绿
//§3天蓝
//§4红色
//§5深紫
//§6金黄
//§7浅灰
//§8深灰
//§9淡紫
//§a浅绿
//§b淡蓝
//§c淡红
//§d淡紫
//§e淡黄
//§f白色
//§l加粗
//§m斜体
//§n下划线
//
public final class Bigataosha extends JavaPlugin {
    public static Plugin instance;
    //创建一个结构体，包含一个Block，一个BlockType，一个BlockData
    public static class BlockData{
        public Block block;
        public Material blockType;
        public org.bukkit.block.data.BlockData blockData;
        public BlockData(Block block, Material blockType, org.bukkit.block.data.BlockData blockData){
            this.block = block;
            this.blockType = blockType;
            this.blockData = blockData;
        }
    }
    public static class PlayerScores{
        public Player player;
        public int scores;
        public PlayerScores(Player player, int scores){
            this.player = player;
            this.scores = scores;
        }
        public void setScores(int scores){
            this.scores = scores;
        }
        public int getScores(){
            return this.scores;
        }
        public Player getPlayer(){
            return this.player;
        }
        public void addScores(int scores){
            this.scores += scores;
        }
        public double getScore(){
            return scores / 10000.0 + player.getName().length();
        }
    }
    public static class PlayerCalled{
        public Player player;
        public String called;
        public PlayerCalled(Player player, String string_Called_PLUGINMAKER){
            this.player = player;
            this.called = called;
        }
        public void setCalled(String called){
            this.called = called;
        }
        public String getCalled(){
            return this.called;
        }
        public Player getPlayer(){
            return this.player;
        }
    }
    public boolean cmp(PlayerScores a, PlayerScores b){
        return a.getScores() > b.getScores();
    }
    public static List<PlayerCalled> PlayerCalledList = new ArrayList<>();
    public static List<PlayerScores> paihang;
    public static List<BlockData> BrokenDoors;
    public static List<Player> VIPs = null;
    public static List<Player> VIPPROs = null;
    public static List<Player> VIPULTRAs = null;
    public static List<Player> OPs = null;
    public static List<Player> PLUGINMAKERs = null;
    public static Team teamred = null;
    public static Team teamblue = null;
    public static Team showTeam = null;
    public static Team speciallTeam = null;
    public static Team HealthShow = null;
    public static Team TPS = null;
    public static Team teamTeacher = null;
    public static Team VIP = null;
    public static Team VIPPRO = null;
    public static Team VIPULTRA = null;
    public static Team OP = null;
    public static Team PLUGINMAKER = null;
    public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    public static Scoreboard scoreboard = null;
    public static double LastMinute = 114514;
    public static int Player_Spawn_x = 0;
    public static int Player_Spawn_y = 0;
    public static int Player_Spawn_z = 0;
    public static int World_Spawn_x = 0;
    public static int World_Spawn_y = 0;
    public static int World_Spawn_z = 0;
    public static int Teacher_Spawn_x = 0;
    public static int Teacher_Spawn_y = 0;
    public static int Teacher_Spawn_z = 0;
    //创建两个List，用于记录从Config中来的玩家名字和分数
    List<String> PlayerNames = new ArrayList<>();
    List<Integer> PlayerScores = new ArrayList<>();
    public static List<String> VIPs_String = new ArrayList<>();
    public static List<String> VIPPROs_String = new ArrayList<>();
    public static List<String> VIPULTRAs_String = new ArrayList<>();
    public static List<String> OPs_String = new ArrayList<>();
    public static List<String> PLUGINMAKERs_String = new ArrayList<>();
    //游戏区域
    public static int GameArea_x1 = 0;
    public static int GameArea_z1 = 0;
    public static int GameArea_x2 = 0;
    public static int GameArea_z2 = 0;
    public static int GameArea_y1 = 0;
    public static int GameArea_y2 = 0;
    public static String CauseReturn = null;
    public static boolean ifGameStart = false;
    public static boolean ifDebugger = false;
    public static Server server = null;
    public static Plugin pluginMain = null;
    public static List<BossBar> Catchers = null;
    public static List<Player> Catcher = null;
    //定义三个NPC，用于显示前三名的玩家
    public static NPC npc1 = null;
    public static NPC npc2 = null;
    public static NPC npc3 = null;
    // 定义一个File对象，表示yml文件
    public static File file;
    // 定义一个FileConfiguration对象，表示yml文件中的数据
    public static FileConfiguration config;
    public static String format0p2(double value){
        return String.format("%.2f",value);
    }
    //
    public void Init(){
        pluginMain = this;
        com.abuke.bigataosha.LangHelper.Init();
        Catchers = new ArrayList<>();
        Catcher = new ArrayList<>();
        //指令绑定
        Objects.requireNonNull(Bukkit.getPluginCommand("startinggame")).setExecutor(new commands());
        Objects.requireNonNull(Bukkit.getPluginCommand("setPlayerSpawn")).setExecutor(new com.abuke.bigataosha.com_setPlayerSpawn());
        Objects.requireNonNull(Bukkit.getPluginCommand("stopgame")).setExecutor(new com.abuke.bigataosha.com.com_stopgame());
        Objects.requireNonNull(Bukkit.getPluginCommand("returnConfig")).setExecutor(new com.abuke.bigataosha.com.com_returnConfig());
        Objects.requireNonNull(Bukkit.getPluginCommand("startdebugger")).setExecutor(new com.abuke.bigataosha.com_ifDebugger());
        Objects.requireNonNull(Bukkit.getPluginCommand("setWorldSpawn")).setExecutor(new com.abuke.bigataosha.com_setWorldSpawn());
        Objects.requireNonNull(Bukkit.getPluginCommand("setConfig")).setExecutor(new com.abuke.bigataosha.com.com_setConfig());
        Objects.requireNonNull(Bukkit.getPluginCommand("cd")).setExecutor(new com.abuke.bigataosha.com.com_cd());
        Objects.requireNonNull(Bukkit.getPluginCommand("setCalled")).setExecutor(new com.abuke.bigataosha.com.com_setCalled());
        //称号
        VIPs_String = pluginMain.getConfig().getStringList("CalledVIP");
        VIPPROs_String = pluginMain.getConfig().getStringList("CalledVIPPRO");
        VIPULTRAs_String = pluginMain.getConfig().getStringList("CalledVIPULTRA");
        OPs_String = pluginMain.getConfig().getStringList("CalledOP");
        PLUGINMAKERs_String = pluginMain.getConfig().getStringList("CalledPLUGINMAKER");
        for(String obj : VIPs_String){
            VIPs.add(Bukkit.getPlayer(obj));
        }
        for(String obj : VIPPROs_String)
        {
            VIPPROs.add(Bukkit.getPlayer(obj));
        }
        for(String obj : VIPULTRAs_String)
        {
            VIPULTRAs.add(Bukkit.getPlayer(obj));
        }
        for(String obj : OPs_String)
        {
            OPs.add(Bukkit.getPlayer(obj));
        }
        for(String obj : PLUGINMAKERs_String)
        {
            PLUGINMAKERs.add(Bukkit.getPlayer(obj));
        }
        //绑定监听器
        Bukkit.getPluginManager().registerEvents(new com.abuke.bigataosha.MyListener(), this);
        //计分板
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        System.out.println(String_Message_ScoreBoard_LoadSuccseefully);
        PLUGINMAKER = scoreboard.registerNewTeam("PLUGINMAKER");
        OP = scoreboard.registerNewTeam("OP");
        VIP = scoreboard.registerNewTeam("VIP");
        VIPPRO = scoreboard.registerNewTeam("VIPPRO");
        VIPULTRA = scoreboard.registerNewTeam("VIPULTRA");
        PLUGINMAKER.setPrefix(String_Called_PLUGINMAKER);
        OP.setPrefix(String_Called_OP);
        VIP.setPrefix(String_Called_VIP);
        VIPPRO.setPrefix(String_Called_VIPPRO);
        VIPULTRA.setPrefix(String_Called_VIPULTRA);

        //变量
        Player_Spawn_x = pluginMain.getConfig().getInt("Player_Spawn_x");
        Player_Spawn_y = pluginMain.getConfig().getInt("Player_Spawn_y");
        Player_Spawn_z = pluginMain.getConfig().getInt("Player_Spawn_z");
        World_Spawn_x = pluginMain.getConfig().getInt("World_Spawn_x");
        World_Spawn_y = pluginMain.getConfig().getInt("World_Spawn_y");
        World_Spawn_z = pluginMain.getConfig().getInt("World_Spawn_z");
        ifDebugger = pluginMain.getConfig().contains("IsDebugger");
        Teacher_Spawn_x = pluginMain.getConfig().getInt("Teacher_Spawn_x");
        Teacher_Spawn_y = pluginMain.getConfig().getInt("Teacher_Spawn_y");
        Teacher_Spawn_z = pluginMain.getConfig().getInt("Teacher_Spawn_z");
        PlayerNames = pluginMain.getConfig().getStringList("PlayerName");
        PlayerScores = pluginMain.getConfig().getIntegerList("PlayerScore");
        for(int i = 0; i < PlayerNames.size(); i++){
            paihang.add(new PlayerScores(Bukkit.getPlayer(PlayerNames.get(i)), PlayerScores.get(i)));
        }
        //如果在线玩家中有不在列表里的玩家，就把他加入列表
        for (Player player: Bukkit.getOnlinePlayers()) {
            if (!PlayerNames.contains(player.getName())) {
                PlayerNames.add(player.getName());
                PlayerScores.add(0);
                paihang.add(new PlayerScores(player, 0));
                pluginMain.getConfig().set("PlayerName", PlayerNames);
                pluginMain.getConfig().set("PlayerScore", PlayerScores);
                pluginMain.saveConfig();
            }
        }
        //玩家称号

        //游戏区域
        GameArea_x1 = pluginMain.getConfig().getInt("GameArea_x1");
        GameArea_z1 = pluginMain.getConfig().getInt("GameArea_z1");
        GameArea_x2 = pluginMain.getConfig().getInt("GameArea_x2");
        GameArea_z2 = pluginMain.getConfig().getInt("GameArea_z2");
        GameArea_y1 = pluginMain.getConfig().getInt("GameArea_y1");
        GameArea_y2 = pluginMain.getConfig().getInt("GameArea_y2");
        server = Bukkit.getServer();
        com.abuke.bigataosha.Plugin_init.Init_Value();
        Objects.requireNonNull(server.getWorld("world")).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Objects.requireNonNull(server.getWorld("world")).setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        Objects.requireNonNull(server.getWorld("world")).setGameRule(GameRule.NATURAL_REGENERATION, false);
        Objects.requireNonNull(server.getWorld("world")).setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, true);
        Objects.requireNonNull(server.getWorld("world")).setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
        //玩家称号
        for(Player obj : VIPs){
            VIP.addEntry(obj.getName());
        }
        for(Player obj : VIPPROs){
            VIPPRO.addEntry(obj.getName());
        }
        for(Player obj : VIPULTRAs){
            VIPULTRA.addEntry(obj.getName());
        }
        for(Player obj : OPs){
            OP.addEntry(obj.getName());
        }
        for(Player obj : PLUGINMAKERs){
            PLUGINMAKER.addEntry(obj.getName());
        }
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\n" +
                "__________.__  ________        ____  __.___.____    .____     \n" +
                "\\______   \\__|/  _____/_____  |    |/ _|   |    |   |    |    \n" +
                " |    |  _/  /   \\  ___\\__  \\ |      < |   |    |   |    |    \n" +
                " |    |   \\  \\    \\_\\  \\/ __ \\|    |  \\|   |    |___|    |___ \n" +
                " |______  /__|\\______  (____  /____|__ \\___|_______ \\_______ \\\n" +
                "        \\/           \\/     \\/        \\/           \\/       \\/");
        System.out.println("Loading 0%");
        Init();
        System.out.println("Loading 80%");
        refreshScoreboard();
        System.out.println("Loading 95%");
        saveDefaultConfig();
        System.out.println("Loading 100%");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Saving Config...");
        saveDefaultConfig();
        System.out.println("Saving Config...Done");
        System.out.println(pluginMain.getConfig().getString("String_PluginShutdown"));
    }
    //字符串保留前3位
    public static String save3(String value){
        return value.substring(0,3);
    }
    // 更新计分板的内容和显示
    public static void updateScoreboard() {
        // 对每个在线玩家执行以下操作

        for (Player player : Bukkit.getOnlinePlayers()) {
            Objective objective;
            Date date = new Date();
            objective = scoreboard.registerNewObjective(save3(player.getName())+ date.hashCode(), "dummy","§m§l§4" + String_GameName);
            Score line2_3 = objective.getScore("§l§e欢迎来到子洋追杀！");
            line2_3.setScore(-1);
            // 获取第三行的得分对象，并设置得分为-2（不影响显示）
            Score line3 = objective.getScore("§6" + String_Message_SystemMsg_LatestTime + "：");
            line3.setScore(-2);
            showTeam.addEntry("§6" + String_Message_SystemMsg_LatestTime + "：");
            showTeam.setSuffix(format0p2(LastMinute));
            Score TPs = objective.getScore("§a服务器TPS：");
            TPS.addEntry("§a服务器TPS：");
            TPS.setSuffix(format0p2(server.getTPS()[0]));
            TPs.setScore(-3);
            Score line5 = objective.getScore("§4" + String_Message_Default_haveagoodtime);
            line5.setScore(-4);
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            //System.out.println(String_Message_Default_to + player.getName() + String_Message_ScoreBoard_SendBoardSuccessfully);
            // 将计分板推送给玩家
            player.setScoreboard(scoreboard);
        }
    }
    public static void updateBossBar(){
        int i = 0;
        for(BossBar obj : Catchers){
            obj.setProgress(Catcher.get(i).getHealth() / 20.0);
            i++;
        }
    }
    // 每过1秒刷新一次计分板，并减少剩余时间变量
    public void refreshScoreboard() {
        showTeam = scoreboard.registerNewTeam("showTeam");
        HealthShow = scoreboard.registerNewTeam("HealthyTeam");
        TPS = scoreboard.registerNewTeam("TPSTeam");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                updateScoreboard();
                updateBossBar();
                if(ifGameStart)
                {
                    LastMinute -= 1/60.0;
                }
                if(LastMinute <= 0)
                {
                    EndGame("",String_Name_Runner);
                    ifGameStart = false;

                }
            }

        }, 0L, 20L);

    }
    // 传送所有玩家到指定位置
    public static void teleportPlayers(double x, double y, double z, String message, CommandSender commandSender) {
        // 获取服务器中所有在线玩家的数组
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        // 遍历每个玩家
        for (Player player : players) {
            // 创建一个新的位置对象，用给定的坐标和玩家所在世界
            Location location = new Location(player.getWorld(), x, y, z);
            // 调用玩家的teleport方法，传入位置对象
            player.teleport(location);
            if(commandSender != null)
                TitleShow("§a" + String_Message_Default_Already + String_Message_Default_Successfully +"把" + player.getName() + String_Message_Command_Tp);
            player.sendMessage(message);
        }
    }
    //结束游戏
    public static void EndGame(String msg,String winner){
        if(winner.equals(String_Name_Catcher))
        {
            TitleShow("§4§l" + String_Name_Catcher + String_Message_Default_win);

        }
        else if(winner.equals(String_Name_Runner))
        {
            TitleShow("§3§l" + String_Name_Runner + String_Message_Default_win);
        }
        else
        {
            TitleShow("§4§m" + msg);
        }
        teamblue.unregister();
        teamred.unregister();
        teamTeacher.unregister();
        speciallTeam.unregister();
        ifGameStart = false;
        LastMinute = 114514;
        CommandSender commandSender = null;
        for(Player obj : Bukkit.getOnlinePlayers())
        {
            obj.setGameMode(GameMode.SURVIVAL);
            //清除玩家背包
            obj.getInventory().clear();
        }
        OP.setPrefix(String_Called_OP);
        VIP.setPrefix(String_Called_VIP);
        VIPPRO.setPrefix(String_Called_VIPPRO);
        VIPULTRA.setPrefix(String_Called_VIPULTRA);
        for(Player obj : VIPs){
            VIP.addEntry(obj.getName());
        }
        for(Player obj : VIPPROs){
            VIPPRO.addEntry(obj.getName());
        }
        for(Player obj : VIPULTRAs){
            VIPULTRA.addEntry(obj.getName());
        }
        for(Player obj : OPs){
            OP.addEntry(obj.getName());
        }
        for(Player obj : PLUGINMAKERs){
            PLUGINMAKER.addEntry(obj.getName());
        }
        teleportPlayers(World_Spawn_x,World_Spawn_y,World_Spawn_z,"正在把你传送到世界出生点", null);
    }

}
