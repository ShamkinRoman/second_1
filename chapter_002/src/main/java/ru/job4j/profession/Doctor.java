package ru.job4j.profession;
/**
 * Created by Администратор on 15.05.2017.
 */
public class Doctor extends Profession {
	/**Внутренне поле nameChild, заранее заданное.*/
        String nameChild = "Полина";
	/**Конструктор класса Teacher.
	 *@param profa profa
	 *@param name name
	 *@param age age*/
    public Doctor(String profa, String name, int age) {
        this.profa = profa;
        this.name = name;
        this.age = age;
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
        int hour = this.age + profession.getAge();
        String result = (this.profa + " " + this.name + " лечит " + profession.getProfa() + " " + profession.getName() + ", стоимость лечения составляет " + hour + " рублей.");
        return result;
    }
    /**Входящий класс Doctor.
     * @param engineer engineer
     * @return result*/
    public String healEngineer(Engineer engineer) {
        int hour = this.age + engineer.getAge();
        String result = (this.profa + " " + this.name + " лечит " + engineer.getProfa() + " " + engineer.getName() + ", стоимость лечения составляет " + hour + " рублей. Адрес " + engineer.getAdress());
        return result;
    }
}
