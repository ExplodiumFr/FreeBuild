package fr.thetimedev.freebuild.commands;

import fr.thetimedev.freebuild.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class ConfigReload implements CommandExecutor {

    Logger LOGGER = getLogger();

    private Main explodium;

    public ConfigReload(Main explodium){
        this.explodium = explodium;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.hasPermission("freebuild.reload")) {
            String message = explodium.config.getString("messages.prefix") + explodium.config.getString("messages.no_permission");
            if (message.equals("")) {
                message = "§cVous n'avez pas la permission pour execter cette commande !";
            }
            sender.sendMessage(message);
            return true;
        }

        try {
            explodium.config.load(explodium.configfile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        String message = explodium.config.getString("messages.prefix") + explodium.config.getString("messages.reload_success");
        if(message.equals("")) {
            message = "§aConfig Reloaded!";
        }
        sender.sendMessage(message);
        return true;
    }
}
