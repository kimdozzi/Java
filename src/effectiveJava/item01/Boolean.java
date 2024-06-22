package effectiveJava.item01;

public class BooleanClass {
    public static final Boolean TRUE = true;
    public static final Boolean FALSE = false;

    public static boolean valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }
}
