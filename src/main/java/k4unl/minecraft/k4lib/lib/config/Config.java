package k4unl.minecraft.k4lib.lib.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public abstract class Config {

	public static final String CATEGORY_GENERAL = "general";
	public static ForgeConfigSpec.BooleanValue debug;
	private final ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();
	private final ForgeConfigSpec.Builder serverBuilder = new ForgeConfigSpec.Builder();
	private final ForgeConfigSpec.Builder commonBuilder = new ForgeConfigSpec.Builder();
	public ForgeConfigSpec commonConfig;
	public ForgeConfigSpec serverConfig;
	public ForgeConfigSpec clientConfig;

	public Config() {
		commonBuilder.comment("General settings").push(CATEGORY_GENERAL);
		debug = commonBuilder.comment("Whether debug is enabled or not. Only enable this if you plan to actually debug stuff!").define("debug", false);
		buildCommon(commonBuilder);
		commonBuilder.pop();

		clientBuilder.comment("General settings").push(CATEGORY_GENERAL);
		buildClient(clientBuilder);
		clientBuilder.pop();


		serverBuilder.comment("General settings").push(CATEGORY_GENERAL);
		buildServer(serverBuilder);
		serverBuilder.pop();
	}

	private static void loadConfig(ForgeConfigSpec spec, Path path) {

		final CommentedFileConfig configData = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		configData.load();
		spec.setConfig(configData);
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {

	}

	@SubscribeEvent
	public static void onReload(final ModConfig.ConfigReloading configEvent) {
	}

	protected abstract void buildCommon(ForgeConfigSpec.Builder builder);

	protected abstract void buildServer(ForgeConfigSpec.Builder builder);

	protected abstract void buildClient(ForgeConfigSpec.Builder builder);

	public void load(String modid) {
		commonConfig = commonBuilder.build();
		clientConfig = clientBuilder.build();
		serverConfig = serverBuilder.build();

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, clientConfig);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfig);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverConfig);

		Config.loadConfig(clientConfig, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
		Config.loadConfig(serverConfig, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
		Config.loadConfig(commonConfig, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
	}
}

