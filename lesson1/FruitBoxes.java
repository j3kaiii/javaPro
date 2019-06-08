package lesson1;

import java.util.ArrayList;

public class FruitBoxes {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<Apple>();
        appleBox.addFruit(new Apple());

        Box<Orange> orangeBox = new Box<Orange>();
        orangeBox.addFruit(new Orange());
    }
}

abstract class Fruit {
    public abstract float getWeight();

}

class Apple extends Fruit {
    static float WEIGHT = 1.0f;

    public float getWeight() {
        return WEIGHT;
    }
}

class Orange extends Fruit {
    static float WEIGHT = 1.5f;

    public float getWeight() {
        return WEIGHT;
    }
}

class Box<T> {
    private ArrayList<T> list;
    private float weight;

    public Box() {
        this.weight = 0.0f;
        this.list = new ArrayList<T>();
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public float getWeight() {
        return weight; // list.size() * <T>.getWeight - не работает, не известен объект Т
    }
}
