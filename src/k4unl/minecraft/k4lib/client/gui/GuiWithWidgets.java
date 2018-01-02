package k4unl.minecraft.k4lib.client.gui;

import com.google.common.collect.Lists;
import k4unl.minecraft.k4lib.client.gui.elements.GuiButton;
import k4unl.minecraft.k4lib.client.gui.elements.GuiWidget;
import k4unl.minecraft.k4lib.lib.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Koen Beckers (K-4U)
 */
@SideOnly(Side.CLIENT)
public abstract class GuiWithWidgets extends GuiScreen {
    
    /**
     * The X size of the inventory window in pixels.
     */
    protected int xSize = 176;
    /**
     * The Y size of the inventory window in pixels.
     */
    protected int ySize = 224;
    /**
     * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiLeft;
    /**
     * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
     */
    protected int guiTop;
    
    private   List<GuiWidget>                          widgetList = new ArrayList<>();
    protected List<GuiButton> buttonList = Lists.newArrayList();
    
    public GuiWithWidgets() {
        
        super();
        
    }
    
    protected abstract void drawBackground();
    
    protected abstract void renderForeground(int mouseX, int mouseY);
    
    @Override
    public void initGui() {
        widgetList.clear();
        buttonList.clear();
        labelList.clear();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseX = mouseX - guiLeft;
        mouseY = mouseY - guiTop;
        if (mouseButton == 0) {
            for (GuiWidget widget : widgetList) {
                if ((mouseX >= widget.getX() && mouseX <= widget.getX() + widget.getWidth()) && (mouseY >= widget.getY() && mouseY <= widget.getY() + widget.getHeight())) {
                    widget.onMouseClick(mouseX, mouseY);
                }
            }
            for (GuiButton button : buttonList) {
                if (button.mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
                    button.playPressSound(mc.getSoundHandler());
                    button.getCallback().accept(button);
                }
            }
        }
    }
    
    @Override
    public void handleMouseInput() throws IOException {
        
        super.handleMouseInput();
        int mouseX = (Mouse.getEventX() * this.width / this.mc.displayWidth) - guiLeft;
        int mouseY = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1) - guiTop;
        for (GuiWidget widget : widgetList) {
            if ((mouseX >= widget.getX() && mouseX <= widget.getX() + widget.getWidth()) && (mouseY >= widget.getY() && mouseY <= widget.getY() + widget.getHeight())) {
                widget.onHouseHover(Mouse.getEventX() - widget.getX(), Mouse.getEventY() - widget.getY());
                widget.setHover(true);
            } else {
                if (widget.isHover()) {
                    widget.setHover(false);
                }
            }
        }
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //mouseX = (mouseX * this.width / this.mc.displayWidth) - guiLeft;
        //mouseY = (this.height - mouseY * this.height / this.mc.displayHeight - 1) - guiTop;
        mouseX = mouseX - guiLeft;
        mouseY = mouseY - guiTop;
        this.drawDefaultBackground();
        this.drawBackground();
        
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) guiLeft, (float) guiTop, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableRescaleNormal();
        
        renderForeground(mouseX, mouseY);
        
        for (GuiWidget widget : widgetList) {
            widget.doRender();
        }
        
        for (net.minecraft.client.gui.GuiButton button : buttonList) {
            button.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }
        
        for (GuiLabel label : labelList) {
            label.drawLabel(this.mc, mouseX, mouseY);
        }
        
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
    }
    
    protected void addWidget(GuiWidget widget) {
        
        if (!widgetList.contains(widget)) {
            widgetList.add(widget);
        }
    }
    
    @Override
    public boolean doesGuiPauseGame() {
        
        return true;
    }
}
