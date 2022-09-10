package fr.thetimedev.freebuild.gui;

import java.security.KeyException;

public class GuiType {

    private BaseGui baseGui;
    protected GuiType(String id) throws KeyException {
        GuiManager.getInstance().addType(this,id);
    }

    public static GuiType get(String id){
        return GuiManager.getInstance().getType(id);
    }
    public static GuiType create(String id) {
        try {
            return new GuiType(id);
        } catch (KeyException e) {
            e.printStackTrace();
        }
        return get(id);//the last inventory on this id will be destroyed
    }

    public void setBaseGui(fr.thetimedev.freebuild.gui.BaseGui baseGui){
        this.baseGui = baseGui;
    }
    public fr.thetimedev.freebuild.gui.BaseGui getBaseGui() {
        return baseGui;
    }
}
