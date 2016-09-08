import java.util.Random;
import java.awt.Rectangle;

/**
 * This class is an abstract parent class for other SouthHouses of Westeros.
 *
 * @author Kalidindi, Bharath
 * @version 1.0
 */
public abstract class SouthHouse extends House {

    /**
     * Constructor
     *
     * Creates instance of a SouthHouse
     * @param x-Position, y-Position, the image filename, the image boundaries
     */
    public SouthHouse(int xPos, int yPos, String imageFilename,
                 Rectangle imageBorder) {
        super(xPos, yPos, imageFilename, imageBorder);
    }

    /**
     * Moves the house in a random yet effective manner. Each move increases
     * the age of the house and decreases its health. If this instance is in
     * the southern hempisphere of Westeros, its speed increases
     */
    protected void move() {
        Random rand = new Random();
        if (this.yPos >= 300) {
            int changey = rand.nextInt(40) - 20;
            int changex = rand.nextInt(40) - 20;
            this.xPos += changex;
            this.yPos += changey;
        } else {
            int changey = rand.nextInt(20) - 10;
            int changex = rand.nextInt(20) - 10;
            this.xPos += changex;
            this.yPos += changey;
        }
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
        this.age += 1;
        this.health--;
    }
}