package k4unl.minecraft.k4lib.lib.config;


import net.minecraftforge.common.config.Configuration;

public class ConfigOption {
    private Configuration config;
    private String        key;

    private boolean isBool   = false;
    private boolean isInt    = false;
    private boolean isString = false;
    private boolean isChar   = false;
    private boolean isDouble = false;

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
    private String comment  = "";

    public ConfigOption(String _key, boolean _def) {
        key = _key;
        val = _def;
        def = _def;
        isBool = true;
        updateComment();
    }

    public ConfigOption(String _key, int _def) {
        key = _key;
        valInt = _def;
        defInt = _def;
        isInt = true;
        updateComment();
    }

    public ConfigOption(String _key, String _def) {
        key = _key;
        valString = _def;
        defString = _def;
        isString = true;
        updateComment();
    }

    public ConfigOption(String _key, char _def) {
        key = _key;
        valChar = _def;
        defChar = _def;
        isChar = true;
        updateComment();
    }

    public ConfigOption(String _key, double _def) {
        key = _key;
        valDouble = _def;
        defDouble = _def;
        isDouble = true;
        updateComment();
    }

    private void updateComment() {
        if (isDouble) {
            comment += " [Default: " + defDouble + "]";
        } else if (isBool) {
            comment += " [Default: " + def + "]";
        } else if (isChar) {
            comment += " [Default: " + defChar + "]";
        } else if (isInt) {
            comment += " [Default: " + defInt + "]";
        } else if (isString) {
            comment += " [Default: " + defString + "]";
        }
    }

    public String getComment() {
        return comment;
    }

    public ConfigOption setComment(String newComment) {
        comment = newComment;
        updateComment();
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ConfigOption setCategory(String newCat) {
        category = newCat;
        return this;
    }

    public String getKey() {
        return key;
    }

    public boolean getBool() {
        return val;
    }

    public void setBool(boolean newValue) {
        val = newValue;
    }

    public int getInt() {
        return valInt;
    }

    public void setInt(int newValue) {
        valInt = newValue;
    }

    public String getString() {
        return valString;
    }

    public void setString(String newVal) {
        valString = newVal;
    }

    public char getChar() {
        return valChar;
    }

    public void setChar(char newValue) {
        valChar = newValue;
    }

    public double getDouble() {
        return valDouble;
    }

    public void setDouble(double newValue) {
        valDouble = newValue;
    }

    public void saveConfig() {
        if (isBool) {
            config.get(category, key, def).set(val);
            if (!comment.equals("")) {
                config.get(category, key, def).comment = comment;
            }
        } else if (isInt) {
            config.get(category, key, defInt).set(valInt);
            if (!comment.equals("")) {
                config.get(category, key, defInt).comment = comment;
            }
        } else if (isString) {
            config.get(category, key, defString).set(valString);
            if (!comment.equals("")) {
                config.get(category, key, defString).comment = comment;
            }
        } else if (isChar) {
            config.get(category, key, defChar + "").set(valChar + "");
            if (!comment.equals("")) {
                config.get(category, key, defChar + "").comment = comment;
            }
        } else if (isDouble) {
            config.get(category, key, defDouble).set(valDouble);
            if (!comment.equals("")) {
                config.get(category, key, defDouble).comment = comment;
            }
        }
        config.save();
    }

    public void loadFromConfig(Configuration config) {
        this.config = config;
        if (isBool) {
            val = config.get(category, key, def).getBoolean(def);
            if (!comment.equals("")) {
                config.get(category, key, def).comment = comment;
            }
        } else if (isInt) {
            valInt = config.get(category, key, defInt).getInt(defInt);
            if (!comment.equals("")) {
                config.get(category, key, defInt).comment = comment;
            }
        } else if (isString) {
            valString = config.get(category, key, defString).getString();
            if (!comment.equals("")) {
                config.get(category, key, defString).comment = comment;
            }
        } else if (isChar) {
            String t = config.get(category, key, defChar + "").getString();
            if (!comment.equals("")) {
                config.get(category, key, defChar + "").comment = comment;
            }
            if (t.length() > 0) {
                valChar = t.charAt(0);
            }
        } else if (isDouble) {
            valDouble = config.get(category, key, defDouble).getDouble();
            if (!comment.equals("")) {
                config.get(category, key, defDouble).comment = comment;
            }
        }
    }
}
