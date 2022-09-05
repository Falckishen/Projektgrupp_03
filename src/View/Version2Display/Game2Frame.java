package View.Version2Display;

import View.DisplayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game2Frame extends JFrame implements DisplayInterface {

    public Game2Frame(int width, int height){
        setSize(new Dimension(1000,800));
        setResizable(false);
        setTitle("(╯°□°）╯︵ ┻━┻");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);

        createBufferStrategy(2);
    }

    @Override
    public void paintImageAtVector(BufferedImage image, int x, int y) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        g.drawImage(image, x, y, null);
    }

    @Override
    public void iRepaint() {
        getBufferStrategy().show();
    }
}
