package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

class UFrameInterface {

    static JFrame createFrame(int width, int height){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(width,height));
        frame.setResizable(false);
        frame.setTitle("(╯°□°）╯︵ ┻━┻");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.createBufferStrategy(2);

        frame.setCursor(frame.getToolkit().createCustomCursor(ImageContainer.getImageFromTypeVariant(UImageTypeEnum.MISC, 1), new Point(16, 16), null));

        return(frame);
    }

    private static void paintImage(JFrame frame, BufferedImage image, int right, int down){
        Graphics g = frame.getBufferStrategy().getDrawGraphics();
        g.drawImage(image, right-(image.getWidth()/2), down-(image.getHeight()/2), null);
    }

    static void paintImageRelativeToCenter(JFrame frame, BufferedImage image, int right, int down){
        paintImage(frame, image, right+(frame.getWidth()/2), down+(frame.getHeight()/2));
    }

    static void displayBuffer(JFrame frame){
        frame.getBufferStrategy().show();
    }
}