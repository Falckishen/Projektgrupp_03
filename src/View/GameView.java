package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import Model.Entities.Entity;
import Model.Game;
import Model.MainMenu;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;
import View.FramesAndPanels.GamePanel;
import View.FramesAndPanels.MainFrame;
import View.FramesAndPanels.MainMenuPanel;
import View.FramesAndPanels.PanelInterface;

/**
 * View without outside input renders either menus or the world that a player might be playing in. It accesses
 * information from the Game class to know where to paint entities as well as the background, it therefore requires
 * a Game instance as input when constructing a View
 */
public class GameView extends JComponent implements ViewObserver {

    private final MainMenu mainMenu;
    private JFrame frame;
    private final int displayWidth;
    private final int displayHeight;
    private final BufferedImage specialBorderBackground;
    private MainFrame mainFrame;
    private PanelInterface activePanel;
    private GamePanel gamePanel;
    private MainMenuPanel mainMenuPanel;

    /**
     * Constructs an instance of the View
     *
     * @param width the width of the frame
     * @param height the height of the frame
     */
    public GameView(MainMenu mainMenu, int width, int height) {
        this.mainMenu = mainMenu;
        ImageContainer.loadImages();
        this.displayWidth = width;
        this.displayHeight = height;
        mainMenu.addViewObserver(this);
        specialBorderBackground = generateSpecialBorderBackground();
        mainFrame = new MainFrame(1000, 800);
        this.activePanel = new GamePanel();
        mainFrame.replaceSubPanel(activePanel);
    }

    /**
     * Render the main menu.
     */
    @Override
    public void showMainMenu() {
        mainFrame.replaceSubPanel(activePanel);
    }

    /**
     * Renders a frame of the current game.
     */
    @Override
    public void renderFrame() {
        Game currentGame = mainMenu.getCurrentGame();
        Position playerPosition = currentGame.getPlayerPosition();
        if (!(playerPosition == null)) {
            paintBackground(playerPosition);
            paintEntities(currentGame.getFriendlies(), playerPosition);
            paintEntities(currentGame.getEnemies(), playerPosition);
            paintEntities(currentGame.getProjectiles(), playerPosition);
            paintEntities(currentGame.getNonLivingObjects(), playerPosition);
            refreshScreen();
        }
    }

    /**
     * @return the root pane of the frame.
     */
    public JComponent getFrameRootPane() {
        return mainFrame.getRootPane();
    }

    /**
     * Tells the mainFrame to refresh the screen.
     */
    private void refreshScreen(){
        mainFrame.refreshScreen();
    }

    /**
     * Renders the background onto the gamePanel stored in activePanel.
     */
    private void paintBackground(Position playerPosition){
        BufferedImage background = ImageContainer.getImageFromTypeVariant(ImageTypeEnum.MISC, 0);
        Position position = new Position(0, 0);
        position = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        position = ConversionQueryable.repeatingEverySize(position, background.getWidth(), background.getHeight());
        ((GamePanel)activePanel).paintImageRelativeToCenter(specialBorderBackground, position.getX(), position.getY());
    }

    /**
     * Takes the background image from the ImageContainer and creates a larger version so that it can be displayed in
     * a loop without it visibly teleporting.
     */
    private BufferedImage generateSpecialBorderBackground(){
        BufferedImage background = ImageContainer.getImageFromTypeVariant(ImageTypeEnum.MISC, 0);
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

    /**
     * Renders all entities in the input onto the gamePanel stored in activePanel.
     * @param entities the entities to render.
     * @param playerPosition the position relative to which the entities are to be rendered.
     */
    private void paintEntities(Iterable<? extends Entity> entities, Position playerPosition){
        for(Entity entity : entities){
            paintEntity(entity, playerPosition);
        }
    }

    /**
     * Renders an entity onto the gamePanel stored in activePanel.
     * @param entity the entity to render.
     * @param playerPosition the position relative to which the entity is to be rendered.
     */
    private void paintEntity(Entity entity, Position playerPosition){
        if(entity.getEntityType() != EntityType.wall){
            Position pos = entity.getPosition();
            pos = ConversionQueryable.transformWithPlayerPosition(pos, playerPosition);
            int variant;
            if(entity.getEntityType() == EntityType.player){
                double changePerSec = 0.5;
                variant = (int)((System.currentTimeMillis()/(changePerSec*1000))%2);
            }else if(entity.getEntityType() == EntityType.monster && ConversionQueryable.gruntType == ImageTypeEnum.RICK){
                double changePerSec = 0.2;
                variant = (int)((System.currentTimeMillis()/(changePerSec*1000))%12);
            }else{
                variant = 0;
            }
            ((GamePanel)activePanel).paintImageRelativeToCenter(ImageContainer.getImageFromTypeVariant(ConversionQueryable.getImageType(entity), variant), pos.getX(), pos.getY());
            paintHitBox(entity, pos);
        }else{
            paintWall(entity.getHitBoxRadiusX()*2, entity.getHitBoxRadiusY()*2, entity.getPosition(), playerPosition);
        }
    }

    /**
     * Debugging method for seeing the hitboxes of a entity
     * @param entity the entity to debug
     * @param pos the position of that entity
     */
    private void paintHitBox(Entity entity, Position pos){
        if(entity.getHitBoxRadiusX() != 0 && entity.getHitBoxRadiusY() != 0){
            BufferedImage hitbox = new BufferedImage(entity.getHitBoxRadiusX()*2, entity.getHitBoxRadiusY()*2, BufferedImage.TYPE_INT_ARGB);
            Graphics g = hitbox.getGraphics();
            g.setColor(Color.red);
            g.drawRect(0, 0, entity.getHitBoxRadiusX()*2-1, entity.getHitBoxRadiusY()*2-1);
            g.dispose();
            ((GamePanel)activePanel).paintImageRelativeToCenter(hitbox, pos.getX(), pos.getY());
        }
    }

    /**
     * Renders a wall onto the gamePanel stored in activePanel.
     * @param width the width of the wall
     * @param height the height of the wall
     * @param position the position of the wall
     * @param playerPosition the position relative to which the wall is to be rendered.
     */
    private void paintWall(int width, int height, Position position, Position playerPosition){
        BufferedImage wall = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        wall.getGraphics().setColor(Color.BLACK);
        wall.getGraphics().fillRect(0,0,width,height);
        Position newPosition = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        ((GamePanel)activePanel).paintImageRelativeToCenter(wall, newPosition.getX(), newPosition.getY());
    }
}