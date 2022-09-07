import Model.Game;
import View.GameView;
import Controller.KeyboardInput;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        JPanel mainFrame = new JPanel();
        mainFrame.addKeyListener(new KeyboardInput());
        GameView mainView = new GameView(mainFrame);

        game.start(); // The world comes to life!
    }
}