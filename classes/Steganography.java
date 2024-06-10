import java.awt.Color;
public class Steganography {

    public static void clearLow(Pixel p) {
        p.setColor(new Color(p.getRed()/4*4, p.getGreen()/4*4, p.getBlue()/4*4));
    }

    public static Picture testClearLow(Picture p) {
        Picture s = p; 
        for(int i = 0; i<s.getWidth(); i++) {
            for(int j = 0; j<s.getHeight(); j++) {
                clearLow(s.getPixel(i, j));
            }
        }
        return s;
    }

    public static void setLow(Pixel p, Color c) {
        int r = p.getRed()/4*4;
        int b = p.getBlue()/4*4;
        int g = p.getGreen()/4*4;
        r += c.getRed()/64;
        b += c.getBlue()/64;
        g += c.getGreen()/64;
        p.setColor(new Color(r, g, b));
    }

    public static Picture testSetLow(Picture p, Color c) {
        Picture s = p;
        for(int i = 0; i<s.getWidth(); i++) {
            for(int j = 0; j<s.getHeight(); j++) {
                setLow(s.getPixel(i,j), c);
            }
        }
        return s;
    }

    public static Picture revealPicture(Picture hidden){
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for(int r = 0; r<pixels.length; r++){
            for(int c = 0; c<pixels[0].length; c++){
                Color col = source[r][c].getColor();
                int red = col.getRed()%4*64;
                int green = col.getGreen()%4*64;
                int blue = col.getBlue()%4*64;
                pixels[r][c].setColor(new Color(red, green, blue));
                // copy.getPixel(c, r).setColor(new Color(red, green, blue));
            }
        }
        return copy;
    }

    public static boolean canHide(Picture source, Picture secret) {
        return((source.getHeight() == secret.getHeight()) && (source.getWidth() == secret.getWidth()));
    }

    public static Picture hidePicture(Picture source, Picture secret) {
        Pixel[][] S = secret.getPixels2D();
        Pixel[][] s = source.getPixels2D();
        for(int i = 0; i<source.getHeight(); i++) {
            for(int j = 0; j<source.getWidth(); j++) {
                int reds = s[i][j].getRed();
                int greens = s[i][j].getGreen();
                int blues = s[i][j].getBlue();

                int redS = S[i][j].getRed();
                int greenS = S[i][j].getGreen();
                int blueS = S[i][j].getBlue();

                Pixel P = s[i][j];
                P.setRed(reds/4*4+redS/64);
                P.setBlue(blues/4*4+blueS/64);
                P.setGreen(greens/4*4+greenS/64);
            }
        }
        return source;
    }

    public static Picture hidePicture(Picture source, Picture secret, int startCol, int startRow) {
        Pixel[][] S = secret.getPixels2D();
        Pixel[][] s = source.getPixels2D();
        int c = 0;
        for(int i = startRow; i<(startRow+secret.getHeight()); i++) {
            int r = 0;
            for(int j = startCol; j<(startCol+secret.getHeight()); j++) {
                int red = s[i][j].getRed();
                int green = s[i][j].getGreen();
                int blue = s[i][j].getBlue();


                int Sred = S[r][c].getRed();
                int Sgreen = S[r][c].getGreen();
                int Sblue = S[r][c].getBlue();


                Pixel currPixel = s[r][c];
                currPixel.setRed(red/4*4+Sred/64);
                currPixel.setBlue(blue/4*4+Sblue/64);
                currPixel.setGreen(green/4*4+Sgreen/64);
                r++;
            }
            c++;
        }
        return source;
    }

    public static void main(String args[]) {
        // Picture beach2 = new Picture("beach.jpg");
        // beach2.explore();
        // Picture copy2 = testSetLow(beach2, Color.PINK);
        // copy2.explore();
        // Picture copy3 = revealPicture(copy2);
        // copy3.explore();
        // Picture k2 = new Picture("kitten2.jpg");
        // Picture swan = new Picture("swan.jpg");
        // k2.explore();
        // swan.explore();
        // if(canHide(swan, k2) == true) {
        //     Picture copy4 = hidePicture(swan, k2);
        //     copy4.explore();
        //     Picture copy5 = revealPicture(copy4);
        //     copy5.explore();            
        // }
        Picture beach = new Picture("beach.jpg");
        Picture robot = new Picture("robot.jpg");
        Picture flower = new Picture("flower1.jpg");
        beach.explore();

        Picture hidden = hidePicture(beach, robot, 65, 208);
        Picture otherHidden = hidePicture(hidden, flower, 28, 110);
        otherHidden.explore();
        Picture unhidden = revealPicture(otherHidden);
        unhidden.explore();
    } 
}