package Model;

import javax.swing.*;

/**
 * Interface for all the views.
 *
 * @author Samue Falck
 */
public interface ViewObserver {

    /**
     * Used by Controller. Returns the root pane of the main frame.
     *
     * @return the root pane of the main frame.
     */
    JComponent getMainFrameRootPane();

    /**
     * Renders a frame of the current game.
     */
    void renderGameFrame();

    /**
     * Renders the main menu.
     */
    void showMainMenu();

    /**
     * Renders the game over screen.
     */
    void showGameOverScreen();

    /**
     * Renders the pause menu.
     */
    void showPauseMenu();

    /**
     * Tells the view to show the game screen.
     */
    void showGameScreen();
}