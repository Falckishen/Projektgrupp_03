package Controller;

import Model.MainMenu;

/**
 * @author Samuel Falck
 */
public class ButtonHandler {

    private final MainMenu mainMenu;

    /**
     * Creates an instance of ButtonHandler.
     *
     * @param mainMenu reference to the MainMenu.
     */
    public ButtonHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Starts a game.
     */
    public void startGame() {
        mainMenu.startGame();
    }

    /**
     * The main menu appears.
     */
    public void showMainMenu() {
        mainMenu.showMainMenu();
    }

    /**
     * The application shuts down.
     */
    public void quitApplication() {
        mainMenu.quitApplication();
    }

    /**
     * Sets a new difficulty.
     *
     * @param difficulty the new difficulty.
     */
    public void setDifficulty(int difficulty) {
        mainMenu.setDifficulty(difficulty);
    }
}
