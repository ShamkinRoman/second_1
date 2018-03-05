package sqlTracker;

/**
 * Created by Up on 29.06.2017.
 */
public class MenuOutExceptoin extends RuntimeException {
    /**
     * Для вывода ошибок.
     * @param msg String
     */
    public MenuOutExceptoin(String msg) {
        super(msg);
    }
}
