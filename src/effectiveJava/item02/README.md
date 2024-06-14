# 생성자에 매개변수가 많다면 빌더를 고려하라.

정적 팩터리와 생성자에게 똑같은 제약이 하나 존재하는데, 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 점이다. 과거 프래그래머들은 점층적 생성자 패턴(telescoping constructor pattern)을 즐겨 사용했다고 한다. 개인적으로 좋은 방법은 아닌 것 같다.

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;

    private final int calories;
    private final int fat;
    private final int sodium;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }
    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35);
    }
}

```

또 다른 방법이 존재한다. 이것은 자바빈즈 패턴(JavaBeans pattern)이라고 한다. 매개변수가 없는 생성자로 객체를 만든 후, Setter 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식이다. 자바빈즈는 심각한 단점을 지니고 있다. 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성도기 전까지 일관성이 무너진 상태에 놓이게 된다.

점층적 생성사 패턴에서는 매개변수들이 유효한지를 생성자에서만 확인하면 일관성을 유지할 수 있었는데, 그 장치가 완전히 사라진 것이다. 이처럼 일관성이 무너지는 문제 때문에 자바빈즈 패턴에서는 클래스를 불편으로 만들 수 없으며 스레드 안전성을 얻으려면 프로그래머가 추가 작업을 해줘야만 한다.

```java
public class NutritionFacts {
    private int servingSize = -1;
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;

    public NutritionFacts() {}

    // Setter Method
    public void setServingSize(int val) { servingSize = val; }
    public void setServings(int val) { servings = val; }

    // .........

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(240);
        // ...
        
    }
}

```

## 빌더 패턴 (Builder pattern)
```java
public class Main {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    private Main(Builder builder) {
        this.servingSize = builder.servingSize;;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        // 필수 매개변수를 받기 위한 생성자
        public Builder (int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        public Main build() {
            return new Main(this);
        }
    }

    public static void main(String[] args) {
        Main cocaCola = new Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
    }
}
```
