package k4unl.minecraft.k4lib.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public Configuration config;

    public void init(Config toLoad, File configFile) {

        config = new Configuration(configFile);

        toLoad.loadConfigOptions(config);

        if (config.hasChanged()){
			config.save();
		}
	}
	
}
