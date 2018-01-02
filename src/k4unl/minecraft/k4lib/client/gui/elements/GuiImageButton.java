package k4unl.minecraft.k4lib.client.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

/**
 * @author Koen Beckers (K-4U)
 */
public class GuiImageButton extends GuiButton {
    
    private ResourceLocation image;
    
    public GuiImageButton(int buttonId, int x, int y, ResourceLocation image, Consumer<GuiButton> callback) {
        
        super(buttonId, x, y, "", callback);
        this.image = image;
    }
    
    public GuiImageButton(int buttonId, int x, int y, int widthIn, int heightIn, ResourceLocation image, Consumer<GuiButton> callback) {
        
        super(buttonId, x, y, widthIn, heightIn, "", callback);
        this.image = image;
    }
    
    @Override
    protected void drawButtonForeground(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        
        mc.getTextureManager().bindTexture(image);
        this.drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height, 20, 20);
    }
}
