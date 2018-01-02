package k4unl.minecraft.k4lib.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public void init(){

	}

    @Override
    public EntityPlayer getPlayer() {

        return FMLClientHandler.instance().getClientPlayerEntity();
    }
    
    public void postInit(FMLPostInitializationEvent event){
    
    }
}
