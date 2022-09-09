package View;

import Model.Entity;
import Utilities.ViewObserver;
import javax.swing.*;
import java.awt.*;

public class GameView implements ViewObserver {
    ScreenDirector screenDirector;

    public GameView(JPanel mainFrame){
        screenDirector = new ScreenDirector();
        ImageContainer.compileImages();
        screenDirector.startGameScreen(mainFrame);
    }

    private void paintEntities(Entity[] entities){
        //for()
    }

    // updateScreenSequence
    @Override
    public void drawWorld() {
        /*screenDirector.inputPlayerPosition();
        screenDirector.paintBackground();
        paintEntities();
        screenDirector.refreshScreen();*/
    }
}
