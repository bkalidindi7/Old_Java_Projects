import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * Creates Eraser object. Eraser sets whatever is on it's path's color to white
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class Eraser implements Tool {
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
        g.setLineWidth(5);
    }

    /**
     * Method called when the mouse is being dragged, erasing whatever is on
     * its path.
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        g.setStroke(Color.WHITE);
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
     * The name of this Eraser.
     *
     * @return "Eraser"
     */
    public String getName() {
        return "Eraser";
    }
}