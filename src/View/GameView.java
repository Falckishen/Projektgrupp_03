package View;

import Controller.KeyboardInput;
import Model.Entities.Entity;
import Model.Game;
import Model.Entities.Position;
import Utilities.ViewObserver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameView extends JComponent implements ViewObserver {
    private ScreenDirector screenDirector;
    private Game game;
    private JFrame frame;
    private int displayWidth;
    private int displayHeight;
    private BufferedImage specialBorderBackground;

    public GameView(Game game, int width, int height){
        ImageContainer.loadImages();
        this.frame = UFrameInterface.createFrame(width, height);
        this.displayWidth = width;
        this.displayHeight = height;
        new KeyboardInput(game, frame.getRootPane());
        game.addViewObserver(this);
        this.game = game;
        specialBorderBackground = generateSpecialBorderBackground();

        /*screenDirector = new ScreenDirector();
        ImageContainer.loadImages();
        screenDirector.startGameScreen(game);
        game.addViewObserver(this);
        this.game = game;*/
    }


    /*public void addKeyListener(Game game){
        screenDirector.addKeyListener(game);
    }*/

    // THIS METHOD IS CALLED EVERY TICK, DRAWS WORLD
    @Override
    public void drawWorld() {
        Position playerPosition = game.getPlayerPosition();
        paintBackground(playerPosition);
        paintPlayer();
        refreshScreen();
        /*Position playerPosition = game.getPlayerPosition();
        screenDirector.inputPlayerPosition(playerPosition);
        screenDirector.paintBackground();
        screenDirector.paintSpriteRelativeToWorld(ImageContainer.getImageFromTypeAndVariant(0,0), playerPosition);
        /*paintEntities();*/
        //screenDirector.refreshScreen();
    }

    private void refreshScreen(){
        UFrameInterface.displayBuffer(frame);
    }

    private void paintPlayer(){
        BufferedImage player = ImageContainer.getImageFromTypeVariant(UImageTypeEnum.PLAYER, 0);
        UFrameInterface.paintImageRelativeToCenter(frame, player, 0, 0);
    }

    private void paintBackground(Position playerPosition){
        BufferedImage background = ImageContainer.getImageFromTypeVariant(UImageTypeEnum.MISC, 0);
        Position position = new Position(0, 0);
        position = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        position = ConversionQueryable.repeatingEverySize(position, background.getWidth(), background.getHeight());
        UFrameInterface.paintImageRelativeToCenter(frame, specialBorderBackground, position.getX(), position.getY());
    }

    private BufferedImage generateSpecialBorderBackground(){
        BufferedImage background = ImageContainer.getImageFromTypeVariant(UImageTypeEnum.MISC, 0);
        int backW = background.getWidth();
        int backH = background.getHeight();
        int dispW = displayWidth;
        int dispH = displayHeight;
        BufferedImage outputImage = new BufferedImage(backW+dispW, backH+dispH, BufferedImage.TYPE_INT_ARGB);
        Graphics g = outputImage.getGraphics();
        for(int x=-1; x<2; x++){
            for(int y=-1; y<2; y++){
                g.drawImage(background, (x*backW) + (dispW/2), (y*backH) + (dispH/2), null);
            }
        }
        g.dispose();
        return(outputImage);
    }

    private void paintEntities(Entity[] entities){
        /*for(Entity entity : entities){
            Position pos = entity.getCurrentPosition();
            screenDirector.paintSpriteRelativeToWorld(ImageContainer.getImageFromTypeAndVariant(entity.type, entity.state), pos.getX(), pos.getY());
        }*/
    }
}
