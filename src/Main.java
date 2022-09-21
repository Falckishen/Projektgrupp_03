import Model.Game;

import Controller.KeyboardInput;
import View.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(1000);                                                                            // Model
        GameView mainView = new GameView(game, 1000, 800);                                                  // View
        KeyboardInput keyboardInput = new KeyboardInput(mainView.getFrameRootPane(), game.getPlayerInputArrayList());   // Controller

        game.startGame();                                                                                               // The world comes to life!
    }
}