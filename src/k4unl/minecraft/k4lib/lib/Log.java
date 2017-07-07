package k4unl.minecraft.k4lib.lib;

import k4unl.minecraft.k4lib.lib.config.Config;
import k4unl.minecraft.k4lib.lib.config.ModInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = null;

    public static void init(Logger modLog) {
        logger = modLog;
    }

    private static void log(Level level, String format, Object... data) {
        logger.log(level, String.format(format, data));
    }

    public static void info(String message, Object... data) {
        log(Level.INFO, message, data);
    }

    public static void error(String format, Object... data) {
        log(Level.ERROR, format, data);
    }
	
	public static void warning(String message, Object... data){
		log(Level.WARN, message, data);
	}


    public static void debug(String message, Object... data){
        if(Config.INSTANCE.getBool("debug")){
            log(Level.INFO, message, data);
        }
    }
}
