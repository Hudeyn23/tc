import java.util.logging.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    public static void logInfo(String text) {
        logger.info(text);
    }


}
