package k4unl.minecraft.k4lib.client.gui.elements;


import k4unl.minecraft.k4lib.lib.config.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Consumer;

/**
 * @author Koen Beckers (K-4U)
 */
@SideOnly(Side.CLIENT)
public class GuiButton extends net.minecraft.client.gui.GuiButton {
    
    protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(ModInfo.ID, "textures/gui/widgets/button.png");
    private Consumer<GuiButton> callback;
    private boolean active = false;
    
    public GuiButton(int buttonId, int x, int y, String buttonText, Consumer<GuiButton> callback) {
        
        super(buttonId, x, y, buttonText);
        this.callback = callback;
    }
    
    public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, Consumer<GuiButton> callback) {
        
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.callback = callback;
    }
    
    public Consumer<GuiButton> getCallback() {
        
        return callback;
    }
    
    public void setActive(boolean b) {
        
        active = b;
    }
    
    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, i * 20, this.width / 2, this.height, 200, 80);
            //this.drawTexturedModalRect(this.x, this.y, 0, i * 20, this.width / 2, this.height);
            this.drawModalRectWithCustomSizedTexture(this.x + this.width / 2, this.y, 200 - this.width / 2, i * 20, this.width / 2, this.height, 200, 80);
            this.mouseDragged(mc, mouseX, mouseY);
            
            this.drawButtonForeground(mc, mouseX, mouseY, partialTicks);
        }
    }
    
    protected void drawButtonForeground(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        
        FontRenderer fontrenderer = mc.fontRenderer;
        int j = 14737632;
        
        if (packedFGColour != 0) {
            j = packedFGColour;
        } else if (!this.enabled) {
            j = 10526880;
        } else if (this.hovered) {
            j = 16777120;
        }
        
        this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
    }
    
    protected int getHoverState(boolean mouseOver) {
        
        int i = 1;
        
        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        } else if (active) {
            i = 3;
        }
        
        return i;
    }
}
