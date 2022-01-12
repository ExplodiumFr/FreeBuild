package fr.romdu57.freebuild;

import org.bukkit.plugin.java.JavaPlugin;

import fr.romdu57.freebuild.commands.CommandSpawn;

public class FreeBuild extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println("[FreeBuild-Explo] Plugin activer !");
        getCommand("spawn").setExecutor(new CommandSpawn(this));
        this.getServer().getPluginManager().registerEvents(new FreeBuildListeners(this), this);

    }

    @Override
    public void onDisable() {
        System.out.println("[FreeBuild-Explo] Plugin desactiver !");
    }

}