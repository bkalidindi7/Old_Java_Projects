/**
 * Immutable abstraction for Vector
 *
 * @author Michael Maurer
 * @version 1.2
 */
public class Vector {

    private final double[] vector;
    private final int length;

    /**
     * Initialize instance variables
     * @param vector array representation of vector
     */
    public Vector(double[] vector) {
        this.vector = vector;
        length = vector.length;
    }

    /**
     * Gets value located at specified index
     * @param i index in vector
     * @return double located at index 'i' in vector
     */
    public double get(int i) {
        double val = 0.0;
        try {
            val = vector[i];
        } catch (VectorIndexOutOfBoundsException v) {
            System.out.println("Index " + i + " is not valid in a Vector of "
                               + length + " length");
        }
        return val;
    }

    /**
     * Get's the length of the Vector.
     * @return number of components in vector
     */
    public int getLength() {
        return length;
    }

    /**
     * String representation of vector with components
     * separated by tabs
     * @return String representation of vector
     */
    public String toString() {
        String v = "";
        for (int i = 0; i < length; i++) {
            v += vector[i] + "\t";
        }
        return v;
    }
}