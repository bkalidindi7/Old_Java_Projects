/**
 * Immutable abstraction of Matrix.
 *
 * @author Michael Maurer
 * @version 1.2
 */
public final class Matrix {

    private final double[][] matrix;
    private final int height;
    private final int width;

    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
    }

    /**
     * Gets value located at specified row and column
     * @param i row
     * @param j column
     * @return double located at row i and column j in matrix
     */
    public double get(int i, int j) {
        double num = 0.0;
        try {
            num = matrix[i][j];
        } catch (MatrixIndexOutOfBoundsException m) {
            System.out.println("Index " + i + "," + j + " is not valid"
                               + "within a " + height + "x" + width
                               + " matrix");
        }
        return num;
    }

    /**
     * Get's the height of the matrix.
     * @return number of rows in matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get's the width of the matrix.
     * @return number of columns in matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets String representation of matrix.
     * Columns separated by tabs, rows by new lines.
     * @return String representation of matrix.
     */
    public String toString() {
        String mat = new String();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mat += matrix[row][col] + "\t";
                if (col == width - 1) {
                    mat += "\n";
                }
            }
        }
        return mat;
    }
}