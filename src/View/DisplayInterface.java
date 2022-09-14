package View;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public interface DisplayInterface {
    void paintImageAtVector(BufferedImage image, int x, int y);

    void iRepaint();

    int getWindowWidth();

    int getWindowHeight();

    void implementKeyListener(KeyListener keyListener);

    void implementMouseListener(MouseListener mouseListener);
}
