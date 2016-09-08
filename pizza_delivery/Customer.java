/**
 * A representation of a customer with a randomly
 * generated amount of money between $5 and $34 and an idea
 * of an Order that they want. Customers are also comparable
 * based on the price of their orders, where the Customer with
 * more money should come first.
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class Customer implements Comparable<Customer> {

    private int money;
    private Order custOrder;

    /**
     * Creates an instance of a customer with a randomly
     * generated amount of money between $5 and $34 and an idea
     * of an Order that they want
     *
     * @param the customer's order
     */
    public Customer(Order custOrder) {
        this.custOrder = custOrder;
        money = 5 + Driver.RANDOM.nextInt(30);
    }

    /**
     * Determines whether the customer has enough money to afford their order
     *
     * @return true if customer can afford order, false otherwise
     */
    public boolean enoughMoney() {
        if (money < custOrder.getPrice()) {
            return false;
        }
        return true;
    }

    /**
     * Gives the order of the customer
     *
     * @return the customer's order
     */
    public Order getOrder() {
        return custOrder;
    }

    /**
     * Gives the amount of money the customer possesses
     *
     * @return the customer's amount of money
     */
    public int getMoney() {
        return money;
    }

    /**
     * Gives the order price of the customer
     *
     * @return the customer's order price
     */
    public int getOrderPrice() {
        return custOrder.getPrice();
    }

    /**
     * Dtermines which customer's order is greater
     *
     * @return amount difference in price, indicating which is greater
     */
    public int compareTo(Customer other) {
        return other.getOrderPrice() - this.getOrderPrice();
    }
}