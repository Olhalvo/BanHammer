package me.wellthatssad.BanHam;

import me.wellthatssad.BanHam.Commands.GiveBanhammerCommand;
import me.wellthatssad.BanHam.Listeners.BanHammerEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("banhammer").setExecutor(new GiveBanhammerCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new BanHammerEvent(), this);
        System.out.println("Your Mother");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
