import java.util.Set;
import java.util.PriorityQueue;

/**
 * A cunning pizza place. Delivers orders based on their price.
 * Expensive orders get delivered first.
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class MarySuePizza extends AbstractPizzeria {

    /**
     * Creates an Mary Sue Pizzeria, with Queue sorted by order price from
     * highest to lowest
     *
     * @param Set of orders available
     */
    public MarySuePizza(Set menu) {
        super(new PriorityQueue<Customer>(), menu);
        name = "Mary Sue Pizza";
    }
}