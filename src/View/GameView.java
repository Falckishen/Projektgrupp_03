package View;

import Model.Entities.Entity;

import Model.Game;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;
import View.FramesAndPanels.GamePanel;
import View.FramesAndPanels.MainFrame;
import View.FramesAndPanels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JComponent implements ViewObserver {

    private final Game game;
    private JFrame frame;
    private final int displayWidth;
    private final int displayHeight;
    private final BufferedImage specialBorderBackground;
    private MainFrame mainFrame;

    private GamePanel gamePanel;
    private MainMenuPanel mainMenuPanel;

    public GameView(Game game, int width, int height){
        ImageContainer.loadImages();
        this.displayWidth = width;
        this.displayHeight = height;
        game.addViewObserver(this);
        this.game = game;
        specialBorderBackground = generateSpecialBorderBackground();

        mainFrame = new MainFrame(1000, 800);
        startGame();
    }

    public void startGame(){
        this.gamePanel = new GamePanel();
        mainFrame.replaceSubPanel(gamePanel);
    }

    public void startMainMenu(){
        this.mainMenuPanel = new MainMenuPanel();
        mainFrame.replaceSubPanel(mainMenuPanel);
    }

    public JComponent getFrameRootPane() {
        return mainFrame.getRootPane();
    }

    @Override
    public void drawFrame() {
        Position playerPosition = game.getPlayerPosition();
        if(gamePanel != null){
            paintBackground(playerPosition);
            paintEntities(game.getFriendlies(), playerPosition);
            paintEntities(game.getEnemies(), playerPosition);
            paintEntities(game.getProjectiles(), playerPosition);
            //paintEntities(game.getWalls(), playerPosition);
        }
        refreshScreen();
    }

    private void refreshScreen(){
        mainFrame.refreshScreen();
    }

    private void paintBackground(Position playerPosition){
        BufferedImage background = ImageContainer.getImageFromTypeVariant(UImageTypeEnum.MISC, 0);
        Position position = new Position(0, 0);
        position = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        position = ConversionQueryable.repeatingEverySize(position, background.getWidth(), background.getHeight());
        gamePanel.paintImageRelativeToCenter(specialBorderBackground, position.getX(), position.getY());
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

    private void paintEntities(Iterable<? extends Entity> entities, Position playerPosition){
        for(Entity entity : entities){
            paintEntity(entity, playerPosition);
        }
    }

    private void paintEntity(Entity entity, Position playerPosition){
        if(entity.getEntityType() != EntityType.wall){
            Position pos = entity.getPosition();
            pos = ConversionQueryable.transformWithPlayerPosition(pos, playerPosition);
            int variant;
            if(entity.getEntityType() == EntityType.player){
                if((int)System.currentTimeMillis()/500%200 == 0){
                    variant = 2;
                }else{
                    variant = 1;
                }
            }else{
                variant = 0;
            }
            gamePanel.paintImageRelativeToCenter(ImageContainer.getImageFromTypeVariant(ConversionQueryable.getImageType(entity), variant), pos.getX(), pos.getY());
        }else{
            //paintWall(entity.getHitBoxRadiusX(), entity.getHitBoxRadiusY(), entity.getPosition(), playerPosition);
        }
    }

    public void paintWall(int width, int height, Position position, Position playerPosition){
        BufferedImage wall = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        wall.getGraphics().setColor(Color.BLACK);
        wall.getGraphics().fillRect(0,0,width,height);
        Position newPosition = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        gamePanel.paintImageRelativeToCenter(wall, newPosition.getX(), newPosition.getY());
    }
}