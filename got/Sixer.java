import java.util.Random;
import java.awt.Rectangle;

/**
 * This class represents a Sixer object. House Sixer is from the North,
 * and is represented by the 76ers logo.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Sixer extends NorthHouse {
    private static final int SIXER_MAX_AGE = 41;

    /**
     * Constructor
     *
     * Creates instance of Sixer and assigns it the image of the 76ers logo
     * @param x-Position, y-Position, the image boundaries
     */
    public Sixer(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "sixer.jpeg", imageBorder);
    }

    /**
     * This method tests whether or not this Sixer instance can
     * reproduce with the other house
     *
     * @param The House whose type is being checked for compatability
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
     * @return an instance of Sixer
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Sixer baby = new Sixer(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(18);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method tests whether or not an instance of Sixer has surpassed
     * the max Sixer age
     *
     * @return true if this instance exceeds the max Sixer age
     */
    protected Boolean isOld() {
        if (this.age >= SIXER_MAX_AGE) {
            return true;
        }
        return false;
    }

    /**
     * This method tests whether or not the current instance of Sixer can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if the other house is an instance of Lannister, Targaryan,
     * or Stark
     */
    protected Boolean canHarmHouse(House otherHouse) {
        if (otherHouse instanceof Lannister
            || otherHouse instanceof Targaryan
            || otherHouse instanceof Stark) {
            return true;
        }
        return false;
    }

    /**
     * if the current instance can harm the other house then, if older than 10,
     * it has a 40% chance of decreasing the other instance's health by 1, and
     * if older, a 70% chance of decreasing the other instances health by an
     * amount that increases with this instances age
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (this.age <= 10) {
                if (odds < 4) {
                    otherHouse.health -= 1;
                }
            } else {
                if (odds < 7) {
                    otherHouse.health -= (age - 10);
                }
            }
        }
    }
}