package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class FruitBoxes {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<Apple>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        //appleBox.addFruit(new Orange());  -- не работает, этот бокс только для яблок

        Box<Orange> orangeBox = new Box<Orange>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        //orangeBox.addFruit(new Apple());  -- не работает, этот бокс только для апельсинов

        System.out.println("Apple box weight : " + appleBox.getWeight());
        System.out.println("Orange box weight : " + orangeBox.getWeight());

        Box<Apple> appleBox1 = new Box<>();
        appleBox.moveToOtherBox(appleBox1);
        //orangeBox.moveToOtherBox(appleBox1);  -- не работает, разные типы боксов
        System.out.println("Old apple box weight : " + appleBox.getWeight());
        System.out.println("New Apple box weight : " + appleBox1.getWeight());
        System.out.println(appleBox1.compare(orangeBox));
    }
}

abstract class Fruit {
    public abstract float getWeight();
}

class Apple extends Fruit {
    float weight = 1.0f;

    public float getWeight() {
        return weight;
    }
}

class Orange extends Fruit {
    static float weight = 1.5f;

    public float getWeight() {
        return weight;
    }
}

class Box<T> {
    private ArrayList<T> list;

    public Box() {
        this.list = new ArrayList<T>();
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public void moveToOtherBox(Box<T> otherBox) {
        for (T obj : list) {
            otherBox.addFruit(obj);
        }
        list.clear();
    }

    public float getWeight() {
        float weight = 0.0f;
        if (!list.isEmpty()) {
            Fruit f = (Fruit)list.get(0);
            weight =  list.size() * f.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> otherBox) {
        if (this.getWeight() == otherBox.getWeight()) return true;
        return false;
    }
}
