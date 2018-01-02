package k4unl.minecraft.k4lib.client.gui.elements;

import net.minecraft.client.gui.FontRenderer;

/**
 * @author Koen Beckers (K-4U)
 */
public class GuiTextField extends net.minecraft.client.gui.GuiTextField {
    
    private boolean needsAttention = false;
    
    public GuiTextField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
    }
    
    @Override
    public void drawTextBox() {
        
        super.drawTextBox();
        if(isNeedsAttention()) {
            drawHorizontalLine(this.x - 1, this.width + x, this.y - 1, 0xFF8C0000);
            drawHorizontalLine(this.x - 1, this.width + x, this.y + this.height, 0xFF8C0000);
            drawVerticalLine(this.x - 1, this.y - 1, this.y + this.height + 1, 0xFF8C0000);
            drawVerticalLine(this.x + this.width, this.y - 1, this.y + this.height + 1, 0xFF8C0000);
        }
    }
    
    public boolean isNeedsAttention() {
        
        return needsAttention;
    }
    
    public void setNeedsAttention(boolean needsAttention) {
        
        this.needsAttention = needsAttention;
    }
}
