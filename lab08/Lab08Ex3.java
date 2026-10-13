import java.util.ArrayList;
import java.util.List;

/** Lab 8, Exercise 3 (Intermediate): generic Stack<T> backed by an ArrayList<T>. */
public class Lab08Ex3 {

    static class Stack<T> {
        private final List<T> items = new ArrayList<>();

        void push(T item) { items.add(item); }

        T pop() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            return items.remove(items.size() - 1);
        }

        T peek() {
            if (isEmpty()) throw new IllegalStateException("Stack is empty");
            return items.get(items.size() - 1);
        }

        boolean isEmpty() { return items.isEmpty(); }
        int size() { return items.size(); }
    }

    public static void main(String[] args) {
        Stack<Integer> numbers = new Stack<>();
        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        System.out.println("peek = " + numbers.peek() + ", size = " + numbers.size());
        System.out.println("pop  = " + numbers.pop());
        System.out.println("pop  = " + numbers.pop());
        System.out.println("isEmpty? " + numbers.isEmpty());

        Stack<String> words = new Stack<>();     // same class, different type
        words.push("generic");
        words.push("stack");
        System.out.println("words.pop() = " + words.pop());
        // words.push(5);   // COMPILE ERROR: Stack<String> only accepts Strings

        numbers.pop();
        try {
            numbers.pop();
        } catch (IllegalStateException e) {
            System.out.println("Pop on empty stack: " + e.getMessage());
        }
    }
}
