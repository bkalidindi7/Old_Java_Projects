import java.util.HashSet;

/**
 * A representation of a set of Ingredients a Customer may order
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class Order extends HashSet<Ingredient> {
    /**
     * Gives the price of the order
     *
     * @return the order's price
     */
    public int getPrice() {
        int sumMoney = 0;
        for (Ingredient i : this) {
            sumMoney += i.getPrice();
        }
        return sumMoney;
    }
}