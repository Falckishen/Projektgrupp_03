package View.Version2Display;

import Model.Game;
import java.awt.image.BufferedImage;

interface DisplayInterface {

    void paintImageAtVector(BufferedImage image, int x, int y);

    void iRepaint();

    int getWindowWidth();

    int getWindowHeight();

    void implementKeyListener(Game game);
}