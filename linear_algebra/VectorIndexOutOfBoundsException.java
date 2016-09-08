/**
 * Exception thrown when attempting to reach an index not within the Vector
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class VectorIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
     * Creates an exception that prints a generic reply for this error
     */
    public VectorIndexOutOfBoundsException() {
        this("Sizes don't match up.");
    }

    /**
     * Creates an exception that prints a user-created reply for this error
     */
    public VectorIndexOutOfBoundsException(String s) {
        super(s);
    }
}