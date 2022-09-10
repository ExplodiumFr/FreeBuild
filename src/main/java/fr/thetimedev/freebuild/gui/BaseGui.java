package fr.thetimedev.freebuild.gui;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class BaseGui {

    protected GuiType type;
    protected int lines;
    protected String name;
    protected ItemStack[] base;

    public BaseGui(GuiType type, int lines, String name){
        this.type = type;
        this.lines = lines;
        this.name = name;

        this.base = new ItemStack[lines*9];
        populateBase(this.base);
        type.setBaseGui(this);
    }
    protected BaseGui getself(){
        return this;
    }

    public void open(Player player){
        Inventory inv = Bukkit.createInventory(player,this.getLines(player)*9,this.getName(player));
        inv.setStorageContents(this.base);//peux cossée un but si le nombre le ligne est diférent
        this.populate(inv,player);
        GuiManager.getInstance().setOpen(player.getName(),inv,this);
        player.openInventory(inv);

    }
    public ItemStack[] getContenant(Player player){
        Inventory inv = Bukkit.createInventory(player,this.getLines(player)*9,this.getName(player));
        inv.setContents(this.base);//peux cossée un but si le nombre le ligne est diférent
        this.populate(inv,player);
        return inv.getStorageContents();
    }

    protected String getName(Player player){
        return this.name;
    }
    protected int getLines(Player player){
        return this.lines;
    }

    protected abstract void populateBase(ItemStack[] items);
    protected abstract void populate(Inventory inv, Player p);

    protected abstract void click(InventoryClickEvent click,Player player);
    protected abstract void clickTop(InventoryClickEvent click,Player player);
    protected abstract void clickBottom(InventoryClickEvent click,Player player);
    protected abstract void clickVoid(InventoryClickEvent click,Player player);
    public abstract void shiftClickBottom(InventoryClickEvent click, Player p);
    public abstract void shiftClickTop(InventoryClickEvent click, Player p);

    public GuiType getType() {
        return type;
    }

}
