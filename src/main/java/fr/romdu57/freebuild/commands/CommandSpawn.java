package fr.romdu57.freebuild.commands;

import fr.romdu57.freebuild.FreeBuild;
import jdk.internal.org.jline.reader.ConfigurationPath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    private FreeBuild explodium;

    public CommandSpawn(FreeBuild explodium){
        this.explodium = explodium;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            //Random random = new Random();
            Player player = (Player) sender;
            //Location ploc = player.getLocation();

            //Location spawn = new Location(player.getWorld(), ploc.getX(), ploc.getY() + random.nextInt(50), ploc.getZ());
            player.teleport(parseStringToLoc(this.explodium.getConfig().getString("world")));
            player.sendMessage("[§6FreeBuild-Explo§f] Vous avez été téléporter au spawn !");
        }
    return false;
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
