package k4unl.minecraft.k4lib.lib.config;


import net.minecraftforge.common.config.Configuration;

public class ConfigOption {
    private String key;

    private boolean isBool;
    private boolean isInt;
    private boolean isString;
    private boolean isChar;

    private boolean val;
    private boolean def;

    private int valInt;
    private int defInt;

    private String valString;
    private String defString;

    private char valChar;
    private char defChar;

    private String category = Configuration.CATEGORY_GENERAL;

    public ConfigOption(String _key, boolean _def) {
        key = _key;
        val = _def;
        def = _def;
        isBool = true;
        isInt = false;
        isString = false;
    }

    public ConfigOption(String _key, int _def){
        key = _key;
        valInt = _def;
        defInt = _def;
        isBool = false;
        isInt = true;
        isString = false;
    }

    public ConfigOption(String _key, String _def){
        key = _key;
        valString = _def;
        defString = _def;
        isBool = false;
        isInt = false;
        isString = true;
    }

    public ConfigOption(String _key, char _def){
        key = _key;
        valChar = _def;
        defChar = _def;
        isBool = false;
        isInt = false;
        isString = false;
        isChar = true;
    }

    public ConfigOption setCategory(String newCat){
        category = newCat;
        return this;
    }

    public String getKey(){
        return key;
    }

    public boolean getBool(){
        return val;
    }

    public int getInt(){
        return valInt;
    }

    public String getString(){
        return valString;
    }

    public char getChar(){
        return valChar;
    }

    public void loadFromConfig(Configuration config){
        if(isBool){
            val = config.get(category, key, def).getBoolean(def);
        }else if(isInt){
            valInt = config.get(category, key, defInt).getInt(defInt);
        }else if(isString){
            valString = config.get(category, key, defString).getString();
        }else if(isChar){
            String t = config.get(category, key, defChar + "").getString();
            if(t.length() > 0){
                valChar = t.charAt(0);
            }
        }
    }
}
