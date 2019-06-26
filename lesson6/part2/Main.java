package lesson6.part2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] mass = new int[] {1,2,3,5,6,7,8,9,10,11};
        int[] newMass = getMass(mass);
        System.out.println(Arrays.toString(newMass));

    }

    public static int[] getMass(int[] oldMass) {


        int size = 0; // размер нового массива
        for (int i = oldMass.length - 1; i >= 0; i--) {
            if (oldMass[i] == 4) break;
            if (i == 0 && oldMass[0] != 4) throw new RuntimeException("нет четверок");
            size++;
        }
        int[] newMass = new int[size];
        for (int i = 0; i < size; i++) {
            newMass[i] = oldMass[oldMass.length - size + i];
        }
        return newMass;
    }

}
