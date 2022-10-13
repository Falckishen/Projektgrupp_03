package Model;

import java.util.List;
import java.util.ArrayList;
import Utilities.ViewObserver;

/**
 * A class that acts as the bridge between the model and the views, uses the observer pattern to not be dependent on the
 * views.
 *
 * @author Samuel Falck
 */
class OutputHandler {

    private final List<ViewObserver> viewObservers;

    /**
     * Creates an instance of OutputHandler.
     */
    OutputHandler() {
        this.viewObservers = new ArrayList<>();
    }

    /**
     * Adds a view to become an observer of the model.
     *
     * @param viewObserver the view to be added as an observer of the model.
     */
    void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    /**
     * All the views renders a frame of the current game.
     */
    void updateGameFrame() {
        viewObservers.forEach(ViewObserver::renderGameFrame);
    }

    /**
     * All the views renders the main menu.
     */
    void showMainMenu() {
        viewObservers.forEach(ViewObserver::showMainMenu);
    }

    /**
     * All the views renders the game over screen.
     */
    void showGameOverScreen() {
        viewObservers.forEach(ViewObserver::showGameOverScreen);
    }

    /**
     * All the views renders the pause menu.
     */
    void showPauseMenu() {
        viewObservers.forEach(ViewObserver::showPauseMenu);
    }

    /**
     * All the views are notified that the game is about to be rendered.
     */
    void showGameScreen(){viewObservers.forEach(ViewObserver::showGameScreen);}
}