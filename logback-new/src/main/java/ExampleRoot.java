import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleRoot {
    public static void main(String[] args) {
        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.baeldung.logback");
        logger.debug("Hi there!");

        ch.qos.logback.classic.Logger rootLogger =
                (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.debug("This message is logged because DEBUG == DEBUG.");

        rootLogger.setLevel(Level.ERROR);

        logger.warn("This message is not logged because WARN < ERROR.");
        logger.error("This is logged.");

    }
}
