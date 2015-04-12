package k4unl.minecraft.k4lib.lib.config;

import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class Config {
    public List<ConfigOption> configOptions = new ArrayList<ConfigOption>();


    public void loadConfigOptions(Configuration c) {

        for (ConfigOption config : this.configOptions) {
            config.loadFromConfig(c);
        }
    }

    public boolean getBool(String key){
        for(ConfigOption config : this.configOptions){
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
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                return config.getString();
            }
        }
        return "";
    }

    public char getChar(String key){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                return config.getChar();
            }
        }
        return 0;
    }

    public double getDouble(String key){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                return config.getDouble();
            }
        }
        return 0;
    }

    public void setBool(String key, boolean newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                config.setBool(newValue);
                config.saveConfig();
            }
        }
    }

    public void setInt(String key, int newValue){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key)){
                config.setInt(newValue);
                config.saveConfig();
            }
        }
    }

    public void setString(String key, String newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                config.setString(newValue);
                config.saveConfig();
            }
        }
    }

    public void setChar(String key, char newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                config.setChar(newValue);
                config.saveConfig();
            }
        }
    }

    public void setDouble(String key, double newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key)){
                config.setDouble(newValue);
                config.saveConfig();
            }
        }
    }


    public void init(){
        configOptions.add(new ConfigOption("debug", false));
    }
}
