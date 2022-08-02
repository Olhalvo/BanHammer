package me.wellthatssad.BanHam;

import me.wellthatssad.BanHam.Commands.GiveBanhammerCommand;
import me.wellthatssad.BanHam.Commands.GiveKickHammerCommand;
import me.wellthatssad.BanHam.Listeners.BanHammerEvent;
import me.wellthatssad.BanHam.Listeners.KickHammerEvent;
import me.wellthatssad.BanHam.extras.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.DrilldownPie;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public UpdateChecker updateChecker;
    public Metrics metrics;
    public final Logger console = this.getLogger();


    @Override
    public void onEnable() {
        int pluginId = 16002;
        metrics = new Metrics(this, 103297);
        updateChecker = new UpdateChecker(this, 103297);
        console.info("Initializing commands");
        this.getCommand("banhammer").setExecutor(new GiveBanhammerCommand());
        this.getCommand("kickhammer").setExecutor(new GiveKickHammerCommand());
        console.info("Commands initialized");
        console.info("Initializing listeners");
        Bukkit.getServer().getPluginManager().registerEvents(new BanHammerEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KickHammerEvent(), this);
        console.info("Listeners initialized");
        updateChecker.getVersion(version -> {
            if(this.getDescription().getVersion().equals(version))
            {
                console.info("You are on the newest version(" + this.getDescription().getVersion() + ")");
            }
            else
            {
                console.info("There is an update available for version: " + this.getDescription().getVersion());
            }
        });

    }

    @Override
    public void onDisable() {
        console.info("stopping plugin");
    }
}
