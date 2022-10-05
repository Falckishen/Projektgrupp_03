package Model;

import java.util.List;
import java.util.ArrayList;
import Utilities.ViewObserver;

class OutputHandler {

    private final List<ViewObserver> viewObservers;

    public OutputHandler() {
        this.viewObservers = new ArrayList<>();
    }

    /**
     * Adds a view to become an observer of the model.
     *
     * @param viewObserver the view to be added as an observer of the model.
     */
    public void addViewObserver(ViewObserver viewObserver) {
        viewObservers.add(viewObserver);
    }

    public void updateGameFrame() {
        viewObservers.forEach(ViewObserver::drawFrame);
    }

    public void showMainMenu() {
        viewObservers.forEach(ViewObserver::showMainMenu);
    }
}
