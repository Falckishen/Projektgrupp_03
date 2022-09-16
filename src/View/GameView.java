package View;

import Model.Entities.Entity;
import Model.Game;
import Model.Entities.Position;
import Utilities.ViewObserver;
import javax.swing.*;

public class GameView extends JComponent implements ViewObserver {
    private ScreenDirector screenDirector;
    private Game game;

    public GameView(Game game){
        screenDirector = new ScreenDirector();
        ImageContainer.compileImages();
        screenDirector.startGameScreen(game);
        game.addViewObserver(this);
        this.game = game;
    }


    public void addKeyListener(Game game){
        screenDirector.addKeyListener(game);
    }


    private void paintEntities(Entity[] entities){
        //for()
    }

    // THIS METHOD IS CALLED EVERY TICK, DRAWS WORLD
    @Override
    public void drawWorld() {
        Position playerPosition = game.getPlayerPosition();
        screenDirector.inputPlayerPosition(playerPosition.getX(), playerPosition.getY());
        screenDirector.paintBackground();
        screenDirector.paintSpriteRelativeToWorld(ImageContainer.getImageFromTypeAndVariant(0,0), playerPosition.getX(), playerPosition.getY());
        /*paintEntities();*/
        screenDirector.refreshScreen();
    }
}
