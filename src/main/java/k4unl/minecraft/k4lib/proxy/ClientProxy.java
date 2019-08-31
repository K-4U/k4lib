package k4unl.minecraft.k4lib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void init(FMLCommonSetupEvent setupEvent) {

	}

	@Override
	public PlayerEntity getPlayer() {

		return Minecraft.getInstance().player;
	}

}
