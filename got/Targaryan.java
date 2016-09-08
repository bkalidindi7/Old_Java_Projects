import java.util.Random;
import java.awt.Rectangle;
/**
 * This class represents a Targaryan object. House Targaryan represented
 * by a dragon.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public class Targaryan extends House {

    /**
     * Constructor
     *
     * Creates instance of Targaryan and assigns it the dragon image
     * @param x-Position, y-Position, the image boundaries
     */
    public Targaryan(int xPos, int yPos, Rectangle imageBorder) {
        super(xPos, yPos, "dragon.png", imageBorder);
    }

    /**
     * Moves the house in a random yet effective manner. Each move increases
     * the age of the house and decreases its health.
     */
    protected void move() {
        Random rand = new Random();
        int changey = rand.nextInt(20) - 10;
        int changex = rand.nextInt(20) - 10;
        this.xPos += changex;
        this.yPos += changey;
        if (this.xPos < 0) {
            this.xPos = 0;
        }
        if (this.xPos > 600) {
            this.xPos = 600;
        }
        if (this.yPos < 0) {
            this.yPos = 0;
        }
        if (this.yPos > 600) {
            this.yPos = 600;
        }
        this.health--;
        this.age += 1;
    }

    /**
     * This method tests whether or not this Targaryan instance can
     * reproduce with the other house
     *
     * @param The House whose type is being checked for compatability
     * @return true if the other house is an instance of Targaryan
     */
    protected Boolean canReproduceWithHouse(House otherHouse) {
        if (this.age > 10 && otherHouse instanceof Targaryan) {
            return true;
        }
        return false;
    }

    /**
     * This method produces a house which is a byproduct of this instance
     * colliding with a Targaryan instance
     *
     * @param The House this instance collided with
     * @return an instance of Targaryan
     */
    protected House reproduceWithHouse(House otherHouse) {
        if (!canReproduceWithHouse(otherHouse)) {
            return null;
        }
        Targaryan baby = new Targaryan(this.xPos, this.yPos, this.imageBorder);
        Random rand = new Random();
        int chance = rand.nextInt(80);
        if (chance < 1) {
            return baby;
        }
        return null;
    }

    /**
     * This method assures a Targaryan instance will not die of old age
     *
     * @return false because Targaryan does not have a max age
     */
    protected Boolean isOld() {
        return false;
    }

    /**
     * This method tests whether or not the current instance of Targaryan can
     * harm an instance of the other house through mortal combat
     *
     * @param The House this instance collided with
     * @return true if the other house is an instance of Stark, Tully,
     * Lannister, or Sixer
     */
    protected Boolean canHarmHouse(House otherHouse) {
        if (otherHouse instanceof Stark || otherHouse instanceof Tully
            || otherHouse instanceof Lannister || otherHouse instanceof Sixer) {
            return true;
        }
        return false;
    }

    /**
     * if the current instance can harm the other house then it has a 90%
     * chance of decreasing the other House instance's health by 1
     *
     * @param The House instance this one is attempting to hurt
     */
    protected void harmHouse(House otherHouse) {
        if (canHarmHouse(otherHouse)) {
            Random rand = new Random();
            int odds = rand.nextInt(10);
            if (odds < 9) {
                otherHouse.health -= 1;
            }
        }
    }
}