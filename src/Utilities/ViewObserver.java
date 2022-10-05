package Utilities;

public interface ViewObserver {

    /**
     * Renders onto the frame depending on the current mode either the world as it is in the Game instance or the
     * main menu if the mode is GAME or MAINMENU respectively.
     */
    void drawFrame();

    void showMainMenu();
}