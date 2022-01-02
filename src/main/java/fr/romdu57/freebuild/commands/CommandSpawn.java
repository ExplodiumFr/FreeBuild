package fr.romdu57.freebuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            //Random random = new Random();
            Player player = (Player) sender;
            //Location ploc = player.getLocation();

            //Location spawn = new Location(player.getWorld(), ploc.getX(), ploc.getY() + random.nextInt(50), ploc.getZ());
            Location spawn = new Location(Bukkit.getWorld("world"), 20.507, 7, 0.494, 0f, 0f);
            player.teleport(spawn);
            player.sendMessage("[§6FreeBuild-Explo§f] Vous avez été téléporter au spawn !");
        }

        return false;
    }

}
