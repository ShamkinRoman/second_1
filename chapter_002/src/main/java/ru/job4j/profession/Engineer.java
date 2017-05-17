package ru.job4j.profession;

/**
 * Created by Администратор on 15.05.2017.
 */
public class Engineer extends Profession {
	/**Внутренне поле adress, заранее заданное.*/
    private String adress = "г. Монте-Карло улица Зеленая дом 8.";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Engineer(String profa, String name, int age) {
       super(profa, name, age);
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
        int price = getAge() + teacher.getAge() * 2;
        //String result = (getProfa() + " " + getName() + " посчитал смету " + teacher.getProfa() + " " + teacher.getName() + " и она равна " + price + " рублей. Диплом у " + teacher.getProfa() + " " + teacher.getDiplom() + ".");
        String result = String.format("%1$1s %2$1s посчитал смету %3$1s %4$1s и она равна %5$1s рублей. Диплом у %3$1s %6$1s.", getProfa(), getName(), teacher.getProfa(), teacher.getName(), price, teacher.getDiplom());
        return result;
    }
    /**Входящий класс Profession.
     * @param  profession profession
     * @return result*/
    public String smetaProfession(Profession profession) {
        int price = getAge() + profession.getAge() * 2;
        //String result = (getProfa() + " " + getName() + " посчитал смету " + profession.getProfa() + " " + profession.getName() + " и она равна " + price + " рублей.");
        String result = String.format("%1$1s %2$1s посчитал смету %3$1s %4$1s и она равна %5$1s рублей.", getProfa(), getName(), profession.getProfa(), profession.getName(), price);
        return result;
    }
}
