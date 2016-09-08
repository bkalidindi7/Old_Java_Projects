import java.util.Set;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * A sophisticated (and probably criminal) pizza place. Delivers orders based
 * on both price and the customer’s ability to pay.
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class PizzaShack extends AbstractPizzeria {

    /**
     * Creates a Pizza Shack, with queue sorted by whether the customer can
     * pay, and the price of their order
     *
     * @param Set of orders available
     */
    public PizzaShack(Set menu) {
        super(new PriorityQueue<Customer>(
        Comparator.comparing(Customer::enoughMoney).reversed().thenComparing(
        Customer::compareTo)), menu);
        name = "Pizza Shack";
    }
}