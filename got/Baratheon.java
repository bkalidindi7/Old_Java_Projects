import java.util.Random;
import java.awt.Rectangle;

/**
 * This class represents a Baratheon object. House Baratheon is from the South,
 * and is represented by a stag.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Baratheon extends SouthHouse {
    private static final int BARATHEON_MAX_AGE = 74;

    /**
     * Constructor
     *
     * Creates instance of Baratheon and assigns it the stag image
     * @param x-Position, y-Position, the image boundaries
     */
    public Baratheon(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "stag.png", imageBorder);
    }

    /**
     * This method tests whether or not this Baratheon instance can
     * reproduce with the other house
     *
     * @param The House whose type is being checked for compatability
     * @return true if the other house is an instance of Lannister
     */
    protected Boolean canReproduceWithHouse(House otherHouse) {
        if (this.age > 10 && otherHouse instanceof Lannister) {
            return true;
        }
        return false;
    }

    /**
     * This method produces a house which is a byproduct of this instance
     * colliding with a Lannister instance
     *
     * @param The House this instance collided with
     * @return an instance of Baratheon
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Baratheon baby = new Baratheon(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(77);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method tests whether or not an instance of Baratheon has surpassed
     * the max Baratheon age
     *
     * @return true if this instance exceeds the max Baratheon age
     */
    protected Boolean isOld() {
        if (this.age >= BARATHEON_MAX_AGE) {
            return true;
        }
        return false;
    }

    /**
     * This method tests whether or not the current instance of Baratheon can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if the other house is an instance of Targaryan
     */
    protected Boolean canHarmHouse(House otherHouse) {
        if (otherHouse instanceof Targaryan) {
            return true;
        }
        return false;
    }

    /**
     * if the current instance can harm the other house then it has a 40%
     * chance of decreasing the other House instance's health by 4
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (odds < 2) {
                otherHouse.health -= 4;
            }
        }
    }
}