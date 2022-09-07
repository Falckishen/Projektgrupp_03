package View.Version1Display;

import View.DisplayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Game1Frame extends JFrame implements DisplayInterface {

    Game1Panel subPanel;

    Game1Frame(int width, int height){
        this.subPanel = new Game1Panel(width, height);
        add(subPanel);
        pack();

        setPreferredSize(new Dimension(1000,800));
        setTitle("Yup were about done.");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paintImageAtVector(BufferedImage image, int x, int y){
        subPanel.paintImageAtVector(image, x, y);
    }

    @Override
    public void iRepaint() {
        repaint();
    }

    @Override
    public int getWindowWidth() {
        return 0;
    }

    @Override
    public int getWindowHeight() {
        return 0;
    }
}
