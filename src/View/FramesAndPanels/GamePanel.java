package View.FramesAndPanels;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class GamePanel extends JPanel implements PanelInterface{

    private BufferStrategy bufferStrategy;

    public GamePanel(){}

    public void paintImage(BufferedImage image, int right, int down){
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, right-(image.getWidth()/2), down-(image.getHeight()/2), null);
    }

    public void paintImageRelativeToCenter(BufferedImage image, int right, int down){
        paintImage(image, right+(getWidth()/2), down+(getHeight()/2));
    }


    @Override
    public void startPanel(JFrame parentFrame) {
        setSize(parentFrame.getWidth(), parentFrame.getHeight());
        parentFrame.createBufferStrategy(2);
        this.bufferStrategy = parentFrame.getBufferStrategy();
    }

    @Override
    public void updateDisplay() {
        bufferStrategy.show();
    }

    @Override
    public void destroyPanel() {
        bufferStrategy.dispose();
    }
}
