package ru.job4j.profession;
/**
 * Created by Администратор on 15.05.2017.
 */
public class Doctor extends Profession {
	/**Внутренне поле nameChild, заранее заданное.*/
       private String nameChild = "Полина";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Doctor(String profa, String name, int age) {
        super(profa, name, age);
        this.nameChild = nameChild;
    }
	/**Метод обращение к внутреннему полю nameChild.
     * @return nameChild*/
    public String getNameChild() {
            return this.nameChild;
    }

    /** Входящий класс Profession.
     * исполльзуются общие поля
     * @param profession profession
     * @return result*/
    public String healProfession(Profession profession) {
        int hour = getAge() + profession.getAge();
        //String result = (getProfa() + " " + getName() + " лечит " + profession.getProfa() + " " + profession.getName() + ", стоимость лечения составляет " + hour + " рублей.");
		String result = String.format("%1$1s %2$1s лечит %3$1s %4$1s, стоимость лечения составляет %5$1s рублей.", getProfa(), getName(), profession.getProfa(), profession.getName(), hour);
        return result;
    }
    /**Входящий класс Doctor.
     * @param engineer engineer
     * @return result*/
    public String healEngineer(Engineer engineer) {
        int hour = getAge() + engineer.getAge();
        //String result = (getProfa() + " " + getName() + " лечит " + engineer.getProfa() + " " + engineer.getName() + ", стоимость лечения составляет " + hour + " рублей. Адрес " + engineer.getAdress());
        String result = String.format("%1$1s %2$1s лечит %3$1s %4$1s, стоимость лечения составляет %5$1s рублей. %6$1s адрес проживания.", getProfa(), getName(), engineer.getProfa(), engineer.getName(), hour, engineer.getAdress());
        return result;
    }
}
