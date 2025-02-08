import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleFileAppender {
    private static final Logger logger = LoggerFactory.getLogger(ExampleFileAppender.class);
    public static void main(String[] args) {
        logger.info("INFO from MainApp.");
        logger.warn("WARN from MainApp.");
        logger.error("ERROR from MainApp.");
    }
}
