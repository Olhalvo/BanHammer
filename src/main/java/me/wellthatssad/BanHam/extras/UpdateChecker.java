package me.wellthatssad.BanHam.extras;


import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;


public class UpdateChecker {

    private final JavaPlugin pl;
    private final int id;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.pl = plugin;
        this.id = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.pl, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.id).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                pl.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }
}
