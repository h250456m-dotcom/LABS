/** Lab 7, Exercise 5 (Challenge): return inside try with a print in finally. */
public class Lab07Ex5 {

    static int test1() {
        try {
            System.out.println("  test1: in try, about to return 1");
            return 1;
        } finally {
            System.out.println("  test1: finally runs BEFORE the method actually returns");
        }
    }

    static int test2() {
        int x = 10;
        try {
            return x;                   // the value 10 is saved here
        } finally {
            x = 99;                     // changing x now does NOT change the saved return value
            System.out.println("  test2: finally set x = " + x);
        }
    }

    @SuppressWarnings("finally")
    static int test3() {
        try {
            return 1;
        } finally {
            return 2;                   // a return in finally OVERRIDES the earlier return (bad practice)
        }
    }

    public static void main(String[] args) {
        System.out.println("test1 returned " + test1());
        System.out.println("test2 returned " + test2());
        System.out.println("test3 returned " + test3());
    }
}
