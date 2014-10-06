package k4unl.minecraft.k4lib.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    protected static Configuration config;

    public static void init(File configFile) {

        config = new Configuration(configFile);

        Config.loadConfigOptions(config);

        if (config.hasChanged()){
			config.save();
		}
	}
	
}
