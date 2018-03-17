package parserSQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Main class for starting parser.
 */
public class StartParser {
    private int MINUTA_IN_SECOND = 60;
    private int HOUR_IN_MINUTA = 60;
    private int DAY_IN_HOUR = 24;
    private int DAY_IN_SECOND = DAY_IN_HOUR * HOUR_IN_MINUTA * MINUTA_IN_SECOND;
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public static void main(String[] args) {
        new StartParser().start();
    }

    public void start() {
        CheckConnectToDataBase check = new CheckConnectToDataBase();
        Storage storage = new Storage();
        int startupFrequency = Integer.parseInt(storage.getValue("startupFrequency"));
        int delayTime = DAY_IN_SECOND / startupFrequency;
        log.warn(String.format("Check forum always %s second", delayTime));
        do {
            check.check();
            try {
                Thread.sleep(delayTime * 1000);
            } catch (InterruptedException e) {
                log.warn("You have a problem with start program", e);
            }
        } while (true);
    }
}
