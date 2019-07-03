package lesson8;

import java.util.*;


public class ListCount<T> {

    public void ListObjectsCount(ArrayList<T> list) {
            int count = 0;

            if (!list.isEmpty()) {
                Iterator<T> iter = list.iterator();
                while (iter.hasNext()) {
                    count++;
                    iter.next();
                }
            }
        System.out.println("Count: " + count);
        }
    }


class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9,8,7,6,5,4,3));
        ListCount lc = new ListCount();
        lc.ListObjectsCount(list);
    }
}