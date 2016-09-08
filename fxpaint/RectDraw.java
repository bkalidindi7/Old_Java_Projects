import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
/**
 * This class represents a RectDraw object, creating a rectangle
 * @author Bharath Kalidindi
 * @version 1.0
 */
public class RectDraw implements Tool {
    private double xpos;
    private double ypos;

    /**
     * Sets the initial position and initial color.
     *
     * @param e The mouseevent that fired this onPress.
     * @param g The current graphics context.
     */
    public void onPress(MouseEvent e, GraphicsContext g) {
        xpos = e.getX();
        ypos = e.getY();
        g.setFill(g.getStroke());
        g.fillRect(xpos, ypos, 0, 0);
    }

    /**
     * Resizes the rectangle outward as the mouse is dragged outward
     *
     * @param e The mouseevent that fired this onDrag.
     * @param g The current graphics context.
     */
    public void onDrag(MouseEvent e, GraphicsContext g) {
        if (xpos > e.getX()) {
            if (ypos > e.getY()) {
                g.fillRect(e.getX(), e.getY(), xpos - e.getX(),
                    ypos - e.getY());
            } else {
                g.fillRect(e.getX(), ypos, xpos - e.getX(), e.getY() - ypos);
            }
        } else {
            if (ypos < e.getY()) {
                g.fillRect(xpos, ypos, e.getX() - xpos, e.getY() - ypos);
            } else {
                g.fillRect(xpos, e.getY(), e.getX() - xpos, ypos - e.getY());
            }

        }
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
     * The name of this Rectangle.
     *
     * @return "Rectangle"
     */
    public String getName() {
        return "Rectangle";
    }
}