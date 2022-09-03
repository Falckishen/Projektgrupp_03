package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class GamePanel extends JPanel {

    BufferedImage screenImage;

    GamePanel(int width, int height){
        setPreferredSize(new Dimension(width, height));
        this.screenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    void paintImageAtVector(BufferedImage image, int x, int y){
        screenImage.getGraphics().drawImage(image, x, y, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(screenImage, 0, 0, null);
    }
}
