package k4unl.minecraft.k4lib.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private static final List<ConfigOption> configOptions = new ArrayList<ConfigOption>();
	
	public static void loadConfigOptions(Configuration c){

		for(ConfigOption config : configOptions){
			config.loadFromConfig(c);
		}
	}

    public static boolean getBool(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey() == key){
                return config.getBool();
            }
        }
        return false;
    }

    public static int getInt(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey() == key){
                return config.getInt();
            }
        }
        return 0;
    }

    public static String getString(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey() == key){
                return config.getString();
            }
        }
        return "";
    }

    public static char getChar(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey() == key){
                return config.getChar();
            }
        }
        return 0;
    }

}
