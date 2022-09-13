import javax.swing.*;
import Model.Game;
import Controller.KeyboardInput;
import View.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();                                 // Model
        KeyboardInput keyboardInput = new KeyboardInput(game);  // Controller
        JPanel mainFrame = new JPanel();
        mainFrame.addKeyListener(keyboardInput);
        GameView mainView = new GameView(game, mainFrame);      // View

        game.start();                                           // The world comes to life!
    }
}