package k4unl.minecraft.k4lib.commands;


import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Created by koen_000 on 12-4-2015.
 */
public class Commands {

    public static void init(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandK4Lib());
        event.registerServerCommand(new CommandCoords());
    }
}
