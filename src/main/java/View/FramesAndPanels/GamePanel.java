package View.FramesAndPanels;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * GamePanel is used when you want to manually render the screen which means when playing a round.
 * @author William Johansson
 */
public class GamePanel extends JPanel implements PanelInterface{

    private BufferStrategy bufferStrategy;

    public GamePanel(){}

    /**
     * Paints an image at a position, the position describes the center of the image.
     * @param image The image to paint
     * @param right How far to the right from the top left corner to paint.
     * @param down How far to the down from the top left corner to paint.
     */
    public void paintImage(BufferedImage image, int right, int down){
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, right-(image.getWidth()/2), down-(image.getHeight()/2), null);
    }


    /**
     * Paints an image at a position relative to the center of the screen, the position describes the center of the image.
     * @param image The image to paint
     * @param right How far to the right from the center to paint.
     * @param down How far to the down from the center to paint.
     */
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
