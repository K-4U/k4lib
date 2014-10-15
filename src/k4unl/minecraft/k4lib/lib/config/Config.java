package k4unl.minecraft.k4lib.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class Config {
    public final List<ConfigOption> configOptions = new ArrayList<ConfigOption>();

    public void loadConfigOptions(Configuration c) {

        for (ConfigOption config : configOptions) {
            config.loadFromConfig(c);
        }
    }

    public boolean getBool(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                return config.getBool();
            }
        }
        return false;
    }

    public int getInt(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                return config.getInt();
            }
        }
        return 0;
    }

    public String getString(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                return config.getString();
            }
        }
        return "";
    }

    public char getChar(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                return config.getChar();
            }
        }
        return 0;
    }

    public double getDouble(String key){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                return config.getDouble();
            }
        }
        return 0;
    }


    public void init(){};
}
