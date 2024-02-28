package deepdive.functionalinterface;

@FunctionalInterface
public interface CustomInterface<T> {
    // abstract method 오직 하나 !
    T myCall();

    default void printDefault() {
        System.out.println("hello Default.");
    }

    static void printStatic() {
        System.out.println("hello Static.");
    }
}
