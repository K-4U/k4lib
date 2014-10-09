package k4unl.minecraft.k4lib.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(getModId());

    /**
     * OVERRIDE ME
     * @return
     */
    private static String getModId(){
        return "";
    }

    public static void init() {

        logger.log(Level.INFO, getModId() + " starting up!");
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
	
}
