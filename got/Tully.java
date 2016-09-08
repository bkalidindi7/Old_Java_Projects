import java.util.Random;
import java.awt.Rectangle;

/**
 * This class represents a Tully object. House Tully is from the North, and is
 * represented by a trout.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Tully extends NorthHouse {
    private static final int TULLY_MAX_AGE = 100;

    /**
     * Constructor
     *
     * Creates instance of Tully and assigns it the trout image
     * @param x-Position, y-Position, the image boundaries
     */
    public Tully(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "trout.png", imageBorder);
    }

    /**
     * This method tests whether or not this Tully instance can reproduce with
     * the other house
     *
     * @param The House whose instance type is being checked for compatability
     * @return true if the other house is an instance of Stark
     */
    protected Boolean canReproduceWithHouse(House otherHouse) {
        if (this.age > 10 && otherHouse instanceof Stark) {
            return true;
        }
        return false;
    }

    /**
     * This method produces a house which is a byproduct of this instance
     * colliding with a Stark instance
     *
     * @param The House this instance collided with
     * @return an instance of Tully
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Tully baby = new Tully(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(50);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method tests whether or not an instance of Tully has surpassed
     * the max Tully age
     *
     * @return true if this instance exceeds the max Tully age
     */
    protected Boolean isOld() {
        if (this.age >= TULLY_MAX_AGE) {
            return true;
        }
        return false;
    }

    /**
     * This method tests whether or not the current instance of Tully can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if the other house is an instance of Lannister
     */
    protected Boolean canHarmHouse(House otherHouse) {
        if (otherHouse instanceof Lannister) {
            return true;
        }
        return false;
    }

    /**
     * if the current instance can harm the other house then it has a 20%
     * chance of decreasing the other House instance's health by 5
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (odds < 2) {
                otherHouse.health -= 5;
            }
        }
    }
}