package deepdive.functionalinterface;

public class Functional {
    public static void main(String[] args) {
        CustomInterface<String> ci = () -> "hello custom!!";

        String myCall = ci.myCall();
        System.out.println(myCall);

        ci.printDefault();
    }
}
