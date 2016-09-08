/**
 * Exception thrown when attempting to reach an index not within the Matrix
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class MatrixIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
     * Creates an exception that prints a generic reply for this error
     */
    public MatrixIndexOutOfBoundsException() {
        this("Sizes don't match up.");
    }

    /**
     * Creates an exception that prints a user-created reply for this error
     */
    public MatrixIndexOutOfBoundsException(String s) {
        super(s);
    }
}