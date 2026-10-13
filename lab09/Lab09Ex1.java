/** Lab 9, Exercise 1 (Basic): enum Level with a switch printing advice. */
public class Lab09Ex1 {

    enum Level { LOW, MEDIUM, HIGH }

    static void advise(Level level) {
        switch (level) {
            case LOW -> System.out.println("LOW: relax, nothing to do yet");
            case MEDIUM -> System.out.println("MEDIUM: keep an eye on it");
            case HIGH -> System.out.println("HIGH: act immediately");
        }
    }

    public static void main(String[] args) {
        for (Level l : Level.values()) {          // values() returns all constants in order
            advise(l);
        }
        System.out.println("Level.valueOf(\"HIGH\") == Level.HIGH ? " + (Level.valueOf("HIGH") == Level.HIGH));
        System.out.println("MEDIUM ordinal = " + Level.MEDIUM.ordinal());
    }
}
