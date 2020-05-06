package internetshop;

import org.apache.log4j.Logger;

public class TestLogs {
    private static final Logger logger = Logger.getLogger(TestLogs.class);

    public static void main(String[] args) {
        TestLogs obj = new TestLogs();
        obj.runMe("mkyong");
    }

    private void runMe(String parameter) {

        if (logger.isDebugEnabled()) {
            logger.debug("This is debug : " + parameter);
        }

        if (logger.isInfoEnabled()) {
            logger.info("This is info : " + parameter);
        }

        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);
    }
}
