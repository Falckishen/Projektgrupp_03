package View.Version1Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

class Game1Panel extends JPanel {

    BufferedImage screenImage;

    Game1Panel(int width, int height){
        setPreferredSize(new Dimension(width, height));
        this.screenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    void paintImageAtVector(BufferedImage image, int x, int y){
        Graphics g = screenImage.getGraphics();
        g.drawImage(image, x, y, null);
        g.dispose();
    }

    BufferedImage getImage(){
        return(screenImage);
    }

    private void render(Graphics g){
        g.drawImage(screenImage, 0, 0, null);
        g.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        render(g);
        super.paintComponent(g);
    }
}
