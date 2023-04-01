package com.abuke.bigataosha;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static com.abuke.bigataosha.Bigataosha.*;
import static com.abuke.bigataosha.LangHelper.*;
import static com.abuke.bigataosha.Plugin_init.*;

public class MyListener implements Listener {
    //当玩家聊天时，检测有没有@玩家，如果有，那么给玩家发送消息
    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        String message = event.toString();
        if (message.contains("@")) {
            String[] split = message.split("@");
            String name = split[1];
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(name)) {
                    player.sendMessage("§c" + event.getPlayer().getName() + "§c对你说: " + split[0]);
                    event.getPlayer().sendMessage("§c你对" + player.getName() + "§c说: " + split[0]);
                    event.setCancelled(true);
                }
            }
        }
    }
    //当玩家死亡时，检测死亡的玩家的队伍，如果当前队伍没有玩家了，那么结束游戏，如果是，那么给在服务器中的所有玩家发送死亡消息
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if(!ifGameStart){
            return;
        }
        Player player = event.getEntity();
        //把玩家移出队伍
        Team team = scoreboard.getEntryTeam(player.getName());
        team.removeEntry(player.getName());
        speciallTeam.addEntry(player.getName());
        //把玩家转变为观察者
        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        if (team.getSize() == 0) {
            if (team.getName().equals("红队")) {
                EndGame("", String_Name_Runner);
            } else if(team.getName().equals("蓝队")) {
                EndGame("", String_Name_Catcher);
            }
            else{
                return;
            }
        }

    }
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        // 获取玩家消耗的物品
        ItemStack item = event.getItem();
        // 判断是否是加速苹果
        if (item.getItemMeta() == SpeedPlusAppleMeta) {
            // 给予玩家速度V效果15秒
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15 * 20, 4));
        }
        return;
    }
    //当玩家射出箭时，检测击中的玩家是不是在对方队伍，如果是则给予击退失明缓慢效果10s
    @EventHandler
    public void onArrowHit(org.bukkit.event.entity.ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Entity entity = event.getHitEntity();
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                if (player.getScoreboard().getEntryTeam(player.getName()) != hitPlayer.getScoreboard().getEntryTeam(hitPlayer.getName()) && hitPlayer.teamDisplayName() != teamTeacher.displayName()) {
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * 20, 1));
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10 * 20, 1));
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10 * 20, 1));
                    hitPlayer.setVelocity(player.getLocation().getDirection().multiply(-1).setY(0.5));
                }
                else if(hitPlayer.teamDisplayName() != teamTeacher.displayName()){
                    player.sendMessage("§c你不能打老师，老师敲了你一棒");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40 * 20, 114514));
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40 * 20, 1));
                }
            }
        }
    }
    //当玩家扔出雪球时，检测击中的玩家是不是在对方队伍，如果是，则给予缓慢效果10s，并且对这个玩家造成5点伤害，并广播被击中的玩家
    @EventHandler
    public void onSnowballHit(org.bukkit.event.entity.ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Entity entity = event.getHitEntity();
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                if (player.getScoreboard().getEntryTeam(player.getName()) != hitPlayer.getScoreboard().getEntryTeam(hitPlayer.getName())) {
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10 * 20, 1));
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10 * 20, 1));
                    hitPlayer.damage(5);
                    player.sendMessage("你击中了" + hitPlayer.getName() + "剩余血量" + hitPlayer.getHealth());
                }
            }
        }
    }
    //当玩家扔出喷溅型水瓶时，检测击中的玩家是不是在对方队伍，如果是，则给予反胃效果10s，并且对这个玩家造成5点伤害，并广播被击中的玩家
    @EventHandler
    public void onSplashPotionHit(org.bukkit.event.entity.ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Entity entity = event.getHitEntity();
            if (entity instanceof Player) {
                Player hitPlayer = (Player) entity;
                if (player.getScoreboard().getEntryTeam(player.getName()) != hitPlayer.getScoreboard().getEntryTeam(hitPlayer.getName())) {
                    hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 1));
                    hitPlayer.damage(5);
                    player.sendMessage("你击中了" + hitPlayer.getName() + "剩余血量" + hitPlayer.getHealth());
                }
            }
        }
    }
    //当玩家拿着指南针右键时，在玩家脚底下生成一个骷髅马，把骷髅马隐身，使指南针一直指向骷髅马
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == FoundItem.getType()) {
                    if (event.getItem().getItemMeta() == FoundItemMeta) {
                        event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.SKELETON_HORSE);
                        //给予骷髅马一个NBT标签，用于区分玩家生成的骷髅马
                        for (Entity entity : event.getPlayer().getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof SkeletonHorse) {
                                entity.setCustomName(event.getPlayer().getName() + "的骷髅马");
                            }
                        }
                        //当玩家生成的骷髅马出现时，把骷髅马隐身，使指南针一直指向骷髅马
                        for (Entity entity : event.getPlayer().getNearbyEntities(10, 10, 10)) {
                            if (entity instanceof SkeletonHorse) {
                                entity.setInvulnerable(true);
                                entity.setSilent(true);
                                entity.setCustomNameVisible(false);
                            }
                        }

                    }
                }
            }
        }
    }
    //当玩家生成的骷髅马消失时，把玩家的指南针也消失
    @EventHandler
    public void onHorseRemove(EntityDeathEvent event) {
        if (event.getEntity() instanceof SkeletonHorse) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getInventory().getItemInMainHand().getType() == FoundItem.getType()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta() == FoundItemMeta) {
                        player.getInventory().removeItem(FoundItem);
                    }
                }
            }
        }
    }
    //当玩家碰到骷髅马时，检测是否是红队，如果是，则使得骷髅马一直跟随玩家，持续3分钟，三分钟后骷髅马消失
    @EventHandler
    public void onPlayerTouchHorse(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getScoreboard().getEntryTeam(player.getName()).getDisplayName().equals("§c红队")) {
                if (event.getEntity() instanceof SkeletonHorse) {
                    SkeletonHorse horse = (SkeletonHorse) event.getEntity();
                    horse.setPassenger(player);
                    Bukkit.getScheduler().runTaskLater(pluginMain, new Runnable() {
                        @Override
                        public void run() {
                            horse.remove();
                        }
                    }, 3 * 60 * 20);
                }
            }
        }
    }
    //当玩家受到摔落伤害时，减免此次伤害
    @EventHandler
    public void onPlayerFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                event.setCancelled(true);
            }
        }
    }
    //当玩家拿着拌线钩右键门时，把门替换成铁门，并记录门的位置，门的原样子，门的方向在NBT标签中
    @EventHandler
    public void onPlayerRightClickDoor(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (event.getItem().getType() == Key.getType()) {
                    if (event.getItem().getItemMeta() == KeyMeta) {
                        if (event.getClickedBlock().getType() == Material.OAK_DOOR) {
                            BrokenDoors.add(new BlockData(event.getClickedBlock(), event.getClickedBlock().getType(), event.getClickedBlock().getBlockData()));
                            event.getClickedBlock().setType(Material.IRON_DOOR);
                            //给予铁门一个NBT标签，用于区分玩家生成的铁门
                            for (Entity entity : event.getPlayer().getNearbyEntities(10, 10, 10)) {
                                if (entity.getLocation().getBlock().getType() == Material.IRON_DOOR) {
                                    entity.setCustomName(event.getPlayer().getName() + "的铁门");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //当老师拿着作业攻击玩家时，减免玩家收到的伤害，并给予玩家缓慢VII1分钟和反胃VII20s
    @EventHandler
    public void onTeacherAttackPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getScoreboard().getEntryTeam(player.getName()).getDisplayName().equals("§c红队")) {
                if (event.getEntity() instanceof Player) {
                    Player hitPlayer = (Player) event.getEntity();
                    if (player.getInventory().getItemInMainHand().getType() == HomeWork.getType()) {
                        if (player.getInventory().getItemInMainHand().getItemMeta() == HomeWorkMeta) {
                            event.setCancelled(true);
                            hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60 * 20, 6));
                            hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 6));
                        }
                    }
                }
            }
        }
    }
}
