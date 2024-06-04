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



    public static void main(String args[]) {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        Picture copy = testClearLow(beach);
        copy.explore();
    } 
}