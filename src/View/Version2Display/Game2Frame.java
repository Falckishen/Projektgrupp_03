package View.Version2Display;

import View.DisplayInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game2Frame extends JFrame implements DisplayInterface {
    public Game2Frame(int width, int height, JPanel mainFrame){
        setSize(new Dimension(width,height));
        setResizable(false);
        setTitle("(╯°□°）╯︵ ┻━┻");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        mainFrame.setSize(0,0);
        mainFrame.setFocusable(true);
        mainFrame.requestFocusInWindow();
        this.add(mainFrame);
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

    @Override
    public int getWindowWidth(){
        return(getWidth());
    }

    @Override
    public int getWindowHeight(){
        return(getHeight());
    }
}
