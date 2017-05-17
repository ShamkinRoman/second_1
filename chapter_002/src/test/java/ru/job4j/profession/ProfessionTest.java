package ru.job4j.profession;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Created by Администратор on 15.05.2017.
 */
public class ProfessionTest {
    /**Класс доктор лечит класс Профессия.*/
    @Test
    public void whenDoctorHealingProfession() {
        Profession profession = new Profession("без професии", "Вася", 22);
        Doctor doctor = new Doctor("Доктор", "Саша", 22);
        String result = doctor.healProfession(profession);
        String expected = "Доктор Саша лечит без професии Вася, стоимость лечения составляет 44 рублей.";
        assertThat(result, is(expected));
    }
    /**Класс Инженер считает смету Классу Учитель.
     *А также выводит внутренне поле класса Учитель diplom*/
    @Test
    public void whenEngineerSmetaTeacher() {
        Engineer engineer = new Engineer("Инженер", "Паша", 21);
        Teacher teacher = new Teacher("Учитель", "Маша", 33);
        String result = engineer.smetaTeach(teacher);
        String expected = "Инженер Паша посчитал смету Учитель Маша и она равна 87 рублей. Диплом у Учитель Правильный.";
        assertThat(result, is(expected));
    }
    /**Класс Учитель учит Класс Доктор.
     *А также выводит внутренне поле класса Доктор nameChild*/
    @Test
    public void whenTeacherTeachDoctor() {
        Doctor doctor = new Doctor("Доктор", "Саша", 22);
        Teacher teacher = new Teacher("Учитель", "Маша", 33);
        String result = teacher.teachDoctor(doctor);
        String expected = "Учитель Маша учит Доктор Саша, занятие составило 55 минут. Полина имя ребенка.";
        assertThat(result, is(expected));
    }
}
