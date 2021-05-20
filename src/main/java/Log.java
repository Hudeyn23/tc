import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    public static void logInfo(String msg) {
        logger.info(msg);
    }

    public static void logError(String msg) {
        logger.log(Level.WARNING, msg);
    }


}
