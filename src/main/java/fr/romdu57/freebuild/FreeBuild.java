package fr.romdu57.freebuild;

import org.bukkit.plugin.java.JavaPlugin;

import fr.romdu57.freebuild.commands.CommandSpawn;

public class FreeBuild extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[FreeBuild-Explo] Plugin activer !");
        getCommand("spawn").setExecutor(new CommandSpawn());
        getServer().getPluginManager().registerEvents(new FreeBuildListeners(), this);

    }

    @Override
    public void onDisable() {
        System.out.println("[FreeBuild-Explo] Plugin desactiver !");
    }

}