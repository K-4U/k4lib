package k4unl.minecraft.k4lib.client;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraftforge.client.model.pipeline.IVertexConsumer;
import net.minecraftforge.client.model.pipeline.VertexTransformer;

/**
 * @author Koen Beckers (K-4U)
 */
public class VertexTransformerTransparency extends VertexTransformer {
    private final float alpha;

    public VertexTransformerTransparency(IVertexConsumer parent, float alpha) {
        super(parent);
        this.alpha = alpha;
    }
    public void put(int element, float... data) {
        if (getVertexFormat().getElement(element).getUsage() == VertexFormatElement.EnumUsage.COLOR) {
            float[] newData = new float[data.length];
            for (int i = 0; i < data.length; i++)
                newData[i] = data[i];
            newData[0] = Math.min(Math.max(0, newData[3] * 1), 1);
            newData[1] = Math.min(Math.max(0, newData[3] * 1), 1);
            newData[2] = Math.min(Math.max(0, newData[3] * 1), 1);
            newData[3] = Math.min(Math.max(0, newData[3] * alpha), 1);
            super.put(element, newData);
            return;
        }
        if(getVertexFormat().getElement(element).getUsage() == VertexFormatElement.EnumUsage.POSITION){
            float[] newData = new float[data.length];
            for (int i = 0; i < data.length; i++)
                newData[i] = data[i];

            data[3] = 1.0F;
            return;
        }
        super.put(element, data);
    }

}
