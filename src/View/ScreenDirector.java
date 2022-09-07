package View;

import View.Version2Display.Game2Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenDirector {
    private static final int width = 1000;
    private static final int height = 800;

    DisplayInterface display;

    BufferedImage background;

    Vector playerPosition = new Vector(0, 0);

    public ScreenDirector(){
        try {
            background = ImageIO.read(new File("src\\View\\Images\\BackgroundPNG.png"));
        } catch (IOException e) {
            System.out.println("Failed to load background");
        }
    }

    /*private double getWindowScale(){
        int biggestSize = Math.max(display.getWindowWidth(), display.getWindowHeight());
        return((double)biggestSize/1000);
    }*/

    /**
     * Starts the display.
     * <p></p> Must be called before using other display related methods from GameView.
     */
    public void startGameScreen(JPanel mainFrame){
        //display = new Game1Frame(x_width, y_width);
        display = new Game2Frame(width, height, mainFrame);
    }

    /**
     * Displays everything that has been painted since the last frame, after displaying clears the frame.
     * <p></p> Must be called for the user to see anything.
     */
    public void refreshScreen(){
        display.iRepaint();
    }

    public void paintBackground(){
        paintSpriteRelativeToWorld(background, 0, 0);
    }

    /**
     * Inputs the players position so that the GameView knows where to render relative to the world.
     * <p></p> Must be called before using paintSpriteRelativeToWorld().
     * @param x the x component of the player's position relative to the world
     * @param y the y component of the player's position relative to the world
     */
    public void inputPlayerPosition(int x, int y){
        this.playerPosition = new Vector(x, y);
    }

    /**
     * Paints an image on the screen with the vector relative to the world.
     * <p></p> Must have the player position be set before calling.
     */
    public void paintSpriteRelativeToWorld(BufferedImage image, int x, int y){
        paintSpriteRelativeToScreen(image, x-playerPosition.getX(), y-playerPosition.getY());
    }

    /**
     * Paints an image on the screen with the vector relative to the world.
     * <p></p> Must have the player position be set before calling.
     */
    public void paintSpriteRelativeToScreen(BufferedImage image, int x, int y){
        int adjustedX = (x) + (width /2) - (image.getWidth()/2);
        int adjustedY = (y) + (height /2) - (image.getHeight()/2);
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
