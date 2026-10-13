/** Lab 5, Exercise 4 (Intermediate): methods are polymorphic, fields are not. */
public class Lab05Ex4 {

    static class Parent {
        String label = "Parent field";
        String who() { return "Parent method"; }
    }

    static class Child extends Parent {
        String label = "Child field";                 // HIDES the parent's field
        @Override String who() { return "Child method"; }   // OVERRIDES the parent's method
    }

    public static void main(String[] args) {
        Parent ref = new Child();     // declared type Parent, actual object Child

        System.out.println("ref.who()   -> " + ref.who());     // decided by the OBJECT at run time
        System.out.println("ref.label   -> " + ref.label);     // decided by the DECLARED type

        Child real = (Child) ref;
        System.out.println("real.label  -> " + real.label);    // now declared type is Child
    }
}
