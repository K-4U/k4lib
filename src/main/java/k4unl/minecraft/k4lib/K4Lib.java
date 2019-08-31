package k4unl.minecraft.k4lib;


import k4unl.minecraft.k4lib.commands.CommandsRegistry;
import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.K4LibConfig;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import k4unl.minecraft.k4lib.network.NetworkHandler;
import k4unl.minecraft.k4lib.proxy.ClientProxy;
import k4unl.minecraft.k4lib.proxy.CommonProxy;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModInfo.ID)
public class K4Lib {

	public static K4Lib instance;

	public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	public K4Lib() {
		Config config = new K4LibConfig();
		config.load(ModInfo.ID);
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		MinecraftForge.EVENT_BUS.addListener(this::onServerStart);
	}

	@SubscribeEvent
	public void setup(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(NetworkHandler::init);

		proxy.init(event);
	}

	@SubscribeEvent
	public void onServerStart(FMLServerAboutToStartEvent event) {
		boolean b = event.getServer() instanceof DedicatedServer;
		CommandsRegistry commandsRegistry = new CommandsRegistry(b, event.getServer().getCommandManager().getDispatcher());
	}
}
