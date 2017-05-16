package ru.job4j.profession;

/**
 * Created by Администратор on 15.05.2017.
 */
public class Engineer extends Profession {
	/**Внутренне поле adress, заранее заданное.*/
    String adress = "г. Монте-Карло улица Зеленая дом 8.";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Engineer(String profa, String name, int age) {
        this.profa = profa;
        this.name = name;
        this.age = age;
        this.adress = adress;
    }
	/**Метод обращение к внутреннему полю adress.
     * @return adress*/
    public String getAdress() {
        return this.adress;
    }
    /**Входящий класс Teacher.
     * @param  teacher teacher
     * @return result*/
    public String smetaTeach(Teacher teacher) {
        int price = this.age + teacher.getAge() * 2;
        String result = (this.profa + " " + this.name + " посчитал смету " + teacher.getProfa() + " " + teacher.getName() + " и она равна " + price + " рублей. Диплом у " + teacher.getProfa() + " " + teacher.getDiplom() + ".");
        return result;
    }
    /**Входящий класс Profession.
     * @param  profession profession
     * @return result*/
    public String smetaProfession(Profession profession) {
        int price = this.age + profession.getAge() * 2;
        String result = (this.profa + " " + this.name + " посчитал смету " + profession.getProfa() + " " + profession.getName() + " и она равна " + price + " рублей.");
        return result;
    }
}
