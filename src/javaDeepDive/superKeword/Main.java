package javaDeepDive.superKeword;

class Animal {
    String name = "animalClass";

//    public Animal(String name) {
//        this.name = name;
//        System.out.println("Animal constructor called. Name: " + name);
//    }

    public Animal(String name) {
        this.name = name;
    }
    public Animal() {}

    void makeSound() {
        System.out.println("Aniaml makes a sound");
    }

}

class Dog extends Animal {
    String name = "dogClass";

    public Dog(String name) {
        //super(name);
        this.name = name;
    }

    void printNames() {
        System.out.println("Name in Dog: " + this.name);
        System.out.println("Name in Aniaml: " + super.name);
    }

//     부모 클래스의 생성자 호출
//    public Dog(String name, int age) {
//        super(name);
//        this.age = age;
//        System.out.println("Dog constructor called. Age: " + age);
//    }

    // 부모 클래스의 메서드 호출
    @Override
    void makeSound() {
        super.makeSound();
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");

        //dog.makeSound();

        dog.printNames();
    }
}

