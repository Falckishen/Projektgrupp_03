package View;

import Model.Entity;
import Model.Game;
import Model.Position;
import Utilities.ViewObserver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameView implements ViewObserver {
    private ScreenDirector screenDirector;
    private Game game;

    public GameView(Game game, JPanel mainFrame){
        screenDirector = new ScreenDirector();
        ImageContainer.compileImages();
        screenDirector.startGameScreen(mainFrame);
        game.addViewObserver(this);
        this.game = game;
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
        Position playerPosition = game.getPlayerPosition();
        screenDirector.inputPlayerPosition(playerPosition.getX(), playerPosition.getY());
        screenDirector.paintSpriteRelativeToWorld(ImageContainer.getImageFromTypeAndVariant(0,0), playerPosition.getX(), playerPosition.getY());
        /*screenDirector.paintBackground();
        paintEntities();
        screenDirector.refreshScreen();*/
    }
}
