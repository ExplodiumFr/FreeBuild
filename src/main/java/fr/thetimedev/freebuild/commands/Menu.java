package fr.thetimedev.freebuild.commands;

import fr.thetimedev.freebuild.Main;
import fr.thetimedev.freebuild.gui.BaseGui;
import fr.thetimedev.freebuild.gui.GuiType;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu extends BaseGui implements CommandExecutor {

    private final Main explodium;

    public Menu(Main explodium) {
        super(GuiType.create("Menu"), 1, "§bMenu du freebuild");
        this.explodium = explodium;
    }

    @Override
    protected void populateBase(ItemStack[] items) {
        items[1] = namedItem(Material.ENDER_PEARL,"§eJour");
        items[2] = namedItem(Material.BLACK_SHULKER_BOX,"§8Nuit");
        items[5] = namedItem(Material.ORANGE_SHULKER_BOX,"§6Orage");
        items[6] = namedItem(Material.BLUE_SHULKER_BOX,"§7Pluie");
        items[7] = namedItem(Material.WHITE_SHULKER_BOX,"§bBeau Temps");
    }
    private ItemStack namedItem(Material mat,String name){
        ItemStack itm = new ItemStack(mat);
        ItemMeta meta = itm.getItemMeta();
        meta.setDisplayName(name);
        itm.setItemMeta(meta);
        return itm;
    }

    @Override
    protected void populate(Inventory inv, Player p) {

    }

    @Override
    protected void click(InventoryClickEvent click, Player player) {

    }

    String[] commandes = new String[]{null,"day","night",null,null,"orage","rain","sun",null};

    @Override
    protected void clickTop(InventoryClickEvent click, Player player) {
        click.setCancelled(true);
        String command = this.commandes[click.getSlot()];
        if(command != null){
            player.performCommand(this.commandes[click.getSlot()]);
        }
    }

    @Override
    protected void clickBottom(InventoryClickEvent click, Player player) {

    }

    @Override
    protected void clickVoid(InventoryClickEvent click, Player player) {

    }

    @Override
    public void shiftClickBottom(InventoryClickEvent click, Player p) {
        click.setCancelled(true);
    }

    @Override
    public void shiftClickTop(InventoryClickEvent click, Player p) {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(!player.hasPermission("freebuild.menu")) {
                String message = explodium.config.getString("messages.prefix").replace('&', '§')  + explodium.config.getString("messages.no_permission").replace('&', '§') ;
                if(message.equals("")) {
                    message = "§cVous n'avez pas la permission pour execter cette commande !";
                }
                player.sendMessage(message);
                return true;
            }
            open((Player) commandSender);
        }
        return false;
    }
}
