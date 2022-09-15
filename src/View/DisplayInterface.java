package View;

import Model.Game;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public interface DisplayInterface {
    void paintImageAtVector(BufferedImage image, int x, int y);

    void iRepaint();

    int getWindowWidth();

    int getWindowHeight();

    void implementKeyListener(Game game);
}
