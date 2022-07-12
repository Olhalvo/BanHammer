package me.wellthatssad.BanHam.Listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class KickHammerEvent implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Entity Damager = e.getDamager();
        Entity Banned = e.getEntity();
        if (Damager instanceof Player) {
            Player DamagerP = (Player) Damager;
            if (DamagerP.getInventory().getItemInMainHand() != null
                    && DamagerP.getInventory().getItemInMainHand().getType() != Material.AIR
                    && DamagerP.getInventory().getItemInMainHand().hasItemMeta()
                    && DamagerP.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("kick_hammer"))
            {
                if (DamagerP.hasPermission("BanHammer.CanUseBanhammer")) {
                    if (!(Banned instanceof Player)) {
                        DamagerP.sendMessage(ChatColor.RED + "You can't kick a " + Banned.getType().name());

                    }
                    else{
                        Player BannedP = (Player) Banned;
                        if(BannedP.isOp()){
                            DamagerP.sendMessage(ChatColor.RED + "You can't kick an operator");

                        }else {
                            Location loc = BannedP.getLocation();
                            World world = BannedP.getWorld();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kick " + BannedP.getDisplayName());
                            DamagerP.sendMessage(ChatColor.GREEN + "You have sucessfully punished " + BannedP.getDisplayName());
                            Bukkit.broadcastMessage( ChatColor.AQUA + BannedP.getDisplayName() + ChatColor.RESET + ChatColor.GOLD + " Has been hit by the kick hammer, literally");
                            for(Player p : Bukkit.getOnlinePlayers()){
                                p.playSound(p.getLocation(), Sound.AMBIENT_CAVE , 1000000, 100);
                            }
                            world.strikeLightning(loc);

                        }
                    }
                }
                else{
                    for (int i = 0; i <= 5; i++){
                        DamagerP.getWorld().strikeLightning(DamagerP.getLocation());
                    }
                    DamagerP.damage(Integer.MAX_VALUE);
                    DamagerP.sendMessage(ChatColor.RED + "YOU ARE NOT WORTHY");

                }
                e.setCancelled(true);
            }
        }

    }
}