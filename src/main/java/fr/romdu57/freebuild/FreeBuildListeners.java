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

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6"+player.getName()+"§r§9 vient de rejoindre le serveur !");
        Location spawn = new Location(player.getWorld(), 20.507, 7, 0.494, 0f, 0f);
        player.teleport(spawn);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6"+player.getName()+"§r§9 vient de quitter le serveur !");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();
        if(player.hasPermission("staffchat")) {
            Bukkit.broadcastMessage("<§l§4Staff "+player.getName()+"§r> "+ e.getMessage());
        }else {
            Bukkit.broadcastMessage("<"+player.getName()+"> "+ e.getMessage());
        }
    }

}
