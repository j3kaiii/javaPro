package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

class Part1<T> {
    private T[] mass;

    public Part1(T[] mass) {
        this.mass = mass;
    }

    public void change(int first, int second) {  // первое задание

        T temp = mass[first];
        mass[first] = mass[second];
        mass[second] = temp;
    }  // первое задание

    public ArrayList<T> massToArray (T[] massiv) {  // второе задание
        return new ArrayList<T>(Arrays.asList(massiv));
    } // второе задание
}

 public class Lesson1 {
    public static void main(String[] args) {

        // первое задание  - на смену элементов массива местами

        String[] mass = new String[] {"one", "two", "three", "four"};
        Integer[] intMass = new Integer[] {1, 2, 3, 4};

        System.out.println("String Before: " + Arrays.toString(mass));
        System.out.println("Numbers Before: " + Arrays.toString(intMass));

        Part1<String> part1= new Part1<String>(mass);
        Part1<Integer> part11 = new Part1<Integer>(intMass);

        part1.change(2,3);
        part11.change(1,2);

        System.out.println("String After: " + Arrays.toString(mass));
        System.out.println("Numbers After: " + Arrays.toString(intMass));

        // второе задание - массив в ArrayList

        ArrayList<String> stringList = part1.massToArray(mass);
        System.out.println("ArrayList<String> -  " + stringList);
        ArrayList<Integer> intList = part11.massToArray(intMass);
        System.out.println("ArrayList<Integer> -  " + intList);

    }
}
