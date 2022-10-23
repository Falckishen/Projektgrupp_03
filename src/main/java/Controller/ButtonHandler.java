package Controller;

import Model.MainMenu;

public class ButtonHandler {

    private final MainMenu mainMenu;

    public ButtonHandler(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void startGame() {
        mainMenu.startGame();
    }

    public void showMainMenu() {
        mainMenu.showMainMenu();
    }

    public void quitApplication() {
        mainMenu.quitApplication();
    }

    public void setDifficulty(int difficulty) {
        mainMenu.setDifficulty(difficulty);
    }
}
