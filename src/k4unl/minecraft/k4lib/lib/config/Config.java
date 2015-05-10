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

    public boolean getBool(String key, String category){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public int getInt(String key, String category){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public String getString(String key, String category){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public char getChar(String key, String category){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public double getDouble(String key, String category){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public void setBool(String key, String category, boolean newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public void setInt(String key, String category, int newValue){
        for(ConfigOption config : configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public void setString(String key, String category, String newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public void setChar(String key, String category, char newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
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

    public void setDouble(String key, String category, double newValue){
        for(ConfigOption config : this.configOptions){
            if(config.getKey().equals(key) && config.getCategory().equals(category)){
                config.setDouble(newValue);
                config.saveConfig();
            }
        }
    }


    public void init(){
        configOptions.add(new ConfigOption("debug", false));
    }
}
