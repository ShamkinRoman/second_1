package sqlTracker;

/**
 * Created by Up on 18.06.2017.
 */
public class StunInput implements Input {
	/**Переменная answers.*/
    private String[] answers;
	/**Переменная position.*/
    private int position = 0;
	/**Конструктор.
	*@param answers answers
	*/
    StunInput(String[] answers) {
        this.answers = answers;
    }
	 /**Ввод информации через массив строк.
	*@param question question
	*@return answers answers
	*/
    public String ask(String question) {
        return answers[position++];
    }

    /**
     *
     * @param question question.
     * @param range range
     * @return return
     */
    public int ask(String question, int[] range) {
        return -1;
    }

}
