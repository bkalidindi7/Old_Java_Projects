
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class is an abstract parent class for other Houses of Westeros.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public abstract class House {

    protected ImageIcon image;
    protected String imageFilename;
    protected int xPos, yPos;
    protected int health;
    protected int age;
    protected Rectangle imageBorder;

    /**
     * Constructor
     *
     * Creates instance of a House, sets the health and age of House
     * @param x-Position, y-Position, the image filename, the image boundaries
     */
    public House(int xPos, int yPos, String imageFilename,
                 Rectangle imageBorder) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.imageFilename = imageFilename;
        image = new ImageIcon(imageFilename);
        this.imageBorder = imageBorder;
        health = 50;
        age = 0;
    }

    /**
     * Should draw the House at its location.
     */
    protected void draw(Graphics g) {
        image.paintIcon(null, g, xPos, yPos);
    }

    /**
     * Moves the house in a random yet effective manner. Each move increases
     * the age of the house and decreases its health.
     */
    protected abstract void move();

    /**
     * This method tests whether or not this current instance
     * of a House is colliding with another given instance of a House
     * depending on its location and dimensions of its image
     *
     * @param the House it is possibly colliding with
     * @return true if the images intersect
     */
    protected Boolean collidesWithHouse(House otherHouse) {
        Rectangle border1 = new Rectangle(xPos, yPos,
                                          image.getIconWidth(),
                                          image.getIconHeight());
        Rectangle border2 = new Rectangle(otherHouse.xPos, otherHouse.yPos,
                                          otherHouse.image.getIconWidth(),
                                          otherHouse.image.getIconHeight());
        return border1.intersects(border2);
    }

    /**
     * This method tests whether or not the two instances can reproduce
     *
     * @param The House whose instance type is being checked for compatability
     * @return true if the two houses are compatable
     */
    protected abstract Boolean canReproduceWithHouse(House otherHouse);

    /**
     * This method produces a house which is a byproduct of two other houses
     * colliding
     *
     * @param The House this instance collided with
     * @return a House of the same class as this one
     */
    protected abstract House reproduceWithHouse(House otherHouse);

    /**
     * This method tests whether or not an instance of a House has surpassed
     * some determined maximum age
     *
     * @return true if this instance exceeds the maximum age set
     */
    protected abstract Boolean isOld();

    /**
     * This method tests whether or not the current instance of a House can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if this instance is allowed to harm the other instance
     */
    protected abstract Boolean canHarmHouse(House otherHouse);

    /**
     * if the current house instance can harm the other house then it decreases
     * the other House instance's health by some amount
     *
     * @param The House this one is attempting to hurt
     */
    protected abstract void harmHouse(House otherHouse);

    /**
     * If this house exceeds the maximum age, then its health is set to 0
     */
    protected void die() {
        if (this.isOld()) {
            health = 0;
        }

    }

    /**
     * This method tests whether or not the instance is dead (ie health = 0)
     * @return true if health is less than or equal to 0
     */
    protected Boolean isDead() {
        if (health <= 0) {
            return true;
        }

        return false;
    }
}





