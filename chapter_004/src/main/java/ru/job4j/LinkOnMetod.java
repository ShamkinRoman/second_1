package ru.job4j;




public class LinkOnMetod {

    public static void main(String[] args) {
        Lamda001 lamda001 = new Lamda001();
        lamda001.biFun(1,3,5,UnitAdd::add,result-> System.out.println(result));
    }

}
class UnitAdd{
    public static double add( int a, int b){
        return a+b;
    }
}