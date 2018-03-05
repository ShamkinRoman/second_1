package sqlTracker;

/**
 * Created by Up on 11.06.2017.
 */
public interface Input {
	/**Метод описывающий метод ask.
	*@param question question
	*@return input
	*/
    String ask(String question);

	/**
	 *
	 * @param question question
	 * @param range range
	 * @return integer
	 */
    int ask(String question, int[] range);
}
