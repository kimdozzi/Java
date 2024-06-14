package effectiveJava.item30;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        union(new HashSet(){}, new HashSet(){});
    }
}
