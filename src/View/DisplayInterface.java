package View;

import java.awt.image.BufferedImage;

public interface DisplayInterface {
    void paintImageAtVector(BufferedImage image, int x, int y);

    void iRepaint();

    int getWindowWidth();

    int getWindowHeight();
}
