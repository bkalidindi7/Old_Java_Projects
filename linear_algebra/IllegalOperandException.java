/**
 * Exception thrown when the parameters for an operation do not match up
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class IllegalOperandException extends Exception {

    /**
     * Creates an exception that prints a generic reply for this error
     */
    public IllegalOperandException() {
        this("Sizes don't match up.");
    }

    /**
     * Creates an exception that prints a user-created reply for this error
     */
    public IllegalOperandException(String message) {
        super(message);
    }
}