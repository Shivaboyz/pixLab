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
        return((source.getHeight() == secret.getHeight()) && (source.getWidth() == secret.getWidth())) return true;
    }

    public static Picture hidePicture(Picture source, Picture secret) {
        Pixel[][] p = 
    }

    public static void main(String args[]) {
        Picture beach2 = new Picture("beach.jpg");
        beach2.explore();
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        Picture copy3 = revealPicture(copy2);
        copy3.explore();
    } 
}