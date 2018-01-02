package k4unl.minecraft.k4lib.client.gui.elements;


import k4unl.minecraft.k4lib.lib.config.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Consumer;

/**
 * @author Koen Beckers (K-4U)
 */
@SideOnly(Side.CLIENT)
public class ToggleButton extends GuiWidget {
    
    private boolean                value;
    private Consumer<ToggleButton> callback;
    public static final ResourceLocation background = new ResourceLocation(ModInfo.LID, "textures/gui/widgets/togglebutton.png");
    
    public ToggleButton(String name, int x, int y, Minecraft mc, GuiScreen parent, Consumer<ToggleButton> callback) {
        
        super(name, x, y, mc, parent);
        this.callback = callback;
    }
    
    @Override
    public void onHouseHover(int mouseX, int mouseY) {
    
    }
    
    @Override
    public void onHouseOut() {
    
    }
    
    @Override
    public void onMouseClick(int mouseX, int mouseY) {
        
        value = !value;
        if (callback != null) {
            callback.accept(this);
        }
    }
    
    @Override
    public int getWidth() {
        
        return 32;
    }
    
    @Override
    public int getHeight() {
        
        return 14;
    }
    
    @Override
    public void doRender() {
        
        mc.getTextureManager().bindTexture(background);
        int textureY = 0;
        
        if (isHover()) {
            textureY += getHeight();
        }
        if (isValue()) {
            textureY += getHeight() * 2;
        }
        
        Gui.drawModalRectWithCustomSizedTexture(getX(), getY(), 0, textureY, getWidth(), getHeight(), 32, 56);
    }
    
    public boolean isValue() {
        
        return value;
    }
    
    public void setValue(boolean value) {
        
        this.value = value;
    }
    
    public boolean getValue() {
        
        return value;
    }
}
