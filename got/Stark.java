import java.util.Random;
import java.awt.Rectangle;

/**
 * This class represents a Stark object. House Stark is from the North, and is
 * represented by a direwolf.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Stark extends NorthHouse {
    private static final int STARK_MAX_AGE = 90;

    /**
     * Constructor
     *
     * Creates instance of Stark and assigns it the direwolf image
     * @param x-Position, y-Position, the image boundaries
     */
    public Stark(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "direwolf.png", imageBorder);
    }

    /**
     * This method tests whether or not this Stark instance can reproduce with
     * the other house
     *
     * @param The House whose instance type is being checked for compatability
     * @return true if the other house is an instance of Tully
     */
    protected Boolean canReproduceWithHouse(House otherHouse) {
        if (this.age > 10 && otherHouse instanceof Tully) {
            return true;
        }
        return false;
    }

    /**
     * This method produces a house which is a byproduct of this instance
     * colliding with a Tully instance
     *
     * @param The House this instance collided with
     * @return an instance of Stark
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Stark baby = new Stark(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(45);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method tests whether or not an instance of Stark has surpassed
     * the max Stark age
     *
     * @return true if this instance exceeds the max Stark age
     */
    protected Boolean isOld() {
        if (this.age >= STARK_MAX_AGE) {
            return true;
        }
        return false;
    }

    /**
     * This method tests whether or not the current instance of Stark can
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
     * if the current instance can harm the other house then it has a 40%
     * chance of decreasing the other House instance's health by 2
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (odds < 4) {
                otherHouse.health -= 2;
            }
        }
    }
}