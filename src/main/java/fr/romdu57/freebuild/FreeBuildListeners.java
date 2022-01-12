package fr.romdu57.freebuild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class FreeBuildListeners implements Listener {

    private FreeBuild explodium;

    public FreeBuildListeners(FreeBuild explodium){
        this.explodium = explodium;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6"+player.getName()+"§r§9 vient de rejoindre le serveur !");
        player.teleport(parseStringToLoc(this.explodium.getConfig().getString("world")));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6"+player.getName()+"§r§9 vient de quitter le serveur !");
    }

    public Location parseStringToLoc(String string) {
        String[] parsedLoc = string.split(", ");
        double x = Double.valueOf(parsedLoc[0]);
        double y = Double.valueOf(parsedLoc[1]);
        double z = Double.valueOf(parsedLoc[2]);
        String world = parsedLoc[3];
        float yaw = Float.valueOf(parsedLoc[4]);
        float pitch = Float.valueOf(parsedLoc[5]);
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
