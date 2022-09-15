import javax.swing.*;
import Model.Game;
import Controller.*;
import View.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();                                 // Model
        //KeyboardInput keyboardInput = new KeyboardInput(game);  // Controller
        GameView mainView = new GameView(game);      // View
        mainView.addKeyListener(game);
        game.start();                                           // The world comes to life!
    }
}