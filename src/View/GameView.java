package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView {
    private static final int x_width = 1000;
    private static final int y_width = 800;

    DisplayInterface display;

    BufferedImage background;

    Vector playerVector = new Vector(0, 0);

    public GameView(){
        try {
            background = ImageIO.read(new File("src\\View\\Images\\BackgroundPNG.png"));
        } catch (IOException e) {
            System.out.println("Failed to load background");
        }
    }

    public void startGameScreen(){
        display = new Game1Frame(x_width, y_width);
    }

    public void refreshScreen(){
        display.iRepaint();
    }

    public void paintBackground(){
        paintSpriteRelativeToWorld(background, 0, 0);
    }

    public void inputPlayerVector(Vector playerVector){
        this.playerVector = playerVector;
    }

    public void paintSpriteRelativeToWorld(BufferedImage image, int x, int y){
        paintSpriteRelativeToScreen(image, x-playerVector.getX(), y-playerVector.getY());
    }

    public void paintSpriteRelativeToScreen(BufferedImage image, int x, int y){
        int adjustedX = (x) + (x_width/2) - (image.getWidth()/2);
        int adjustedY = (y) + (y_width/2) - (image.getHeight()/2);
        display.paintImageAtVector(image, adjustedX, adjustedY);
    }

    class Vector{
        int x;
        int y;
        Vector(int x, int y){
            this.x = x;
            this.y = y;
        }
        int getX(){
            return x;
        }
        int getY(){
            return y;
        }
    }
}
