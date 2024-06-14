# 정적 펙토리 메서드
정적 팩토리 메서드는 개발자가 구성한 Static Method를 통해 간접적으로 생성자를 호출하는 객체를 생성하는 메서드이다.

<aside>
💡 정적 팩토리 메서드와 디자인 패턴의 하나인 팩토리 메서드와 다르다.
</aside>

## 장점
## 1. 이름을 가질 수 있다. 
즉, 생성 목적에 대한 이름 표현이 가능한 것이다. 단순히 생성자의 역할을 대신 이행하는 것 뿐만 아니라 개발자가 좀 더 가독성 좋은 코드를 작성하고 객체 지향적으로 프로그래밍할 수 있게 도와준다.

지금까지 클래스를 설계할 때 다양한 타입의 객체를 생성하기 위해 생성 목적에 따라 생성자를 오버로딩하여 구분하여 사용해왔다. 하지만, new 키워드를 사용하려면 개발자는 해당 생성자의 인자 순서와 내부 구조를 알고 있어야 목적에 맞게 객체를 생성할 수가 있다는 번거로움이 있다. 따라서 정적 메서드를 통해 적절한 메서드 네이밍을 해준다면 반환될 객체의 특성을 한번에 유추할 수 있고, 어떤 값을 이용해 객체를 만들려고 하는지 쉽게 설계 의도를 전달할 수 있다.

정적 팩토리 메서드를 구성하고자 한다면, 반드시 생성자에 private 접근 제어자를 두어 외부에서 new 키워드를 이용한 객체 생성을 막자!
```java
class Car {
    private String brand;
    private String color;
    
    // private 생성자 
    private Car(String brand, String color) {
            this.brand = brand;
            this.color =color;
        }
        
    // 정적 팩토리 메서드 (매개변수 하나는 from 네이밍)
    public static Car brandBlackFrom(String brand) {
        return new Car(brand, "black");
    }
    
    // 정적 팩토리 메서드 (매개변수 여러개는 of 네이밍)
    public static Car brandColorOf(String brand, String color) {
        return new Car(brand, color);
    }
}
```

```java
public static void main(String[] args) {
    // 검정색 테슬라 자동차 
    Car teslaCar = Car.brandBlackFrom("Tesla");

    // 빨간색 BMW 자동차
    Car bmwRedCar = Car.brandColorOf("BMW", "Red");
}
```

## 2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다. 
이 덕분에 불변 클라스는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성을 피할 수 있다.
```java
class Day {
private String day;

    public Day(String day) { this.day = day; }

    public String getDay() { return day; }
}

// Day 객체를 생성하고 관리하는 Flyweight 팩토리 클래스
class DayFactory {

	// Day 객체를 저장하는 캐싱 저장소 역할
    private static final Map<String, Day> cache = new HashMap<>();
	
    // 자주 사용될것 같은 Day 객체 몇가지를 미리 등록한다
    static { 
    	cache.put("Monday", new Day("Monday")); 
        cache.put("Tuesday", new Day("Tuesday")); 
        cache.put("Wednesday", new Day("Wednesday")); 
    }

    // 정적 팩토리 메서드 (인스턴스에 대해 철저한 관리)
    public static Day from(String day) {

        if(cache.containsKey(day)) {
            // 캐시 되어있으면 그대로 가져와 반환
            System.out.println("해당 요일은 캐싱되어 있습니다.");
            return cache.get(day);
        } else {
            // 캐시 되어 있지 않으면 새로 생성하고 캐싱하고 반환
            System.out.println("해당 요일은 캐싱되어 있지 않아 새로 생성하였습니다.");
            Day d = new Day(day);
            cache.put(day, d);
            return d;
        }
    }
}
class Main {
    public static void main(String[] args) {
        // 이미 등록된 요일 가져오기
        Day day = DayFactory.from("Monday");
        System.out.println(day.getDay());

        // 등록되지 않은 요일 가져오기
        day = DayFactory.from("Friday");
        System.out.println(day.getDay());
    }
}
```
대표적인 예로 싱글톤 패턴을 들 수 있는데, getInstance()라는 정적 팩토리 메서드를 사용해 오로지 하나의 객체만 반환하도록 하여 객체를 재사용해 메모리를 아끼도록 유도할 수 있다.

```java
class effectiveJava.item01.Singleton{
private static effectiveJava.item01.Singleton instance;

private Singleton(){
        }

// 정적 팩토리 메서드
public static synchronized effectiveJava.item01.Singleton getInstance(){
        if(instance==null){
        instance=new effectiveJava.item01.Singleton();
        }
        return instance;
        }
        }

class Main {
    public static void main(String[] args) {
        effectiveJava.item01.Singleton s1 = effectiveJava.item01.Singleton.getInstance();
        effectiveJava.item01.Singleton s2 = effectiveJava.item01.Singleton.getInstance();
        effectiveJava.item01.Singleton s3 = effectiveJava.item01.Singleton.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}
```

## 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다. 
이 능력은 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 유연성을 선물한다. API를 만들 때 이 유연성을 응용하면 구현 클래스를 공개하지 않고도 그 객체를 반환할 수 있어 API를 작게 유지할 수 있다. 이는 인터페이스를 정적 팩토리 메서드의 반환 타입으로 사용하는 인터페이스 기반 프레임워크를 만드는 핵심 기술이기도 하다.

