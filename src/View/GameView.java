package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView {
    private static final int x_width = 1000;
    private static final int y_width = 800;

    GameFrame display;

    BufferedImage background;

    public GameView(){
        try {
            background = ImageIO.read(new File("src\\View\\BackgroundPNG.png"));
        } catch (IOException e) {
            System.out.println("Failed to load background");
        }
    }

    public void startGameScreen(){
        display = new GameFrame(x_width, y_width);
    }

    public void paintSprite(BufferedImage image, int x, int y){
        display.paintImageAtVector(image, x, y);
    }

    public void refreshScreen(){
        int backgroundX = (x_width/2)-(background.getWidth())/2;
        int backgroundY = (y_width/2)-(background.getHeight())/2;
        display.paintImageAtVector(background, backgroundX, backgroundY);

        display.repaint();
    }
}
