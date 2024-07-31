package datastructure.Queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Person> pq = new PriorityQueue<>(new PersonComparator());

        pq.offer(new Person("Alice", 30));
        pq.offer(new Person("Bob", 25));
        pq.offer(new Person("Charlie", 35));
        pq.offer(new Person("Alice", 25));
        pq.offer(new Person("Bob", 30));
        pq.offer(new Person("Charlie", 25));

        System.out.println("PriorityQueue sorted by name (asc) and age (desc):");
        while (!pq.isEmpty()) {
            Person person = pq.poll();
            System.out.println(person.name + " " + person.age);
        }
    }

    static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            int ageCompare = Integer.compare(o1.age, o2.age);
            if (ageCompare != 0) {
                return ageCompare;
            } else {
                return o1.name.compareTo(o2.name);
            }
        }
    }

    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}

