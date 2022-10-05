package Utilities;

/**
 * Interface for all the views.
 *
 * @author Samue Falck
 */
public interface ViewObserver {

    /**
     * Renders a frame of the current game.
     */
    void renderFrame();

    /**
     * Renders the main menu.
     */
    void showMainMenu();
}