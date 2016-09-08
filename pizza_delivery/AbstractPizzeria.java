import java.util.Set;
import java.util.Queue;
/**
 * A representation of an abstract pizzeria
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public abstract class AbstractPizzeria implements Pizzeria {

    protected Set<Order> menu;
    protected Queue<Customer> customers;
    protected int orderNum, orderAtt, orderProcessed, revenue;
    protected String name;

    /**
     * Creates an abstract pizzeria
     *
     * @param Queue of customers who ordered a pizza, Set of orders available
     */
    public AbstractPizzeria(Queue customers, Set menu) {
        this.menu = menu;
        this.customers = customers;
    }

    /**
     * Adds customer to the Queue and adds one to the number of orders
     *
     * @param customer placing the order
     */
    public void placeOrder(Customer customer) {
        customers.add(customer);
        orderNum++;
    }

    /**
     * Finds the cheapest Order in the menu
     *
     * @return smallest priced order
     */
    public Order getCheapestMenuItem() {
        int price = 100;
        Order min = null;
        for (Order o : menu) {
            if (o.getPrice() < price) {
                price = o.getPrice();
                min = o;
            }
        }
        return min;
    }

    /**
     * Finds the priciest Order in the menu
     *
     * @return largest priced order
     */
    public Order getMostExpensiveMenuItem() {
        int price = 0;
        Order max = null;
        for (Order o : menu) {
            if (o.getPrice() > price) {
                price = o.getPrice();
                max = o;
            }
        }
        return max;
    }

    /**
     * Status message of a Pizzeria's performance
     *
     * @return message String
     */
    public String status() {
        return "We delivered " + Math.round(100.0 * orderProcessed / orderNum)
            + "% of our orders!"
            + " We delivered " + Math.round(100.0 * orderProcessed / orderAtt)
            + "% of our attempted orders and made $" + revenue + ".00";
    }

    /**
     * Pizzeria name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Attempts to deliver an order to a customer.
     * If the customer placed an order that's not on the menu,
     * we don't deliver. If the customer placed an order that is
     * on the menu but he doesn't have enough money to pay,
     * we don't deliver. Otherwise, we deliver to customer
     * and collect our money.
     */
    public void processOrder() {
        Customer c = customers.remove();
        orderAtt++;
        if (menu.contains(c.getOrder()) && c.enoughMoney()) {
            revenue += c.getOrderPrice();
            orderProcessed++;
        }
    }
}