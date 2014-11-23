package k4unl.minecraft.k4lib;

import k4unl.minecraft.k4lib.lib.config.ModInfo;
import k4unl.minecraft.k4lib.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

@Mod(
  modid = ModInfo.ID,
  name = ModInfo.NAME,
  version = ModInfo.VERSION
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
}
