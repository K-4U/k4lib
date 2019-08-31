package k4unl.minecraft.k4lib.client;

import org.lwjgl.opengl.GL11;

import k4unl.minecraft.k4lib.lib.Vector3fMax;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHelper {

    static         float         lightBottom     = 0.5F;
    static         float         lightTop        = 1.0F;
    static         float         lightEastWest   = 0.8F;
    static         float         lightNorthSouth = 0.6F;
    private static Tessellator   tess            = Tessellator.getInstance();
    private static BufferBuilder  worldRenderer   = tess.getBuffer();
    public static  float         pixel           = 1.0F / 16.0F;
    public static  float         renderPixel     = 1.0F / 32.0F;
    public static  float         bigRenderPixel  = 1.0F / 64.0F;

    public static void vertexWithTexture(float x, float y, float z, float tL, float tT) {

        GL11.glTexCoord2f(tL, tT);
        GL11.glVertex3f(x, y, z);
    }

    public static void tesselatedTexture(float x, float y, float z, float tL, float tT) {
        worldRenderer.pos(x, y, z).tex(tL, tT).endVertex();
    }

    public static void tesselatedTexture(float x, float y, float z, float tL, float tT, int brightness) {
        int j = brightness >> 16 & 65535;
        int k = brightness & 65535;
        worldRenderer.pos(x, y, z).tex(tL, tT).lightmap(j, k).endVertex();
    }

    public static void startDrawingQuads() {
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
    }

    public static void tesselatorDraw() {
        tess.draw();
    }

    public static void drawCube(Vector3fMax vector) {
        drawTexturedCube(vector);
    }

    public static void drawCube(Vector3fMax vector, boolean doColors) {
        if (doColors) {
            drawColoredCube(vector);
        } else {
            drawCube(vector);
        }
    }

    public static void setBrightness(int brightness){
        int j = brightness >> 16 & 65535;
        int k = brightness & 65535;
        worldRenderer.lightmap(j, k);
    }

    public static void drawColoredCube(Vector3fMax vector) {
        //Top side
        GL11.glColor3f(1.0F, 0.0F, 0.0F);
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());

        //Bottom side
        GL11.glColor3f(1.0F, 1.0F, 0.0F);
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

        //Draw west side:
        GL11.glColor3f(0.0F, 1.0F, 0.0F);
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());

        //Draw east side:
        GL11.glColor3f(0.0F, 1.0F, 1.0F);
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());

        //Draw north side
        GL11.glColor3f(0.0F, 0.0F, 1.0F);
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

        //Draw south side
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
    }

    public static void setARGBFromHex(int hexColor) {
        float a = (hexColor >> 24 & 255) / 255.0F;
        float r = (hexColor >> 16 & 255) / 255.0F;
        float g = (hexColor >> 8 & 255) / 255.0F;
        float b = (hexColor & 255) / 255.0F;

        GL11.glColor4f(r, g, b, a);
    }

    public static void setRGBFromHex(int hexColor) {
        float r = (hexColor >> 16 & 255) / 255.0F;
        float g = (hexColor >> 8 & 255) / 255.0F;
        float b = (hexColor & 255) / 255.0F;

        GL11.glColor3f(r, g, b);
    }

    public static void drawCubeWithoutColor(Vector3fMax vector) {
        //Top side
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());

        //Bottom side
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

        //Draw west side:
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());

        //Draw east side:
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());

        //Draw north side
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

        //Draw south side
        GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
        GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
        GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
    }

    /**
     * Draws a cube with the size of vector. Every face has the same color This uses the Tessellator
     *
     * @param vector
     * @author Koen Beckers (K4Unl)
     */

    public static void drawTesselatedCube(Vector3fMax vector) {

        boolean wasTesselating = false;

        // Check if we were already tesselating
        try {
            startDrawingQuads();
        } catch (IllegalStateException e) {
            wasTesselating = true;
        }

        // Top side
        worldRenderer.color(1.0F, 0.0F, 0.0F, 0.7F);
        worldRenderer.normal(0, 1, 0);
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMax());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMax());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMin());
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMin());

        // Bottom side
        worldRenderer.normal(0, -1, 0);
        worldRenderer.color(1.0F, 1.0F, 0.0F, 0.7F);
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMax());
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMax());
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMin());
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMin());

        // Draw west side:
        worldRenderer.normal(-1, 0, 0);
        worldRenderer.color(0.0F, 1.0F, 0.0F, 0.7F);
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMax());
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMax());
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMin());
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMin());

        // Draw east side:
        worldRenderer.normal(1, 0, 0);
        worldRenderer.color(0.0F, 1.0F, 1.0F, 0.7F);
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMin());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMin());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMax());
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMax());

        // Draw north side
        worldRenderer.normal(0, 0, -1);
        worldRenderer.color(0.0F, 0.0F, 1.0F, 0.7F);
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMin());
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMin());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMin());
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMin());

        // Draw south side
        worldRenderer.normal(0, 0, 1);
        worldRenderer.color(0.0F, 0.0F, 0.0F, 0.7F);
        worldRenderer.pos(vector.getXMin(), vector.getYMin(), vector.getZMax());
        worldRenderer.pos(vector.getXMax(), vector.getYMin(), vector.getZMax());
        worldRenderer.pos(vector.getXMax(), vector.getYMax(), vector.getZMax());
        worldRenderer.pos(vector.getXMin(), vector.getYMax(), vector.getZMax());

        if (!wasTesselating) {
            tess.draw();
        }
    }


    public static boolean beginTesselatingWithTexture() {

        boolean wasTessellating = false;
        try {
            startDrawingQuads();
        } catch (IllegalStateException e) {
            wasTessellating = true;
        }

	    Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

        return wasTessellating;
    }

    public static void drawTesselatedSideTopWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Top side
        worldRenderer.normal(0, 1, 0);
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BR
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TL
    }
    public static void drawTesselatedSideTopWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Top side
        worldRenderer.normal(0, 1, 0);
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMaxV(), brightness); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMaxV(), brightness); //BR
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV(), brightness); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMinV(), brightness); //TL
    }

    public static void drawTesselatedSideBottomWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Bottom side
        worldRenderer.normal(0, -1, 0);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BR
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BL
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TR
    }
    public static void drawTesselatedSideBottomWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Bottom side
        worldRenderer.normal(0, -1, 0);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV(), brightness); //BR
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV(), brightness); //BL
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV(), brightness); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMinV(), brightness); //TR
    }

    public static void drawTesselatedSideWestWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw west side:
        worldRenderer.normal(-1, 0, 0);
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMinV()); //BR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMaxV()); //TL
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //BL
    }
    public static void drawTesselatedSideWestWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Draw west side:
        worldRenderer.normal(-1, 0, 0);
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMinV(), brightness); //BR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMaxV(), brightness); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMaxV(), brightness); //TL
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV(), brightness); //BL
    }

    public static void drawTesselatedSideEastWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw east side:
        worldRenderer.normal(1, 0, 0);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMaxV()); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV()); //TR
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BR
    }
    public static void drawTesselatedSideEastWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Draw east side:
        worldRenderer.normal(1, 0, 0);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMaxV(), brightness); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV(), brightness); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV(), brightness); //TR
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV(), brightness); //BR
    }

    public static void drawTesselatedSideNorthWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw north side
        worldRenderer.normal(0, 0, -1);
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //BL
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMaxV()); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMaxV()); //TR
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //BR
    }
    public static void drawTesselatedSideNorthWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Draw north side
        worldRenderer.normal(0, 0, -1);
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMinV(), brightness); //BL
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMaxV(), brightness); //TL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMaxV(), brightness); //TR
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV(), brightness); //BR
    }

    public static void drawTesselatedSideSouthWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw south side
        worldRenderer.normal(0, 0, 1);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMinV()); //TL
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV()); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BR
    }
    public static void drawTesselatedSideSouthWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        //Draw south side
        worldRenderer.normal(0, 0, 1);
        tesselatedTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV(), brightness); //BL
        tesselatedTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMinV(), brightness); //TL
        tesselatedTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV(), brightness); //TR
        tesselatedTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV(), brightness); //BR
    }

    public static void stopTesselating(boolean wasTessellating) {
        if (!wasTessellating) {
            tess.draw();
        }
    }

    public static void drawTesselatedCubeWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        boolean wasTessellating = beginTesselatingWithTexture();
        drawTesselatedSideTopWithTexture(vector, icon);
        drawTesselatedSideBottomWithTexture(vector, icon);
        drawTesselatedSideWestWithTexture(vector, icon);
        drawTesselatedSideEastWithTexture(vector, icon);
        drawTesselatedSideNorthWithTexture(vector, icon);
        drawTesselatedSideSouthWithTexture(vector, icon);

        stopTesselating(wasTessellating);

    }

    public static void drawTesselatedCubeWithTexture(Vector3fMax vector, TextureAtlasSprite icon, int brightness) {
        boolean wasTessellating = beginTesselatingWithTexture();
        drawTesselatedSideTopWithTexture(vector, icon, brightness);
        drawTesselatedSideBottomWithTexture(vector, icon, brightness);
        drawTesselatedSideWestWithTexture(vector, icon, brightness);
        drawTesselatedSideEastWithTexture(vector, icon, brightness);
        drawTesselatedSideNorthWithTexture(vector, icon, brightness);
        drawTesselatedSideSouthWithTexture(vector, icon, brightness);

        stopTesselating(wasTessellating);

    }


    public static void drawTexturedCube(Vector3fMax vector) {
        //Top side:
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F);

        //Bottom side:
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.0F, 0.5F);

        //Draw west side:
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.0F, 0.0F);

        //Draw east side:
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.0F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);

        //Draw north side
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.0F, 0.0F);

        //Draw south side
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);
        RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.5F, 0.5F);
        RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.0F, 0.5F);
    }

    public static void drawTexturedCubeWithLight(Vector3fMax vector, TileEntity t) {
        //TODO: FIX ME
        drawTexturedCube(vector);
        /*float light = t.blockType.getBlockBrightness(t.getWorldObj(), t.xCoord, t.yCoord, t.zCoord);
		light = (light + ((0.8f - light) * 0.4f)) * 0.9F;
		//light = 1.0F;
		
		int l = t.blockType.colorMultiplier(t.getWorldObj(), t.xCoord, t.yCoord, t.zCoord);
        float f = (l >> 16 & 255) / 255.0F;
        float f1 = (l >> 8 & 255) / 255.0F;
        float f2 = (l & 255) / 255.0F;
		//Tessellator tessellator = Tessellator.instance;
		
		//tessellator.startDrawingQuads();
		//tessellator.setBrightness(brightness);

		GL11.glColor3f(lightTop * light * f, lightTop * light * f1, lightTop * light * f2);
		//Top side:
		
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.0F, 0.0F); //BL
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.5F, 0.0F); //BR
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F); //TR
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F); //TL
		
		GL11.glColor3f(lightBottom * light * f, lightBottom * light * f1, lightBottom * light * f2);
		//Bottom side:
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);		
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.5F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.0F, 0.5F);

		//Draw west side:
		GL11.glColor3f(lightEastWest * light * f, lightEastWest * light * f1, lightEastWest * light * f2);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.5F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.0F, 0.0F);
		
		//Draw east side:
		GL11.glColor3f(lightEastWest * light, lightEastWest * light, lightEastWest * light);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.5F, 0.0F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.0F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);
		
		//Draw north side
		GL11.glColor3f(lightNorthSouth * light, lightNorthSouth * light, lightNorthSouth * light);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), 0.5F, 0.0F); 
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), 0.5F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), 0.0F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), 0.0F, 0.0F);

		//Draw south side
		GL11.glColor3f(lightNorthSouth * light, lightNorthSouth * light, lightNorthSouth * light);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), 0.0F, 0.0F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), 0.5F, 0.0F);
		RenderHelper.vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), 0.5F, 0.5F);
		RenderHelper.vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), 0.0F, 0.5F);
		
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		*/
    }

	public static void draw2DCircle(float xCenter, float yCenter, float r){

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_TRIANGLES);
		int angle = 0;
		int resolution = 10;
		float dToR = (float)(Math.PI / 180.0);

		double prevX = xCenter + (r*Math.cos((angle-resolution)*dToR));
		double prevY = yCenter + (r*Math.sin((angle-resolution)*dToR));
		while(angle < 360){
			double x = xCenter + (r*Math.cos(angle*dToR));
			double y = yCenter + (r*Math.sin(angle*dToR));

			GL11.glVertex2f(xCenter, yCenter);
			GL11.glVertex2d(x, y);
			GL11.glVertex2d(prevX, prevY);
			prevX = x;
			prevY = y;

			angle+= resolution;
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public static void drawCylinder(float xCenter, float yCenter, float zCenter, float r, float length, float xTextureStart, float yTextureStart, float xTextureEnd, float yTextureEnd){
		int ltd = 0;
		float x;
		float z;
		float x2;
		float z2;
		float dToR = (float)(Math.PI / 180.0);
		int reso = 20;

		GL11.glBegin(GL11.GL_QUADS);
		for(ltd = 0; ltd < 360; ltd+=reso){
			x = (float) (xCenter + (Math.sin(ltd * dToR) * r));
			z = (float) (zCenter + (Math.cos(ltd * dToR) * r));

			x2 = (float) (xCenter + (Math.sin((ltd+reso) * dToR) * r));
			z2 = (float) (zCenter + (Math.cos((ltd+reso) * dToR) * r));

			//GL11.glColor3f(ltd/360.0F, 0.0F, 0F);

			GL11.glTexCoord2f(xTextureStart, yTextureStart);
			GL11.glVertex3f(x2, yCenter, z2);

			GL11.glTexCoord2f(xTextureEnd, yTextureStart);
			GL11.glVertex3f(x2, yCenter+length, z2);

			GL11.glTexCoord2f(xTextureEnd, yTextureEnd);
			GL11.glVertex3f(x, yCenter+length, z);

			GL11.glTexCoord2f(xTextureStart, yTextureEnd);
			GL11.glVertex3f(x, yCenter, z);
		}
		GL11.glEnd();
	}


	public static void renderSide(Vector3fMax vector, Direction dir) {
		//Top side
		if (dir == Direction.UP) {
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
		}

		//Bottom side
		if (dir == Direction.DOWN) {
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
		}

		//West side
		if (dir == Direction.WEST) {
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
		}

		//East side
		if (dir == Direction.EAST) {
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
		}

		//North side
		if (dir == Direction.NORTH) {
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
		}

		//South side
		if (dir == Direction.SOUTH) {
			GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
			GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
			GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
			GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
		}
	}
	public static void drawCubeWithLines(int size, boolean isActive, float rF, float gF, float bF){
		float minPP = RenderHelper.pixel * (size+1);
		float minNP = RenderHelper.pixel * size;
		float maxPP = RenderHelper.pixel * (16-(size+1));
		float maxNP = RenderHelper.pixel * (16-size);

		Vector3fMax vNS = new Vector3fMax(minPP, minPP, minNP, maxPP, maxPP, maxNP);
		Vector3fMax vEW = new Vector3fMax(minNP, minPP, minPP, maxNP, maxPP, maxPP);
		Vector3fMax vTB = new Vector3fMax(minPP, minNP, minPP, maxPP, maxNP, maxPP);
		GL11.glColor3f(rF, gF, bF);
		RenderHelper.renderSide(vNS, Direction.NORTH);
		RenderHelper.renderSide(vNS, Direction.SOUTH);

		RenderHelper.renderSide(vEW, Direction.EAST);
		RenderHelper.renderSide(vEW, Direction.WEST);

		RenderHelper.renderSide(vTB, Direction.UP);
		RenderHelper.renderSide(vTB, Direction.DOWN);

		if(!isActive){
			GL11.glColor3f(1.0F, 0.0F, 0.0F);
		}else{
			GL11.glColor3f(0.0F, 1.0F, 0.0F);
		}
		Vector3fMax vEWS = new Vector3fMax(minNP, minNP, minNP, maxNP, maxNP, minPP);
		Vector3fMax vEWN = new Vector3fMax(minNP, minNP, maxPP, maxNP, maxNP, maxNP);

		Vector3fMax vEWT = new Vector3fMax(minNP, maxPP, minPP, maxNP, maxNP, maxPP);
		Vector3fMax vEWB = new Vector3fMax(minNP, minNP, minPP, maxNP, minPP, maxPP);

		Vector3fMax vNSW = new Vector3fMax(minNP, minNP, minNP, minPP, maxNP, maxNP);
		Vector3fMax vNSE = new Vector3fMax(maxPP, minNP, minNP, maxNP, maxNP, maxNP);
		Vector3fMax vNST = new Vector3fMax(minPP, maxPP, minNP, maxPP, maxNP, maxNP);
		Vector3fMax vNSB = new Vector3fMax(minPP, minNP, minNP, maxPP, minPP, maxNP);


		Vector3fMax vTBW = new Vector3fMax(minNP, minNP, minNP, minPP, maxNP, maxNP);
		Vector3fMax vTBE = new Vector3fMax(maxPP, minNP, minNP, maxNP, maxNP, maxNP);
		Vector3fMax vTBN = new Vector3fMax(minPP, minNP, minNP, maxPP, maxNP, minPP);
		Vector3fMax vTBS = new Vector3fMax(minPP, minNP, maxPP, maxPP, maxNP, maxNP);

		RenderHelper.renderSide(vEWS, Direction.EAST);
		RenderHelper.renderSide(vEWS, Direction.WEST);
		RenderHelper.renderSide(vEWN, Direction.EAST);
		RenderHelper.renderSide(vEWN, Direction.WEST);
		RenderHelper.renderSide(vEWT, Direction.EAST);
		RenderHelper.renderSide(vEWT, Direction.WEST);
		RenderHelper.renderSide(vEWB, Direction.EAST);
		RenderHelper.renderSide(vEWB, Direction.WEST);


		RenderHelper.renderSide(vNSW, Direction.NORTH);
		RenderHelper.renderSide(vNSW, Direction.SOUTH);
		RenderHelper.renderSide(vNSE, Direction.NORTH);
		RenderHelper.renderSide(vNSE, Direction.SOUTH);

		RenderHelper.renderSide(vNST, Direction.NORTH);
		RenderHelper.renderSide(vNST, Direction.SOUTH);
		RenderHelper.renderSide(vNSB, Direction.NORTH);
		RenderHelper.renderSide(vNSB, Direction.SOUTH);

		RenderHelper.renderSide(vTBW, Direction.UP);
		RenderHelper.renderSide(vTBW, Direction.DOWN);
		RenderHelper.renderSide(vTBE, Direction.UP);
		RenderHelper.renderSide(vTBE, Direction.DOWN);
		RenderHelper.renderSide(vTBN, Direction.UP);
		RenderHelper.renderSide(vTBN, Direction.DOWN);
		RenderHelper.renderSide(vTBS, Direction.UP);
		RenderHelper.renderSide(vTBS, Direction.DOWN);
	}

	public static void drawWhiteCube(Vector3fMax vector){
		//Top side
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());

		//Bottom side
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

		//West side
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());

		//East side
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());

		//North side
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMin());
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMin());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMin());
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMin());

		//South side
		GL11.glVertex3f(vector.getXMin(), vector.getYMin(), vector.getZMax());
		GL11.glVertex3f(vector.getXMax(), vector.getYMin(), vector.getZMax());
		GL11.glVertex3f(vector.getXMax(), vector.getYMax(), vector.getZMax());
		GL11.glVertex3f(vector.getXMin(), vector.getYMax(), vector.getZMax());
	}

    public static void drawGL11SideTopWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Top side
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BL
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BR
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TR
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TL
    }

    public static void drawGL11SideBottomWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Bottom side
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BR
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BL
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TL
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TR
    }

    public static void drawGL11SideWestWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw west side:
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BR
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMinV()); //TR
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TL
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMaxV()); //BL
    }

    public static void drawGL11SideEastWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw east side:
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMaxV()); //BL
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TL
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV()); //TR
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BR
    }

    public static void drawGL11SideNorthWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw north side
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMin(), icon.getMaxU(), icon.getMaxV()); //BL
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMin(), icon.getMaxU(), icon.getMinV()); //TL
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMin(), icon.getMinU(), icon.getMinV()); //TR
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMin(), icon.getMinU(), icon.getMaxV()); //BR
    }

    public static void drawGL11SideSouthWithTexture(Vector3fMax vector, TextureAtlasSprite icon) {
        //Draw south side
        vertexWithTexture(vector.getXMax(), vector.getYMin(), vector.getZMax(), icon.getMaxU(), icon.getMaxV()); //BL
        vertexWithTexture(vector.getXMax(), vector.getYMax(), vector.getZMax(), icon.getMaxU(), icon.getMinV()); //TL
        vertexWithTexture(vector.getXMin(), vector.getYMax(), vector.getZMax(), icon.getMinU(), icon.getMinV()); //TR
        vertexWithTexture(vector.getXMin(), vector.getYMin(), vector.getZMax(), icon.getMinU(), icon.getMaxV()); //BR
    }
    
    /**
     * <p>Computes the current GUI scale. Calling this method is equivalent to the following:<pre><code>
     * Minecraft mc = Minecraft.getMinecraft();
     * int scale = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight).getScaleFactor();</code></pre></p>
     *
     * @return the current GUI scale
     */
	//TODO: Port me
    /*
    public static int computeGuiScale() {
        Minecraft mc = Minecraft.getInstance();
        int scaleFactor = 1;
        
        int k = mc.gameSettings.guiScale;
        
        if (k == 0) {
            k = 1000;
        }

        while (scaleFactor < k && mc.displayWidth / (scaleFactor + 1) >= 320 && mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        return scaleFactor;
    }*/

}
