import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * This class creates a Pencil object which draws simple lines on the canvas
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class Pencil implements Tool {

    private double x, y;

    /**
     * Method that gives initial coordinates of mouse press
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        x = e.getX();
        y = e.getY();
    }

    /**
     * Method called when the mouse is being dragged, creating lines.
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.setLineWidth(5);
        g.strokeLine(x, y, e.getX(), e.getY());
        x = e.getX();
        y = e.getY();
    }

    /**
     * Method that is called when the mouse is released.
     *
     * @param e The mouseevent that fired this onRelease.
     * @param g The current graphics context.
     */
    public void onRelease(MouseEvent e, GraphicsContext g) {
    }

    /**
     * The name of this Pencil.
     *
     * @return "Pencil"
     */
    public String getName() {
        return "Pencil";
    }
}