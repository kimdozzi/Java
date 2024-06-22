package effectiveJava.item01;

public class Boolean {
    private final boolean value;

    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);

    public Boolean(boolean value) {
        this.value = value;
    }
    public static Boolean valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }
}

