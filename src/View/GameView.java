package View;

import Model.Entity;
import Model.Game;
import Utilities.ViewObserver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameView implements ViewObserver {
    ScreenDirector screenDirector;

    public GameView(Game game, JPanel mainFrame){
        screenDirector = new ScreenDirector();
        ImageContainer.compileImages();
        screenDirector.startGameScreen(mainFrame);
        game.addViewObserver(this);
    }

    public void addKeyListener(KeyListener keyListener){
        screenDirector.addKeyListener(keyListener);
    }

    private void paintEntities(Entity[] entities){
        //for()
    }

    // THIS METHOD IS CALLED EVERY TICK, DRAWS WORLD
    @Override
    public void drawWorld() {
        /*screenDirector.inputPlayerPosition();
        screenDirector.paintBackground();
        paintEntities();
        screenDirector.refreshScreen();*/
    }
}
