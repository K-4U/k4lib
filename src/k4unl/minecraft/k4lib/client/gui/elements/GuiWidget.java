package k4unl.minecraft.k4lib.client.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Koen Beckers (K-4U)
 */
@SideOnly(Side.CLIENT)
public abstract class GuiWidget {
    
    private   String    name;
    private   int       x;
    private   int       y;
    protected Minecraft mc;
    protected GuiScreen parent;
    private   boolean   isHover;
    private   boolean   isVisible;
    
    /*
     * Function that gets called whenever the mouse is in the widget.
     * mouseX and mouseY are relative.
     */
    public abstract void onHouseHover(int mouseX, int mouseY);
    
    public abstract void onHouseOut();
    
    public abstract void onMouseClick(int mouseX, int mouseY);
    
    public abstract int getWidth();
    
    public abstract int getHeight();
    
    public abstract void doRender();
    
    public GuiWidget(String name, int x, int y, Minecraft mc, GuiScreen parent) {
        
        this.name = name;
        this.x = x;
        this.y = y;
        this.mc = mc;
        this.parent = parent;
    }
    
    public String getName() {
        
        return name;
    }
    
    public int getX() {
        
        return x;
    }
    
    public int getY() {
        
        return y;
    }
    
    public void setX(int x) {
        
        this.x = x;
    }
    
    public void setY(int y) {
        
        this.y = y;
    }
    
    public boolean isHover() {
        
        return isHover;
    }
    
    public void setHover(boolean hover) {
        
        isHover = hover;
    }
    
    public boolean isVisible() {
        
        return isVisible;
    }
    
    public void setVisible(boolean visible) {
        
        isVisible = visible;
    }
}
