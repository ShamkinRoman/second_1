package ru.job4j.profession;

/**
 * Created by Администратор on 15.05.2017.
 */
public class Profession {
    /**Переменные класса.*/
    public String profa;
	/**Переменные класса.*/
    public String name;
	/**Переменные класса.*/
    public int age;
    /**Конструктор класса.*/
    public Profession() {

    }
    /**Конструктор класса.
	*@param profa profa
	 *@param name name
	 *@param age age*/
    public Profession(String profa, String name, int age) {
        this.profa = profa;
        this.name = name;
        this.age = age;
    }
    /**Метод получения значения.
	 **@return name
	 */
    public String getName() {
        return this.name;
    }
    /**Метод получения значения.
	 *@return age
	 */
    public int getAge() {
        return this.age;
    }
    /**Метод получения значения.
	 *@return profa
	 */
    public String getProfa() {
        return this.profa;
    }
}
