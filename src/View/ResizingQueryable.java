package View;

import java.awt.*;
import java.awt.image.BufferedImage;

class ResizingQueryable {
    static BufferedImage resizeImage(BufferedImage img, double scale) {
        int width = (int)Math.round(img.getWidth()*scale);
        int height = (int)Math.round(img.getHeight()*scale);
        Image temporaryImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(temporaryImage, 0, 0, null);
        g2d.dispose();

        return (outputImage);
    }

    static int resizeInt(int input, double scale){
        return((int)Math.round(input*scale));
    }
}