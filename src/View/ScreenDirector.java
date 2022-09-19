package View;

import Model.Entities.Position;
import Model.Game;
import View.Version2Display.Game2Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenDirector {
//    private static final int width = 1000;
//    private static final int height = 800;
//
//    DisplayInterface display;
//
//    BufferedImage background;
//
//    Position playerPosition = new Position(0, 0);
//
//    public ScreenDirector(){
//        try {
//            background = ImageIO.read(new File("src/View/Images/NewBackgroundOOP.png"));
//        } catch (IOException e) {
//            System.out.println("Failed to load background");
//        }
//    }
//
//    /*private double getWindowScale(){
//        int biggestSize = Math.max(display.getWindowWidth(), display.getWindowHeight());
//        return((double)biggestSize/1000);
//    }*/
//
//    /**
//     * Starts the display.
//     * <p></p> Must be called before using other display related methods from GameView.
//     */
//    public void startGameScreen(Game game){
//        //display = new Game1Frame(x_width, y_width);
//        display = new Game2Frame(width, height);
//    }
//
//    /**
//     * Displays everything that has been painted since the last frame, after displaying clears the frame.
//     * <p></p> Must be called for the user to see anything.
//     */
//    public void refreshScreen(){
//        display.iRepaint();
//    }
//
//    public void paintBackground(){
//        paintSpriteRelativeToWorld(background, new Position(background.getWidth()*(playerPosition.getX()%background.getWidth()), 0));
//        //paintSpriteRelativeToScreen(background, );
//    }
//
//    /**
//     * Inputs the players position so that the GameView knows where to render relative to the world.
//     * <p></p> Must be called before using paintSpriteRelativeToWorld().
//     */
//    public void inputPlayerPosition(Position position){
//        this.playerPosition = new Position(position.getX(), position.getY());
//    }
//
//    /**
//     * Paints an image on the screen with the vector relative to the world.
//     * <p></p> Must have the player position be set before calling.
//     */
//    public void paintSpriteRelativeToWorld(BufferedImage image, Position position){
//        paintSpriteRelativeToScreen(image, position.getX()-playerPosition.getX(), position.getY()-playerPosition.getY());
//    }
//
//    /**
//     * Paints an image on the screen with the vector relative to the world.
//     * <p></p> Must have the player position be set before calling.
//     */
//    public void paintSpriteRelativeToScreen(BufferedImage image, int x, int y){
//        int adjustedX = (x) + (width /2) - (image.getWidth()/2);
//        int adjustedY = (y) + (height /2) - (image.getHeight()/2);
//        display.paintImageAtVector(image, adjustedX, adjustedY);
//    }
//
//    public void addKeyListener(Game game){
//        display.implementKeyListener(game);
//    }
//
//    class Vector{
//        int x;
//        int y;
//        Vector(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//        int getX(){
//            return x;
//        }
//        int getY(){
//            return y;
//        }
//    }
}
