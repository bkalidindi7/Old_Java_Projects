import java.util.Set;
import java.util.LinkedList;

/**
 * A normal pizza place. Delivers pizzas in the same order they were ordered
 * in, regardless of price or the customer’s ability to pay.
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class CascadePizza extends AbstractPizzeria {

    /**
     * Creates an Cascade Pizzeria, with Queue sorted by first-come basis
     *
     * @param Set of orders available
     */
    public CascadePizza(Set menu) {
        super(new LinkedList<Customer>(), menu);
        name = "Cascade Pizza";
    }
}