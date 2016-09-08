/**
 * Contains several methods used to perform Matrix and Vector functions
 *
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class LinearAlgebra {

    /**
     * Performs a matrix vector multiplication
     * @param Matrix and vector being multiplied
     * @return Vector product of the multiplication
     * @throws IllegalOperandException if Matrix width does not match Vector
     *         length
     */
    public static Vector matrixVectorMultiply(Matrix m, Vector v)
        throws IllegalOperandException {
        int l = v.getLength();
        double[] prod = new double[l];
        int h = m.getHeight();
        int w = m.getWidth();
        if (l != w) {
            throw new IllegalOperandException("Sorry Something went wrong."
                                              + "\nCannot multiply a matrix "
                                              + "of width " + w + " with a "
                                              + "vector of length " + l + ".");
        }
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                prod[r] += m.get(r, c) * v.get(c);
            }
        }
        Vector vecProd = new Vector(prod);
        return vecProd;
    }

    /**
     * Adds two matrices together
     * @param 2 Matrix instances being added
     * @return Matrix sum of the addition
     * @throws IllegalOperandException if Matrix width and height are not the
     *         same for both Matrices
     */
    public static Matrix matrixAdd(Matrix m1, Matrix m2)
        throws IllegalOperandException {
        int h1 = m1.getHeight();
        int w1 = m1.getWidth();
        int h2 = m2.getHeight();
        int w2 = m2.getWidth();
        if (h1 != h2) {
            throw new IllegalOperandException("Sorry Something went wrong."
                                              + "\nCannot add a matrix of "
                                              + "height " + h1 + " with a "
                                              + "matrix of height " + h2 + ".");
        }
        if (w1 != w2) {
            throw new IllegalOperandException("Sorry Something went wrong."
                                              + "\nCannot add a matrix of "
                                              + "width " + w1 + " with a "
                                              + "matrix of width " + w2 + ".");
        }
        double[][] sum = new double[h1][w1];
        for (int r = 0; r < h1; r++) {
            for (int c = 0; c < w1; c++) {
                sum[r][c] = m1.get(r, c) + m2.get(r, c);
            }
        }
        Matrix matSum = new Matrix(sum);
        return matSum;
    }

    /**
     * Finds the dot product of two vectors
     * @param 2 Vectors whose dot product is being solved
     * @return double of the dot product
     * @throws IllegalOperandException if Vectors' length do not match
     */
    public static double dotProduct(Vector v1, Vector v2)
        throws IllegalOperandException {
        if (v1.getLength() != v2.getLength()) {
            throw new IllegalOperandException("Sorry Something went wrong."
                                              + "\nCannot add a vector of "
                                              + "length " + v1.getLength()
                                              + " with a vector of length "
                                              + v2.getLength() + ".");
        }
        double v1num, v2num, prod = 0.0;
        for (int i = 0; i < v1.getLength(); i++) {
            v1num = v1.get(i);
            v2num = v2.get(i);
            prod += v1num * v2num;
        }
        return prod;
    }

    /**
     * Adds 2 vectors together
     * @param Vector and Vector being added
     * @return Vector sum of the addition
     * @throws IllegalOperandException if Vectors' length do not match
     */
    public static Vector vectorAdd(Vector v1, Vector v2)
        throws IllegalOperandException {
        if (v1.getLength() != v2.getLength()) {
            throw new IllegalOperandException("Sorry Something went wrong."
                                              + "\nCannot add a vector of "
                                              + "length " + v1.getLength()
                                              + " with a vector of length "
                                              + v2.getLength() + ".");
        }
        double[] sum = new double[v1.getLength()];
        double v1num, v2num;
        for (int i = 0; i < v1.getLength(); i++) {
            v1num = v1.get(i);
            v2num = v2.get(i);
            sum[i] = v1num + v2num;
        }
        Vector vecSum = new Vector(sum);
        return vecSum;
    }
}