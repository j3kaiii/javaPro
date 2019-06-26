package lesson6.part2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

    }

    public int[] getMass(int[] oldMass) {
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

    public boolean checkMass(int[] mass) {
        boolean one = false;
        boolean two = false;
        for (int i : mass) {
            if (i == 1) {
                one = true;
                break;
            }
        }
        for (int i : mass) {
            if (i == 4) {
                two = true;
                break;
            }
        }
        if (one && two) return true;
        return false;
    }

}