인터페이스 기반 프레임워크 또는 프로그래밍으로 불리는 이 방법은 구상 클래스(Concrete Class)를 직접 참조하지 않고, 상위 인터페이스를 선언하고 다형성을 이용하여 유연하게 프로그램을 작성하는 방법이다.
이러한 방식으로 프로그래밍을 하지 않는다면, 의존성을 다른 구현으로 대체하려면 의존 클래스까지 변경해야 한다고 한다.
```java
interface SmarPhone {
    public static SmarPhone getSamsungPhone() {
        return new Galaxy();
    }

    public static SmarPhone getApplePhone() {
        return new IPhone();
    }

    public static SmarPhone getChinesePhone() {
        return new Huawei();
    }
}
```
## 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다. 
반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관없다. 
```java
interface SmarPhone {
    public static SmarPhone getPhone(int price) {
        if(price > 100000) {
            return new IPhone();
        }

        if(price > 50000) {
            return new Galaxy();
        }

        return new Huawei();
    }
}
```
## 5. 정적 팩토리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
생성자를 사용하는 경우 외부에 내부 구현을 드러내야 하는데, 정적 팩토리 메서드는 구현부를 외부로 부터 숨길 수 있어 캡슐화 및 정보 은닉을 할 수 있다는 특징이 있다. 또한, 노출하지 않는다는 특징은 정보 은닉성을 가지기도 하지만 동시에 사용하고 있는 구현체를 숨겨 의존성을 제거해주는 장점도 지니고 있다.
```java
interface Grade {
    String toText();
}

class A implements Grade {
    @Override
    public String toText() {return "A";}
}

class B implements Grade {
    @Override
    public String toText() {return "B";}
}

class C implements Grade {
    @Override
    public String toText() {return "C";}
}

class D implements Grade {
    @Override
    public String toText() {return "D";}
}

class F implements Grade {
    @Override
    public String toText() {return "F";}
}
class GradeCalculator {
    // 정적 팩토리 메서드
    public static Grade of(int score) {
        if (score >= 90) {
            return new A();
        } else if (score >= 80) {
            return new B();
        } else if (score >= 70) {
            return new C();
        } else if (score >= 60) {
            return new D();
        } else {
            return new F();
        }
    }
}
class Main {
    public static void main(String[] args) {
        String jeff_score = GradeCalculator.of(36).toText();
        String herryPorter_score = GradeCalculator.of(99).toText();

        System.out.println(jeff_score); // F
        System.out.println(herryPorter_score); // A
    }
}
```

## 단점
1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩토리 메서드만 제공하면 하위 클래스를 만들 수 없다. 컬렉션 프레임워크의 유틸리티 구현 클래스들은 상속을 할 수 없다는 말이다. 어찌 보면 이 제약은 상속보다 컴포지션을 사용하도록 유도하고 불변 타입으로 만들려면 이 제약을 지켜야 한다는 점에서 오히려 장점으로 받아들일 수도 있다.
2. 정적 팩토리 메서드는 프로그래머가 찾기 어렵다. 생성자처럼 API 설명에 명확히 드러나지 않으니 사용자는 정적 팩토리 메서드 방식 클래스를 인스턴스화할 방법을 알아내야 한다. 즉, JavaDoc와 같은 문서에 정의되어 있지 않고 개발자가 임의로 만든 메서드이기 때문에, 스스로 공부하고 개발해야 한다. 따라서, 클래스 설계자는 API 문서를 깔끔하게 작성할 필요가 있으며, 정적 팩토리 메서드를 작성할 때 네이밍 컨벤션을 지킴으로써 문제점을 극복해야 한다.


## 정적 팩토리 메서드 네이밍 규칙
1. from: 하나의 매개 변수를 받아서 객체를 생성
```java
Member member = Member.from(1L);
```
2. of: 여러개의 매개 변수를 받아서 객체를 생성
```java
Member member = Member.of(1L, "홍길동");
```
3. geInstance | instance: 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있음
```java
// valueOf: from과 of의 더 자세한 버전
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
```
4. newInstance | create: 항상 새로운 인스턴스를 생성
```java
// instance 혹은 getInstance : (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.
StackWalker luke = StackWalker.getInstance(options);

Calendar instance = Calendar.getInstance();
```
5. get[OrderType]: 다른 타입의 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있음
```java
// create 혹은 newInstance : instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다.
Object newArray = Array.newInstance(classObject, arrayLen);
```
6. new[OrderType]: 항상 다른 타입의 새로운 인스턴스를 생성
```java
// getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. Type은 팩터리 메서드가 반환할 객체의 타입이다.
FileStore fs = Files.getFileStore(path);
```


```java
// newType : newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. Type은 팩터리 메서드가 반환할 객체의 타입이다.
BufferedReader br = Files.newBufferdReader(path);
```

```java
// type : getType과 newType의 간결한 버전
List<Complaint> litany = Collections.list(legacyLitany);
```

---
참고
* 이펙티브 자바
* https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EB%8C%80%EC%8B%A0-%EC%82%AC%EC%9A%A9%ED%95%98%EC%9E%90