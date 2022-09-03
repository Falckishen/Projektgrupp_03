package View;

import javax.swing.*;
import java.awt.image.BufferedImage;

class GameFrame extends JFrame implements DisplayInterface{
    GamePanel subPanel;
    GameFrame(int width, int height){
        this.subPanel = new GamePanel(width, height);
        add(subPanel);
        pack();
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
}
