package lesson8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

class MyObject<E> {
    private E obj;

    public MyObject(E obj) {
        this.obj = obj;
    }

    public E getObj() {
        return obj;
    }

    public void showType() {
        System.out.println("Тип объекта : " + obj.getClass().getName());
    }
}


public class ListCount {

    public void ListObjectsCount(ArrayList<MyObject> list) {
            int count = 0;

            if (!list.isEmpty()) {
                Iterator<MyObject> iter = list.iterator();
                while (iter.hasNext()) {
                    MyObject o = iter.next();
                    o.showType();
                    System.out.println(o.getObj() + "\n");
                    count++;

                }
            }
        System.out.println("Count: " + count);
        }
    }


class Main {

    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(9,8,7,6,5,4,3));
//
        ArrayList<MyObject> list = new ArrayList<>();
        list.add(new MyObject(10));
        list.add(new MyObject("Some string"));
        list.add(new MyObject(true));
        list.add(new MyObject(new Cat("Barsik")));

        ListCount lc = new ListCount();
        lc.ListObjectsCount(list);
    }
}

class Cat {
    String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat name " + getName();
    }
}