package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import Model.Entities.Entity;
import Model.Game;
import Utilities.Position;
import Utilities.ViewObserver;

public class GameView extends JComponent implements ViewObserver {

    private ScreenDirector screenDirector;
    private final Game game;
    private final JFrame frame;
    private final int displayWidth;
    private final int displayHeight;
    private final BufferedImage specialBorderBackground;

    public GameView(Game game, int width, int height){
        ImageContainer.loadImages();
        this.frame = UFrameInterface.createFrame(width, height);
        this.displayWidth = width;
        this.displayHeight = height;
        this.game = game;
        this.game.addViewObserver(this);
        specialBorderBackground = generateSpecialBorderBackground();
    }

    public JComponent getFrameRootPane() {
        return frame.getRootPane();
    }

    @Override
    public void drawFrame() {
        Position playerPosition = game.getPlayerPosition();
        paintBackground(playerPosition);
        paintEntities(game.getFriendlies(), playerPosition);
        paintEntities(game.getEnemies(), playerPosition);
        refreshScreen();
    }

    private void refreshScreen(){
        UFrameInterface.displayBuffer(frame);
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

    private void paintEntities(ArrayList<Entity> entities, Position playerPosition){
        for(Entity entity : entities){
            paintEntity(entity, playerPosition);
        }
    }

    private void paintEntity(Entity entity, Position playerPosition){
        Position pos = entity.getCurrentPosition();
        pos = ConversionQueryable.transformWithPlayerPosition(pos, playerPosition);
        UFrameInterface.paintImageRelativeToCenter(frame, ImageContainer.getImageFromTypeVariant(ConversionQueryable.getImageType(entity), 0), pos.getX(), pos.getY());
    }
}