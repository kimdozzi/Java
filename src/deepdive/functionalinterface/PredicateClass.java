package deepdive.functionalinterface;

import java.util.function.Predicate;

public class PredicateClass {
    public static void main(String[] args) {

        String s = "abc";
        Predicate<String> stringPredicate1 = (str) -> s.equals(str);
        Predicate<String> stringPredicate2 = (str) -> s.contains(str);

        Predicate<String> MergedTwoPredicates = stringPredicate1.and(stringPredicate2);
        MergedTwoPredicates.test("abc"); // true

    }
}
