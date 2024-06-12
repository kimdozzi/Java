package java_syntax.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        // FQCN (Fully Qualified Class Name) : object, 함수, 변수의 계층적 구조를 명시적으로 모두 표현하는 것
        // JAVA의 경우 클래스가 포함된 패키지를 명시한다.

        Class<Car> carClass = Car.class;
        Arrays.stream(carClass.getDeclaredFields())
                .map(Field::getName)
                .map(fieldName -> "field name : " + fieldName)
                .forEach(System.out::println);

        System.out.println(carClass.getDeclaredField("carPosition"));
    }
}
