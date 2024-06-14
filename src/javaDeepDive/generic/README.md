## 제네릭

클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법. 객체별로 다른 타입의 자료가 저장될 수  있도록 한다.

우리가 변수를 선언할 때 변수의 타입을 지정해주듯이, 제네릭은 객체에 타입을 지정해주는 것이라고 보면 된다. 제네릭은 <> 괄호 키워드를 사용하는데 이를 다이아몬드 연산자라고 한다. 그리고 이 꺾쇠 괄호 안에 식별자 기호를 지정함으로써 파라미터화 할 수 있다.

이것을 마치 메소드가 매개변수를 받아 사용하는 것과 비슷하여 제네릭의 타입 매개변수라고 부른다.

```java
List<T> : 타입 매개변수

List<String> stringList = new ArrayList<String>();
    매개변수화된 타입
```

### 타입 파라미터 정의

이 타입 매개변수는 제네릭을 이용한 클래스나 메소드를 설계할 때 사용된다. 예를들어 다음 코드는 제네릭을 감미한 클래스를 정의한 코드이다. 클래스명 옆에 <T> 기호로 제네릭을 붙여준 걸 볼 수 있다. 그리고 클래스 내부에서 식별자 기호 T를 클래스 필드와, 메소드의 매개변수의 타입으로 지정되어 있다.

```java
class FruitBox<T> {
    List<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }
}
```

제네릭 클래스를 만들었고, 이를 인스턴스화 해보자.

```java
public class Main {
    public static void main(String[] args) {
        FruitBox<Integer> intBox = new FruitBox<>();

        FruitBox<Double> doubleBox = new FruitBox<>();

        FruitBox<String> stringBox = new FruitBox<>();

        FruitBox<Apple> appleBox = new FruitBox<>();

    }
    private static class Apple {
    }
}
```

### 타입 파라미터 할당 가능 타입

제네릭에서 할당 받을 수 있는 타입은 Reference 타입 뿐이다. 즉, int, double과 같은 원시 타입은 제네릭 타입 파라미터로 넘길 수 없다.

wrapper 클래스와 boxing & unboxing: [https://inpa.tistory.com/entry/JAVA-☕-wrapper-class-Boxing-UnBoxing](https://inpa.tistory.com/entry/JAVA-%E2%98%95-wrapper-class-Boxing-UnBoxing)

제네릭 타입 파라미터에 클래스(Wrapper)가 타입으로 온다는 것은, 클래스끼리 상속을 통해 관계를 맺는 객체 지향 프로그래밍의 다형성 원리가 그대로 적용이 된다는 소리이다. (?) 어렵다

아래 예제 코드를 보면 타입 파라미터로 <Fruit>로 지정했지만 업캐스팅을 통해 그 자식 객체도 할당이 됨을 볼 수 있다. (업캐스팅 : [https://inpa.tistory.com/entry/JAVA-☕-업캐스팅-다운캐스팅-한방-이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%85%EC%BA%90%EC%8A%A4%ED%8C%85-%EB%8B%A4%EC%9A%B4%EC%BA%90%EC%8A%A4%ED%8C%85-%ED%95%9C%EB%B0%A9-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0) )

```java

class Fruit { }
class Apple extends Fruit { }
class Banana extends Fruit { }
class Grape { }

class FruitBox<T> {
    List<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }
}

public class Main {
    public static void main(String[] args) {
        FruitBox<Fruit> box = new FruitBox<>();

        // 제네릭 타입은 다형성 원리가 그대로 적용된다.
        box.add(new Fruit());
        box.add(new Apple());
        box.add(new Banana());
        // 불가능
        // box.add(new Grape());
    }
}
```

### 타입 파라미터 기호 네이밍

```java
<T> : 타입(type)

<E> : 요소(element), 예를 들어 List

<K> : 키(key), 예를 들어 Map<k,v>

<V> : 리턴 값 또는 매핑된 값(variable)

<N> : 숫자(number)

<S, U, V> : 2,3,4번째 타입
```

## 제네릭 사용 이유와 장점

### 1. 컴파일 타임에 타입 검사를 통해 예외 방지

자바에서 제네릭은 자바 1.5에 추가된 스펙이다. 그래서 JDK1.5 이전에서는 여러 타입을 다루기 위해 인수나 반환값으로 Object 타입을 사용했었다. 하지만 Object로 타입을 선언할 경우 반환된 Object 객체를 다시 원하는 타입으로 일일히 타입 변환을 해야 하며, 런타임 에러가 발생할 가능성도 존재한다.

제네릭은 클래스나 메서드를 정의할 때 타입 파라미터로 객체 서브 타입을 지정해줌으로써, 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거하여 개발을 용이하게 해준다.

```java
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
        Banana banana = (Banana) box.getFruit(1); // 에러 발생 
    }

    private static class Apple {
    }
}
```

### 2. 불필요한 캐스팅을 없애 성능 향상

Object를 사용하면, 요소를 가져올 때 반드시 다운 캐스팅을 통해 가져와야 했다. 이는 곧 추가적인 오버헤드가 발생하는 것과 같다. 반면, 제네릭은 미리 타입을 지정하고 제한해두기 때문에 형 변환의 번거로움을 줄일 수 있으며, 타입 검사에 들어가는 메모리를 줄일 수 있고 더불어 가독성도 좋아진다.

```java
// 미리 제네릭 타입 파라미터를 통해 형(type)을 지정해놓았기 때문에 별도의 형변환은 필요없다.
FruitBox<Apple> box = new FruitBox<>(arr);

Apple apple = box.getFruit(0);
Apple apple = box.getFruit(1);
Apple apple = box.getFruit(2);
```

## 제네릭 사용 주의사항

…

---

참고 자료

- [https://inpa.tistory.com/entry/JAVA-☕-제네릭Generics-개념-문법-정복하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%9C%EB%84%A4%EB%A6%ADGenerics-%EA%B0%9C%EB%85%90-%EB%AC%B8%EB%B2%95-%EC%A0%95%EB%B3%B5%ED%95%98%EA%B8%B0)