package k4unl.minecraft.k4lib;


import k4unl.minecraft.k4lib.commands.Commands;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import k4unl.minecraft.k4lib.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
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

    @Mod.EventHandler
    public void onServerStart(FMLServerStartingEvent event) {

        Commands.init(event);
    }
}
