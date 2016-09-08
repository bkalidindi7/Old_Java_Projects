public class ImageProcessor {

    //private static ImageProcessor image;
    private static Pic picture;
    private static Pixel[][] pixels;

    public static void main(String[] args) {
        picture = new Pic(args[0]);
        ImageProcessor image = new ImageProcessor(picture);
        image.greyscale().show();
        image.invert().show();
        image.noRed().show();
        image.blackAndWhite().show();
        image.maximize().show();
        Pic sndPic = new Pic(args[1]);
        if (args.length == 2) {
            image.overlay(sndPic).show();
        }
    }

    public ImageProcessor(Pic picture) {
        this.picture = picture.deepCopy();
        this.pixels = this.picture.getPixels();
    }

    /**
     * Creates a copy of the original picture into greyscale
     *
     * @return the greyscale copy as a Pic
     */
    public Pic greyscale() {
        Pic greyCopy = picture.deepCopy();
        int red, blue, green, pixAvg;
        Pixel[][] greyPixels = greyCopy.getPixels();
        for (int row = 0; row < greyPixels.length; row++) {
            for (int col = 0; col < greyPixels[row].length; col++) {
                red = greyPixels[row][col].getRed();
                blue = greyPixels[row][col].getBlue();
                green = greyPixels[row][col].getGreen();
                pixAvg = (red + blue + green) / 3;
                greyPixels[row][col].setRed(pixAvg);
                greyPixels[row][col].setGreen(pixAvg);
                greyPixels[row][col].setBlue(pixAvg);
            }
        }
        return greyCopy;
    }

    /**
     * Creates a copy of the original picture with inverted color schemes
     *
     * @return the inverted copy as a Pic
     */
    public Pic invert() {
        Pic invertCopy = picture.deepCopy();
        Pixel[][] invertPixels = invertCopy.getPixels();
        int red, blue, green;
        int max = 255;
        for (int row = 0; row < invertPixels.length; row++) {
            for (int col = 0; col < invertPixels[row].length; col++) {
                red = invertPixels[row][col].getRed();
                blue = invertPixels[row][col].getBlue();
                green = invertPixels[row][col].getGreen();
                invertPixels[row][col].setRed(Math.abs(red - max));
                invertPixels[row][col].setGreen(Math.abs(green - max));
                invertPixels[row][col].setBlue(Math.abs(blue - max));
            }
        }
        return invertCopy;
    }

    /**
     * Creates a copy of the original picture without
     * the red component in each pixels
     *
     * @return the redless copy as a Pic
     */
    public Pic noRed() {
        Pic picNoRed = picture.deepCopy();
        Pixel[][] noRedpixels = picNoRed.getPixels();
        int red = 0;
        for (int row = 0; row < noRedpixels.length; row++) {
            for (int col = 0; col < noRedpixels[row].length; col++) {
                noRedpixels[row][col].setRed(0);
            }
        }
        return picNoRed;
    }

    /**
     * Creates a copy of the original picture into black and white
     *
     * @return the black and white copy as a Pic
     */
    public Pic blackAndWhite() {
        Pic blackWhitePic = picture.deepCopy();
        Pixel[][] blackWhitePix = blackWhitePic.getPixels();
        int red, blue, green, pixAvg;
        for (int row = 0; row < blackWhitePix.length; row++) {
            for (int col = 0; col < blackWhitePix[row].length; col++) {
                red = blackWhitePix[row][col].getRed();
                blue = blackWhitePix[row][col].getBlue();
                green = blackWhitePix[row][col].getGreen();
                pixAvg = (red + blue + green) / 3;
                if (pixAvg < 127) {
                    blackWhitePix[row][col].setRed(0);
                    blackWhitePix[row][col].setGreen(0);
                    blackWhitePix[row][col].setBlue(0);
                }
                if (pixAvg >= 127) {
                    blackWhitePix[row][col].setRed(255);
                    blackWhitePix[row][col].setGreen(255);
                    blackWhitePix[row][col].setBlue(255);
                }
            }
        }
        return blackWhitePic;
    }

    /**
     * Creates a copy of the original picture setting the color component,
     * for each pixel, of the highest value to 255, and the two lowest to 0
     *
     * @return the maximized copy as a Pic
     */
    public Pic maximize() {
        Pic maxPic = picture.deepCopy();
        Pixel[][] maxPixels = maxPic.getPixels();
        int red = 0;
        int blue = 0;
        int green = 0;
        int[] colors = {red, blue, green};
        int max = 0;
        for (int row = 0; row < maxPixels.length; row++) {
            for (int col = 0; col < maxPixels[row].length; col++) {
                red = maxPixels[row][col].getRed();
                blue = maxPixels[row][col].getBlue();
                green = maxPixels[row][col].getGreen();
                max = Math.max(red, Math.max(green, blue));
                if (red != max) {
                    maxPixels[row][col].setRed(0);
                }
                if (green != max) {
                    maxPixels[row][col].setGreen(0);
                }
                if (blue != max) {
                    maxPixels[row][col].setBlue(0);
                }
            }
        }
        return maxPic;
    }

    /**
     * Takes in another picture and overlays the two pictures
     * over each other, starting at the top left corner of both images
     *
     * @return the overlayed copy as a Pic
     */
    public Pic overlay(Pic other) {
        Pic picOrig = picture.deepCopy();
        Pixel[][] origPix = picOrig.getPixels();
        Pixel[][] otherPix = other.getPixels();
        int height = Math.min(picOrig.getHeight(), other.getHeight());
        int width = Math.min(picOrig.getWidth(), other.getWidth());
        int redAvg, blueAvg, greenAvg;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                redAvg = (origPix[row][col].getRed()
                         + (otherPix[row][col].getRed()) / 2);
                origPix[row][col].setRed(redAvg);
                blueAvg = (origPix[row][col].getBlue()
                          + (otherPix[row][col].getBlue()) / 2);
                origPix[row][col].setBlue(blueAvg);
                greenAvg = (origPix[row][col].getGreen()
                           + (otherPix[row][col].getGreen()) / 2);
                origPix[row][col].setGreen(greenAvg);
            }
        }
        return picOrig;
    }
}