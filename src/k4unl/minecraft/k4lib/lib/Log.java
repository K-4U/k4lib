package k4unl.minecraft.k4lib.lib;

import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(ModInfo.ID);

    public static void init() {

    }

    public static void info(String message) {

        logger.log(Level.INFO, message);
    }

    public static void error(String message) {

        logger.log(Level.ERROR, message);
	}
	
	public static void warning(String message){
		logger.log(Level.WARN, message);
	}


    public static void debug(String message){
        if(Config.INSTANCE.getBool("debug")){
            logger.log(Level.INFO, message);
        }
    }
}
