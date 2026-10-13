import java.util.ArrayList;

/** Lab 4, Exercise 5 (Challenge): Stack extends ArrayList (bad) versus Stack HAS-A ArrayList (good). */
public class Lab04Ex5 {

    // BAD DESIGN: inheritance exposes every ArrayList method
    static class BadStack<T> extends ArrayList<T> {
        void push(T item) { add(item); }
        T pop() { return remove(size() - 1); }
    }

    // GOOD DESIGN: composition, only stack operations are exposed
    static class GoodStack<T> {
        private final ArrayList<T> items = new ArrayList<>();   // HAS-A

        void push(T item) { items.add(item); }
        T pop() {
            if (items.isEmpty()) throw new IllegalStateException("Stack is empty");
            return items.remove(items.size() - 1);
        }
        boolean isEmpty() { return items.isEmpty(); }
        int size() { return items.size(); }
    }

    public static void main(String[] args) {
        BadStack<String> bad = new BadStack<>();
        bad.push("A");
        bad.push("B");
        bad.push("C");
        bad.add(0, "INTRUDER");     // legal because ArrayList.add(int, E) is inherited
        System.out.println("BadStack contents: " + bad);
        System.out.println("Direct index access bad.get(1) = " + bad.get(1) + "  (a stack must not allow this)");
        bad.remove(0);              // removes from the BOTTOM: breaks LIFO
        System.out.println("After bad.remove(0): " + bad);

        GoodStack<String> good = new GoodStack<>();
        good.push("A");
        good.push("B");
        good.push("C");
        System.out.println("GoodStack pops (LIFO): " + good.pop() + " " + good.pop() + " " + good.pop());
        // good.add(0, "X");   // COMPILE ERROR: GoodStack has no add(int, E)
    }
}
