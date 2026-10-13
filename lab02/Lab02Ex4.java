/** Lab 2, Exercise 4 (Intermediate): two static blocks and two instance blocks. */
public class Lab02Ex4 {

    static class Demo {
        static { System.out.println("static block 1"); }
        { System.out.println("instance block 1"); }

        Demo() { System.out.println("constructor"); }

        static { System.out.println("static block 2"); }
        { System.out.println("instance block 2"); }
    }

    public static void main(String[] args) {
        System.out.println("main starts (Demo not loaded yet)");
        System.out.println("--- first object ---");
        new Demo();
        System.out.println("--- second object ---");
        new Demo();
    }
}
