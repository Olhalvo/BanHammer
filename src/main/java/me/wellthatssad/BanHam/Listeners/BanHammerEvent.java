package me.wellthatssad.BanHam.Listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class BanHammerEvent implements Listener {
    @EventHandler
    public static void onHit(EntityDamageByEntityEvent e) {
        Entity Damager = e.getDamager();
        Entity Banned = e.getEntity();
        if (!(Damager instanceof Player)) {
            return;
        } else {
            Player DamagerP = (Player) Damager;
            ItemStack mainhandItem = DamagerP.getInventory().getItemInMainHand();
            if (mainhandItem.getItemMeta().getLocalizedName().equals("ban_hammer")) {
                if (DamagerP.hasPermission("BanHammer.CanUseBanhammer")) {
                    if (!(Banned instanceof Player)) {
                        DamagerP.sendMessage(ChatColor.RED + "You can't ban a " + Banned.getType().name());
                        e.setCancelled(true);
                    }
                    else{
                        Player BannedP = (Player) Banned;
                        if(BannedP.isOp()){
                            DamagerP.sendMessage(ChatColor.RED + "You can't ban an operator");
                            e.setCancelled(true);
                        }else {
                            Location loc = BannedP.getLocation();
                            World world = BannedP.getWorld();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + BannedP.getDisplayName() + " You have been hit by the ban hammer, literally");
                            DamagerP.sendMessage(ChatColor.GREEN + "You have sucessfully deleted " + BannedP.getDisplayName());
                            Bukkit.broadcastMessage( ChatColor.AQUA + BannedP.getDisplayName() + ChatColor.RESET + ChatColor.GOLD + " Has been hit by the ban hammer, literally");
                            for(Player p : Bukkit.getOnlinePlayers()){
                                p.playSound(p.getLocation(), Sound.ENTITY_CAT_DEATH, 1000000, 100);
                            }
                            world.strikeLightning(loc);
                            e.setCancelled(true);
                        }
                    }
                }
                else{
                    for (int i = 0; i <= 10; i++){
                        DamagerP.getWorld().strikeLightning(DamagerP.getLocation());
                    }
                    DamagerP.damage(Integer.MAX_VALUE);
                    DamagerP.sendMessage(ChatColor.RED + "YOU ARE NOT WORTHY");
                    e.setCancelled(true);
                }
            }
            else{
                return;
            }

        }

    }
}