import java.util.Random;
import java.awt.Rectangle;

/**
 * This class represents a Lannister object. House Lannister is from the South,
 * and is represented by a lion.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Lannister extends SouthHouse {
    private static final int LANNISTER_MAX_AGE = 40;

    /**
     * Constructor
     *
     * Creates instance of Lannister and assigns it the lion image
     * @param x-Position, y-Position, the image boundaries
     */
    public Lannister(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "lion.png", imageBorder);
    }

    /**
     * This method tests whether or not this Lannister instance can
     * reproduce with the other house
     *
     * @param The House whose type is being checked for compatability
     * @return true if the other house is an instance of Baratheon or Lannister
     */
    protected Boolean canReproduceWithHouse(House otherHouse) {
        if (this.age > 10 && otherHouse instanceof Baratheon
            || otherHouse instanceof Lannister) {
            return true;
        }
        return false;
    }

    /**
     * This method produces a house which is a byproduct of this instance
     * colliding with a Baratheon or Lannister instance
     *
     * @param The House this instance collided with
     * @return an instance of Lannister
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Lannister baby = new Lannister(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(47);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method tests whether or not an instance of Lannister has surpassed
     * the max Lannister age
     *
     * @return true if this instance exceeds the max Lannister age
     */
    protected Boolean isOld() {
        if (this.age >= LANNISTER_MAX_AGE) {
            return true;
        }
        return false;
    }

    /**
     * This method tests whether or not the current instance of Lannister can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if the other house is an instance of Stark or Tully
     */
    protected Boolean canHarmHouse(House otherHouse) {
        if (otherHouse instanceof Stark || otherHouse instanceof Tully) {
            return true;
        }
        return false;
    }

    /**
     * if the current instance can harm the other house then it has a 80%
     * chance of decreasing a Tully instance's health by 2 and a 60% chance of
     * decreasing a Stark instance's health by 2
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (otherHouse instanceof Tully) {
                if (odds < 8) {
                    otherHouse.health -= 2;
                }
            }
            if (otherHouse instanceof Stark) {
                if (odds < 6) {
                    otherHouse.health -= 2;
                }
            }
        }
    }
}