package View;

import Model.Entity;

import javax.swing.*;
import java.awt.*;

public class GameView {
    ScreenDirector screenDirector;

    GameView(JFrame mainFrame){
        screenDirector = new ScreenDirector();
        ImageContainer.compileImages();
        screenDirector.startGameScreen(mainFrame);
    }

    public void updateScreenSequence(){
        /*screenDirector.inputPlayerPosition();
        screenDirector.paintBackground();
        paintEntities();
        screenDirector.refreshScreen();*/
    }

    private void paintEntities(Entity[] entities){
        //for()
    }
}
