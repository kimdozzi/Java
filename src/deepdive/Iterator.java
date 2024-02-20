package datastructure;

import java.util.*;

public class Iterator {
    public static void main(String[] args) {
        LinkedList<Character> l = new LinkedList<>();
        l.add('a'); l.add('b'); l.add('c');

        ListIterator<Character> liter = l.listIterator();
        while (liter.hasNext()) {
            System.out.print(liter.next() + " ");
        }
        System.out.println();

        ListIterator<Character> literBack = l.listIterator(l.size());
        while (literBack.hasPrevious()) {
            System.out.print(literBack.previous() + " ");
        }
    }
}
