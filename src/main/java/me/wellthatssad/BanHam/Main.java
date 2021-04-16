package me.wellthatssad.BanHam;

import me.wellthatssad.BanHam.Commands.GiveBanhammerCommand;
import me.wellthatssad.BanHam.Commands.GiveKickHammerCommand;
import me.wellthatssad.BanHam.Listeners.BanHammerEvent;
import me.wellthatssad.BanHam.Listeners.KickHammerEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getCommand("banhammer").setExecutor(new GiveBanhammerCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new BanHammerEvent(), this);
        this.getCommand("kickhammer").setExecutor(new GiveKickHammerCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new KickHammerEvent(), this);
        System.out.println("Your Mother");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
