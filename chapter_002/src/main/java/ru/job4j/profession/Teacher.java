package ru.job4j.profession;

/**
 * Created by Администратор on 15.05.2017.
 */
public class Teacher extends Profession {
	/**Внутренне поле diplom, заранее заданное.*/
     String diplom = "Правильный";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Teacher(String profa, String name, int age) {
        this.profa = profa;
        this.name = name;
        this.age = age;
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
        int zadanie = this.age + doctor.getAge();
        String result = (this.profa + " " + this.name + " учит " + doctor.getProfa() + " " + doctor.getName() + ", занятие составило " + zadanie + " минут. Имя ребенка " + doctor.getNameChild());
        return result;
    }
    /**Входящий класс Profession.
     * @param  profession profession
     * @return result*/
    public String teachProfession(Profession profession) {
        int zadanie = this.age + profession.getAge();
        String result = (this.profa + " " + this.name + " учит " + profession.getProfa() + " " + profession.getName() + ", занятие составило " + zadanie + " минут.");
        return result;
    }
}
