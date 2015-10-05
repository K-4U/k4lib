package k4unl.minecraft.k4lib;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import k4unl.minecraft.k4lib.commands.Commands;
import k4unl.minecraft.k4lib.lib.Log;
import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.ConfigHandler;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import k4unl.minecraft.k4lib.proxy.CommonProxy;

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

        Log.init();

        Config.INSTANCE.init();
        ConfigHandler.init(Config.INSTANCE, event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {

        Commands.init(event);
    }
}
