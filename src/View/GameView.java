package View;

import View.Version2Display.Game2Frame;

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

    /**
     * Starts the display.
     * <p></p> Must be called before using other display related methods from GameView.
     */
    public void startGameScreen(){
        //display = new Game1Frame(x_width, y_width);
        display = new Game2Frame(x_width, y_width);
    }

    /**
     * Displays everything that has been painted since the last frame, after displaying clears the frame.
     * <p></p> Must be called for the user to see anything.
     */
    public void refreshScreen(){
        display.iRepaint();
        paintBackground();
    }

    private void paintBackground(){
        paintSpriteRelativeToWorld(background, 0, 0);
    }

    /**
     * Inputs the players location so that the GameView knows where to render relative to the world.
     * <p></p> Must be called before using paintSpriteRelativeToWorld().
     * @param playerVector the vector relative to the world of the player
     */
    public void inputPlayerVector(Vector playerVector){
        this.playerVector = playerVector;
    }

    /**
     * Paints an image on the screen with the vector relative to the world, needs.
     * <p></p> Must be called before using other display related methods from GameView.
     */
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
