import java.util.Iterator;
import java.util.NoSuchElementException;

/** Lab 9, Exercise 4 (Intermediate): LinkedList with a private static nested Node and an inner Iterator. */
public class Lab09Ex4 {

    static class MyLinkedList<T> implements Iterable<T> {

        // static nested: a Node does not need a reference to the list that owns it
        private static class Node<T> {
            final T value;
            Node<T> next;
            Node(T value) { this.value = value; }
        }

        private Node<T> head;
        private Node<T> tail;
        private int size;

        void add(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        int size() { return size; }

        // inner (non-static) class: it uses the outer list's 'head' field directly
        private class ListIterator implements Iterator<T> {
            private Node<T> current = head;

            @Override public boolean hasNext() { return current != null; }

            @Override public T next() {
                if (current == null) throw new NoSuchElementException();
                T value = current.value;
                current = current.next;
                return value;
            }
        }

        @Override
        public Iterator<T> iterator() { return new ListIterator(); }
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("Alpha");
        list.add("Bravo");
        list.add("Charlie");
        list.add("Delta");

        System.out.println("Size: " + list.size());
        for (String s : list) {            // works because MyLinkedList implements Iterable
            System.out.println("  " + s);
        }
    }
}
