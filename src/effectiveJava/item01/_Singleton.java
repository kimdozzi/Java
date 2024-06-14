package effective_java.item01;

public class _Singleton {
    private static _Singleton instance;
    private _Singleton() {}

    // 정적 팩토리 메서드
    public static synchronized _Singleton getInstance() {
        if (instance == null) {
            instance = new _Singleton();
        }
        return instance;
    }
    public static void main(String[] args) {
        _Singleton s1 = _Singleton.getInstance();
        _Singleton s2 = _Singleton.getInstance();
        _Singleton s3 = _Singleton.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}
