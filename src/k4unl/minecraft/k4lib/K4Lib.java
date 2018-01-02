package k4unl.minecraft.k4lib;


import k4unl.minecraft.k4lib.commands.Commands;
import k4unl.minecraft.k4lib.lib.Log;
import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.ConfigHandler;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import k4unl.minecraft.k4lib.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
  modid = ModInfo.ID,
  name = ModInfo.NAME,
  version = ModInfo.VERSION,
  acceptableRemoteVersions="*"
)

public class K4Lib {



    //This is the instance that Forge uses:
    @Mod.Instance(value = ModInfo.ID)
    public static K4Lib instance;

    @SidedProxy(
            clientSide = "k4unl.minecraft.k4lib.proxy.ClientProxy",
            serverSide = "k4unl.minecraft.k4lib.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    private ConfigHandler ConfigHandler = new ConfigHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Log.init(event.getModLog());

        Config.INSTANCE.init();
        ConfigHandler.init(Config.INSTANCE, event.getSuggestedConfigurationFile());
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {

        Commands.init(event);
    }
}
