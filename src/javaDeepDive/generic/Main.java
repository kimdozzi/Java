package java_syntax.generic;

class FruitBox<T> {
    private T[] fruit;

    public FruitBox(T[] fruit) {
        this.fruit = fruit;
    }

    public T getFruit(int index) {
        return fruit[index];
    }
}

public class Main {
    public static void main(String[] args) {
        Apple[] arr = {
                new Apple(),
                new Apple()
        };
        FruitBox<Apple> box = new FruitBox<>(arr);

        Apple apple = (Apple) box.getFruit(0);
        // Banana banana = (Banana) box.getFruit(1);
    }

    private static class Apple {
    }
}