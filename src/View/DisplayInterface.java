package View;

import java.awt.image.BufferedImage;

interface DisplayInterface {
    void paintImageAtVector(BufferedImage image, int x, int y);

    void iRepaint();
}
