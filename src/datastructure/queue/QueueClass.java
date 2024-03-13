package datastructure.queue;

import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueClass<E> {
    int size = 0;
    Node<E> frontNode = null;
    Node<E> rearNode = null;

    public static class Node<E> {
        E item;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) frontNode = newNode;
        else rearNode.next = newNode;
        rearNode = newNode;
        size++;
    }

    public E dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is Empty.");
        }
        E item = frontNode.item;
        frontNode = frontNode.next;
        if (frontNode == null) rearNode = null;

        size--;
        return item;
    }

    public static void main(String[] args) {
        QueueClass<Integer> q = new QueueClass<>();

        q.enqueue(3);
        q.enqueue(1);
        q.enqueue(4);

        System.out.println(q.dequeue());
        System.out.println(q.size);
    }
}
