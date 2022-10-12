package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.*;
import Model.Entities.Entity;
import Model.Game;
import Model.MainMenu;
import Utilities.EntityType;
import Utilities.Position;
import Utilities.ViewObserver;
import View.FramesAndPanels.*;

/**
 * View without outside input renders either menus or the world that a player might be playing in. It accesses
 * information from the Game class to know where to paint entities as well as the background, it therefore requires
 * a Game instance as input when constructing a View
 */
public class GameView extends JComponent implements ViewObserver {

    private final MainMenu mainMenu;
    private final int displayWidth;
    private final int displayHeight;
    private final BufferedImage specialBorderBackground;
    private final MainFrame mainFrame;
    private PanelInterface activePanel;
    private String gameName;

    /**
     * Constructs an instance of the View
     * @param mainMenu the model for which the view is to render the main menu and the world
     * @param width the width of the frame
     * @param height the height of the frame
     */
    public GameView(MainMenu mainMenu, int width, int height, String gameName) {
        this.mainMenu = mainMenu;
        ImageContainer.loadImages();
        this.displayWidth = width;
        this.displayHeight = height;
        this.gameName = gameName;
        mainMenu.addViewObserver(this);
        specialBorderBackground = generateSpecialBorderBackground();
        mainFrame = new MainFrame(1000, 800);
        startMainMenu();
    }

    /**
     * Renders the world as it is in the Game inside the MainMenu onto the currently active panel and shows the frame to
     * the user.
     */
    @Override
    public void renderGameFrame() {
        if (activePanel == null || !(activePanel.getClass() == GamePanel.class)) {
            startGame();
        }
        Game currentGame = getCurrentGame();
        Position playerPosition = currentGame.getPlayerPosition();
        if (!(playerPosition == null)) {
            try {
                paintBackground(playerPosition);
                paintEntities(currentGame.getFriendlies(), playerPosition);
                paintEntities(currentGame.getEnemies(), playerPosition);
                paintEntities(currentGame.getProjectiles(), playerPosition);
                paintEntities(currentGame.getNonLivingObjects(), playerPosition);
                paintHealthBar(currentGame.getPlayerHealth()/10d);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        refreshScreen();
    }

    /**
     * Renders the main menu through a mainMenuPanel onto the mainFrame.
     */
    @Override
    public void showMainMenu() {
        ActionListener acStart = e -> mainMenu.startGame();
        ActionListener acQuit = e -> mainMenu.quitApplication();
        ActionListener acFirstDifficulty = e -> mainMenu.setDifficulty(1);
        ActionListener acSecondDifficulty = e -> mainMenu.setDifficulty(2);
        ActionListener acThirdDifficulty = e -> mainMenu.setDifficulty(3);
        activePanel = new MainMenuPanel(acStart, acQuit, acFirstDifficulty, acSecondDifficulty, acThirdDifficulty, gameName, mainMenu.getHighScore());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
        mainFrame.refreshScreen();
    }

    @Override
    public void showGameOverScreen() {
        activePanel = new DeathMenuPanel(new retryPressed(), new exitPressed(), mainMenu.getHighScore(), mainMenu.getCurrentGame().getRound());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
    }

    @Override
    public void showPauseMenu() {
        activePanel = new PauseMenuPanel(new resumePressed(), new forfeitPressed());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
    }

    public Game getCurrentGame() {
        return mainMenu.getCurrentGame();
    }

    /**
     * This method configures the View into GAME mode in which the View renders the current world and everything in it
     * as it's stored in the Game instance.
     */
    public void startGame(){
        activePanel = new GamePanel();
        mainFrame.replaceSubPanel(activePanel);
    }

    /**
     * This method configures the View into MAINMENU mode in which the View renders the main menu.
     */
    public void startMainMenu(){
    }

    /**
     * This method configures the View into PAUSE mode in which the View renders the pause menu.
     */
    public void startPauseMenu(){
    }

    public void showDeathMenu() {
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
            ((GamePanel)activePanel).paintImageRelativeToCenter(ImageContainer.getImageFromTypeVariant(Objects.requireNonNull(ConversionQueryable.getImageType(entity)), variant), pos.getX(), pos.getY());
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

    private void paintHealthBar(double remaining){
        BufferedImage healthBar = new BufferedImage(800, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics g = healthBar.getGraphics();
        g.setColor(new Color(0, 255, 0, 75));
        g.fillRect(0,0, (int)Math.round(800*remaining), 70);
        if(remaining != 1){
            g.setColor(new Color(255, 0, 0, 75));
            g.fillRect((int)Math.round(500*remaining),0, 800, 70);
        }
        ((GamePanel)activePanel).paintImage(healthBar, 500, 700);
    }

    class resumePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {mainMenu.getCurrentGame().unPauseGame();}
    }
    class forfeitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {mainMenu.getCurrentGame().stopGame();}
    }
    class retryPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {mainMenu.getCurrentGame().startGame();}
    }
    class exitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {mainMenu.showMainMenu();}
    }
}