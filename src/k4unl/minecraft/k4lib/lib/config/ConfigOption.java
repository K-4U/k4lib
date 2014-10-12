package k4unl.minecraft.k4lib.lib.config;


import net.minecraftforge.common.config.Configuration;

public class ConfigOption {
    private String key;

    private boolean isBool;
    private boolean isInt;
    private boolean isString;
    private boolean isChar;
    private boolean isDouble;

    private boolean val;
    private boolean def;

    private int valInt;
    private int defInt;

    private String valString;
    private String defString;

    private char valChar;
    private char defChar;

    private double valDouble;
    private double defDouble;

    private String category = Configuration.CATEGORY_GENERAL;
    private String comment = "";

    public ConfigOption(String _key, boolean _def) {
        key = _key;
        val = _def;
        def = _def;
        isBool = true;
        isInt = false;
        isString = false;
        isDouble = false;
    }

    public ConfigOption(String _key, int _def){
        key = _key;
        valInt = _def;
        defInt = _def;
        isBool = false;
        isInt = true;
        isString = false;
        isDouble = false;
    }

    public ConfigOption(String _key, String _def){
        key = _key;
        valString = _def;
        defString = _def;
        isBool = false;
        isInt = false;
        isString = true;
        isDouble = false;
    }

    public ConfigOption(String _key, char _def){
        key = _key;
        valChar = _def;
        defChar = _def;
        isBool = false;
        isInt = false;
        isString = false;
        isChar = true;
        isDouble = false;
    }

    public ConfigOption(String _key, double _def){
        key = _key;
        valDouble = _def;
        defDouble = _def;
        isBool = false;
        isInt = false;
        isString = false;
        isChar = false;
        isDouble = true;
    }

    public ConfigOption setCategory(String newCat){
        category = newCat;
        return this;
    }

    public ConfigOption setComment(String newComment){
        comment = newComment;
        return this;
    }

    public String getComment(){
        return comment;
    }

    public String getCategory(){
        return category;
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

    public double getDouble(){
        return valDouble;
    }

    public void loadFromConfig(Configuration config){
        if(isBool){
            val = config.get(category, key, def).getBoolean(def);
            if(!comment.equals("")) {
                config.get(category, key, def).comment = comment;
            }
        }else if(isInt){
            valInt = config.get(category, key, defInt).getInt(defInt);
            if(!comment.equals("")) {
                config.get(category, key, defInt).comment = comment;
            }
        }else if(isString){
            valString = config.get(category, key, defString).getString();
            if(!comment.equals("")) {
                config.get(category, key, defString).comment = comment;
            }
        }else if(isChar){
            String t = config.get(category, key, defChar + "").getString();
            if(!comment.equals("")) {
                config.get(category, key, defChar).comment = comment;
            }
            if(t.length() > 0){
                valChar = t.charAt(0);
            }
        }else if(isDouble){
            valDouble = config.get(category, key, defDouble).getDouble();
            if(!comment.equals("")) {
                config.get(category, key, defDouble).comment = comment;
            }
        }
    }
}
