import Model.Game;

import View.GameView;
import Controller.KeyboardInput;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(1000, 2);                                                             // Model
        GameView mainView = new GameView(game, 1000, 800);                                                  // View
        KeyboardInput keyboardInput = new KeyboardInput(mainView.getFrameRootPane(), game.getPlayerInputList());        // Controller

        game.startGame();                                                                                               // The world comes to life!
    }
}