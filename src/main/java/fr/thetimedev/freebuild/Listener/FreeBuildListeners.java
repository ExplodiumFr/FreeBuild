package fr.thetimedev.freebuild.Listener;

import fr.thetimedev.freebuild.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;


public class FreeBuildListeners implements Listener {

    private Main explodium;

    public FreeBuildListeners(Main explodium){
        this.explodium = explodium;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6"+player.getName()+"§r§9 vient de rejoindre le serveur !");
        player.teleport(parseStringToLoc(this.explodium.getConfig().getString("world")));

        ItemStack menu = new ItemStack(Material.COMPASS, 1);
        ItemMeta Cmenu = menu.getItemMeta();
        Cmenu.setDisplayName("§6Menu");
        Cmenu.setLore(Collections.singletonList("Affiche le menu du freebuild"));
        Cmenu.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
        Cmenu.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        menu.setItemMeta(Cmenu);

        player.getInventory().setItem(8, menu);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();
        
        if(it == null) return;

        if(it.getType() == Material.COMPASS){
            if(action == Action.RIGHT_CLICK_AIR || action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK && it.getItemMeta().getDisplayName().equalsIgnoreCase("§6Menu")){
                Inventory inv = Bukkit.createInventory(null, 54, "§0Menu FreeBuild");

                inv.setItem(11, getItem(Material.GRASS, "§2Acheter une Zone"));
                inv.setItem(14, getItem(Material.ENDER_PEARL, "§4Aller sur ma Zone"));

                player.openInventory(inv);
            }

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;

        if(inv.getName().equalsIgnoreCase("§0Menu FreeBuild")){

            if(current.getType() == Material.GRASS){
                player.closeInventory();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mvtp "+player.getName()+" FreeBuild2");
                Bukkit.dispatchCommand(player, "plot auto");
            }

            if(current.getType() == Material.ENDER_PEARL){
                player.closeInventory();
                Bukkit.dispatchCommand(player, "plot home");
            }

        }
    }

    public ItemStack getItem(Material material, String customName){
        ItemStack it = new ItemStack(material, 1);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(customName);
        return it;
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
