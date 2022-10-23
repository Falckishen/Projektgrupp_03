package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.*;

import Controller.ButtonHandler;
import Model.Entities.Entity;
import Model.Game;
import Model.MainMenu;
import Model.Entities.EntityType;
import Model.Position;
import Model.ViewObserver;
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
    private final String gameName;
    private final ButtonHandler buttonHandler;
    private PanelInterface activePanel;
    private Position playerPosition;

    /**
     * Constructs an instance of the View
     * @param mainMenu the model for which the view is to render the main menu and the world
     * @param width the width of the frame
     * @param height the height of the frame
     * @param gameName the name of the game
     */
    public GameView(MainMenu mainMenu, int width, int height, String gameName) {
        this.mainMenu = mainMenu;
        ImageContainer.loadImages();
        this.displayWidth = width;
        this.displayHeight = height;
        this.gameName = gameName;
        this.buttonHandler = new ButtonHandler(mainMenu);
        mainMenu.addViewObserver(this);
        specialBorderBackground = generateSpecialBorderBackground();
        mainFrame = new MainFrame(width, height);
        showMainMenu();
    }

    /**
     * Renders the world as it is in the Game inside the MainMenu onto the currently active panel and shows the frame to
     * the user. If the current activePanel isn't a GamePanel then it switches to it.
     */
    @Override
    public void renderGameFrame() {
        if (!(activePanel.getClass() == GamePanel.class)) {
            showGameScreen();
        }
        Game game = getCurrentGame();
        playerPosition = game.getPlayerPosition();
        if (!(playerPosition == null)) {
            try {
                paintBackground();
                paintEntities(game.getAllEntities());
                double playerHealth = game.getPlayerHealth()/10d;
                paintHealthBar(playerHealth);
                paintRoundNumerals(game.getRound());
            }catch (ClassCastException e){
                System.out.println("Switched panel while rendering");
            }
        }
        mainFrame.refreshScreen();
    }

    /**
     * Switches the ActivePanel to a MainMenu panel and renders it to the user.
     */
    @Override
    public void showMainMenu() {
        ActionListener acStart = e -> buttonHandler.startGame();
        ActionListener acQuit = e -> buttonHandler.quitApplication();
        ActionListener acFirstDifficulty = e -> buttonHandler.setDifficulty(1);
        ActionListener acSecondDifficulty = e -> buttonHandler.setDifficulty(2);
        ActionListener acThirdDifficulty = e -> buttonHandler.setDifficulty(3);
        activePanel = new MainMenuPanel(acStart, acQuit, acFirstDifficulty, acSecondDifficulty, acThirdDifficulty, gameName, mainMenu.getHighScore());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
    }

    /**
     * Switches the ActivePanel to a DeathMenu panel and renders it to the user.
     */
    @Override
    public void showGameOverScreen() {
        activePanel = new DeathMenuPanel(new retryPressed(), new exitPressed(), mainMenu.getHighScore(), getCurrentGame().getRound());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
    }

    /**
     * Switches the ActivePanel to a PauseMenu panel and renders it to the user.
     */
    @Override
    public void showPauseMenu() {
        activePanel = new PauseMenuPanel(new resumePressed(), new forfeitPressed());
        mainFrame.replaceSubPanel(activePanel);
        mainFrame.refreshScreen();
    }

    /**
     * This method configures the View into GAME mode in which the View renders the current world and everything in it
     * as it's stored in the Game instance.
     */
    @Override
    public void showGameScreen() {
        activePanel = new GamePanel();
        mainFrame.replaceSubPanel(activePanel);
    }

    /**
     * @return the root pane of the main frame.
     */
    @Override
    public JComponent getMainFrameRootPane() {
        return mainFrame.getRootPane();
    }

    private Game getCurrentGame() {
        return mainMenu.getCurrentGame();
    }

    /**
     * Renders the special background onto the gamePanel stored in activePanel.
     */
    private void paintBackground(){
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
     */
    private void paintEntities(Iterable<Entity> entities){
        for(Entity entity : entities){
            paintEntity(entity);
        }
    }

    /**
     * Renders an entity onto the gamePanel stored in activePanel.
     * @param entity the entity to render.
     */
    private void paintEntity(Entity entity){
        if(entity.getEntityType() != EntityType.wall){
            Position pos = entity.getPosition();
            pos = ConversionQueryable.transformWithPlayerPosition(pos, playerPosition);
            BufferedImage entityImage = ImageContainer.getImageFromTypeVariant(Objects.requireNonNull(ConversionQueryable.getImageType(entity)), 0);
            ((GamePanel)activePanel).paintImageRelativeToCenter(entityImage, pos.getX(), pos.getY());
        }else{
            paintWall(entity.getHitBoxRadiusX()*2, entity.getHitBoxRadiusY()*2, entity.getPosition());
        }
    }

    /**
     * Renders a wall onto the gamePanel stored in activePanel.
     * @param width the width of the wall
     * @param height the height of the wall
     * @param position the position of the wall
     */
    private void paintWall(int width, int height, Position position){
        BufferedImage wall = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        wall.getGraphics().setColor(Color.BLACK);
        wall.getGraphics().fillRect(0,0,width,height);
        Position newPosition = ConversionQueryable.transformWithPlayerPosition(position, playerPosition);
        ((GamePanel)activePanel).paintImageRelativeToCenter(wall, newPosition.getX(), newPosition.getY());
    }

    /**
     * Renders the players health bar onto the gamePanel stored in activePanel.
     * @param healthRemaining players remaining percentage of the max health
     */
    private void paintHealthBar(double healthRemaining){
        BufferedImage healthBar = new BufferedImage(800, 70, BufferedImage.TYPE_INT_ARGB);
        Graphics g = healthBar.getGraphics();
        g.setColor(new Color(0, 255, 0, 75));
        g.fillRect(0,0, (int)Math.round(800*healthRemaining), 70);
        if(healthRemaining != 1){
            g.setColor(new Color(255, 0, 0, 75));
            g.fillRect((int)Math.round(800*healthRemaining),0, 800, 70);
        }
        ((GamePanel)activePanel).paintImage(healthBar, 500, 700);
    }

    /**
     * Renders the current round count onto the gamePanel stored in activePanel.
     *
     * @param round current round.
     */
    private void paintRoundNumerals(int round){
        String roundString = round + "";
        int right = 30;
        while(roundString.length() > 0){
            BufferedImage image = ImageContainer.getImageFromTypeVariant(ImageTypeEnum.NUMERAL, Integer.parseInt(roundString.charAt(0)+""));
            ((GamePanel)activePanel).paintImage(image, right, 30);
            roundString = removeFirstCharacterFromString(roundString);
            right += 20;
        }
    }

    /**
     * @param toRemoveFrom the string from which the first character is to be removed
     * @return the new string without the first character of the old string
     */
    private String removeFirstCharacterFromString(String toRemoveFrom){
        StringBuilder outputString = new StringBuilder();
        for(int i=1; i < toRemoveFrom.length(); i++) outputString.append(toRemoveFrom.charAt(i));
        return(outputString.toString());
    }

    /**
     * Classes representing what happens when you click certain buttons in the UI.
     */
    class resumePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {getCurrentGame().unPauseGame();}
    }
    class forfeitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {getCurrentGame().stopGame();}
    }
    class retryPressed extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonHandler.startGame();}
    }
    class exitPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonHandler.showMainMenu();}
    }
}