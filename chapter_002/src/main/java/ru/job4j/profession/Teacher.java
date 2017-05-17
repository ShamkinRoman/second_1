package ru.job4j.profession;

/**
 * Created by Администратор on 15.05.2017.
 */
public class Teacher extends Profession {
	/**Внутренне поле diplom, заранее заданное.*/
     private String diplom = "Правильный";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Teacher(String profa, String name, int age) {
        super(profa, name, age);
        this.diplom = diplom;
    }
    /**Метод обращение к внутреннему полю diplom.
     * @return diplom*/
    public String getDiplom() {
        return this.diplom;
    }
    /**Входящий класс Doctor.
     * @param  doctor doctor
     * @return result*/
    public String teachDoctor(Doctor doctor) {
        /**int zadanie переменная для вычисления времени учебы, для примера. Использую переменную age обоих классов.*/
        int zadanie = getAge() + doctor.getAge();
        //String result = (getProfa() + " " + getName() + " учит " + doctor.getProfa() + " " + doctor.getName() + ", занятие составило " + zadanie + " минут. Имя ребенка " + doctor.getNameChild());
        String result = String.format("%1$1s %2$1s учит %3$1s %4$1s, занятие составило %5$1s минут. %6$1s имя ребенка.", getProfa(), getName(), doctor.getProfa(), doctor.getName(), zadanie, doctor.getNameChild());
        return result;
    }
    /**Входящий класс Profession.
     * @param  profession profession
     * @return result*/
    public String teachProfession(Profession profession) {
        int zadanie = getAge() + profession.getAge();
        //String result = (getProfa() + " " + getName() + " учит " + profession.getProfa() + " " + profession.getName() + ", занятие составило " + zadanie + " минут.");
        String result = String.format("%1$1s %2$1s учит %3$1s %4$1s, занятие составило %5$1s минут. %6$1s имя ребенка.", getProfa(), getName(), profession.getProfa(), profession.getName(), zadanie);
        return result;
    }
}
