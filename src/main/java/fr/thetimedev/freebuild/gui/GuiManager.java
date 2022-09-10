package fr.thetimedev.freebuild.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.KeyException;
import java.util.HashMap;

public class GuiManager implements Listener {

    public static GuiManager INSTANCE = new GuiManager();
    public static GuiManager getInstance() {
        return INSTANCE;
    }
    private static boolean INITIALISED = false;
    public static void initialise(JavaPlugin plugin){
        if(!INITIALISED){
            Bukkit.getPluginManager().registerEvents(INSTANCE,plugin);
            INITIALISED = true;
        }
    }

    private GuiManager(){}

    HashMap<String,GuiType> types = new HashMap<>();

    public boolean hasType(String id){
        return this.types.containsKey(id);
    }
    public GuiType getType(String id){
        return this.types.get(id);
    }
    public void addType(GuiType type,String id) throws KeyException {
        if(hasType(id)){
            throw new KeyException("GuiType aleready created");
        }
        this.types.put(id,type);
    }

    //key is the player name
    final HashMap<String, Inventory> inventorys = new HashMap<>();
    final HashMap<String, GuiType> lastopen = new HashMap<>();

    public void setOpen(String name, Inventory inv, fr.thetimedev.freebuild.gui.BaseGui gui){
        this.inventorys.put(name,inv);
        this.lastopen.put(name,gui.getType());
    }

    public boolean isOpen(Player p) {
        if(p == null)
            return false;
        String name = p.getName();
        if(!p.isOnline()){
            remove(name);
            return false;
        }
        if(!this.inventorys.containsKey(name)){return false;}
        InventoryView view = p.getOpenInventory();
        if(!view.getTopInventory().equals(this.inventorys.get(name))){
            remove(p.getName());
            return false;}
        return true;
    }

    public void remove(String name){
        this.inventorys.remove(name);
        this.lastopen.remove(name);
    }
    public GuiType getType(Player p) {return this.lastopen.get(p.getName());}

    //events
    @EventHandler
    public void lisenToGuiClick(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        if(this.isOpen(p)){
            fr.thetimedev.freebuild.gui.BaseGui gui = this.getType(p).getBaseGui();
            gui.click(event,p);
            InventoryView view = p.getOpenInventory();
            Inventory now = event.getClickedInventory();
            if(now == null){//he try to drop
                gui.clickVoid(event,p);
            }else if(now.equals(view.getBottomInventory())){
                gui.clickBottom(event,p);
                if(event.getClick().isShiftClick()){
                    gui.shiftClickBottom(event,p);
                }
            }else{
                gui.clickTop(event,p);
                if(event.getClick().isShiftClick()){
                    gui.shiftClickTop(event,p);
                }
            }
        }
    }

}
