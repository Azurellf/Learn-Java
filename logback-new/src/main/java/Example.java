import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {
    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        logger.info("Example log from {}", Example.class.getSimpleName());
        logger.trace("the root logger level is debug");
    }
}
