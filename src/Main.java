import Model.Game;
import View.GameView;
import Controller.KeyboardInput;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();                                 // Model
        KeyboardInput keyboardInput = new KeyboardInput(game);  // Controller
        JPanel mainFrame = new JPanel();
        mainFrame.addKeyListener(keyboardInput);
        GameView mainView = new GameView(mainFrame);            // View

        game.start();                                           // The world comes to life!
    }
}