import java.util.EnumMap;
import java.util.Map;

/** Lab 9, Exercise 5 (Challenge): enum Coin and the fewest coins for an amount. */
public class Lab09Ex5 {

    enum Coin {
        PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

        private final int cents;
        Coin(int cents) { this.cents = cents; }
        int getCents() { return cents; }

        // Greedy: always take the largest coin that fits. This gives the minimum for THIS coin set.
        static Map<Coin, Integer> fewestCoins(int amount) {
            Map<Coin, Integer> result = new EnumMap<>(Coin.class);
            Coin[] all = Coin.values();
            for (int i = all.length - 1; i >= 0; i--) {      // QUARTER, DIME, NICKEL, PENNY
                int n = amount / all[i].cents;
                if (n > 0) {
                    result.put(all[i], n);
                    amount -= n * all[i].cents;
                }
            }
            return result;
        }
    }

    static void show(int amount) {
        Map<Coin, Integer> coins = Coin.fewestCoins(amount);
        int total = coins.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println(amount + " cents -> " + coins + "  (" + total + (total == 1 ? " coin)" : " coins)"));
    }

    public static void main(String[] args) {
        show(68);
        show(99);
        show(25);
        show(4);
    }
}
