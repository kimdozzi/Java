package datastructure.queue;

import java.util.NoSuchElementException;

public class QueueClass<E> {
    int size = 0;
    Node<E> frontNode = null;
    Node<E> rearNode = null;

    public static void main(String[] args) {
        QueueClass<Integer> q = new QueueClass<>();
        q.enqueue(3);
        q.enqueue(1);
        q.enqueue(4);
        System.out.println(q.dequeue());
        System.out.println(q.size);

        MyArrayQueue<Integer> aq = new MyArrayQueue<>(10);
        aq.enqueue(2);
        aq.enqueue(3);
        System.out.println(aq.dequeue());
        System.out.println(aq.size);
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) {
            frontNode = newNode;
        } else {
            rearNode.next = newNode;
        }
        rearNode = newNode;
        size++;
    }

    public E dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is Empty.");
        }
        E item = frontNode.item;
        frontNode = frontNode.next;
        if (frontNode == null) {
            rearNode = null;
        }

        size--;
        return item;
    }

    public static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}

class MyArrayQueue<E> {
    Object[] data;
    int frontIndex;
    int rearIndex;
    int capacity;
    int size;

    public MyArrayQueue(int maxCapacity) {
        this.capacity = maxCapacity;
        data = new Object[capacity];
        frontIndex = 0;
        rearIndex = -1;
        size = 0;
    }

    public boolean enqueue(E element) {
        if (size == capacity) {
            return false;
        }

        rearIndex = (rearIndex + 1) % capacity;
        data[rearIndex] = element;
        size++;
        return true;
    }

    public E dequeue() {
        if (size == 0) {
            return null;
        }

        E value = (E) data[frontIndex];
        data[frontIndex] = null;
        frontIndex = (frontIndex + 1) % capacity;
        size--;
        return value;
    }
}
