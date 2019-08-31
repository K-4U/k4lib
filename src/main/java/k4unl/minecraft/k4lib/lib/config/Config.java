package k4unl.minecraft.k4lib.lib.config;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public abstract class Config {

	public static final String CATEGORY_GENERAL = "general";

	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec SERVER_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;

	public static ForgeConfigSpec.BooleanValue debug;

	static {
		COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
		debug = COMMON_BUILDER.comment("Whether debug is enabled or not. Only enable this if you plan to actually debug stuff!").define("debug", false);

		COMMON_BUILDER.pop();
		COMMON_CONFIG = COMMON_BUILDER.build();

		CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
		CLIENT_BUILDER.pop();
		CLIENT_CONFIG = CLIENT_BUILDER.build();

		SERVER_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
		SERVER_BUILDER.pop();
		SERVER_CONFIG = SERVER_BUILDER.build();
	}

	public static void init(String modid) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);

		Config.loadConfig(CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
		Config.loadConfig(COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
		Config.loadConfig(SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve(modid + ".toml"));
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
}

