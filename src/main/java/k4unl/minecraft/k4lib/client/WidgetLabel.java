package k4unl.minecraft.k4lib.client;

import net.minecraft.client.gui.widget.Widget;

public class WidgetLabel extends Widget {
    public WidgetLabel(int xIn, int yIn, String msg) {
        super(xIn, yIn, msg);
    }

    public WidgetLabel(int xIn, int yIn, int widthIn, int heightIn, String msg) {
        super(xIn, yIn, widthIn, heightIn, msg);
    }
}
