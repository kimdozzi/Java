package effectiveJava.item02;

public class Person {
    private final String name;
    private final int age;
    private final String address;
    private final String phone;

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }

    public static class Builder {
        // 필수 매개변수
        private final String name;
        private final int age;

        // 선택 매개변수
        private String address = null;
        private String phone = null;

        // 필수 매개변수를 받기 위한 생성자
        public Builder (String name, int age) {
            this.name = name;
            this.age = age;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person person = new Builder("Jisu", 30)
                .setAddress("서울특별시 강동구")
                .setPhone("010-0011-1100")
                .build();

        System.out.println(person.name + " " + person.age + " " + person.address + " " + person.phone);
    }
}