import Model.Game;
import View.GameView;
import Controller.KeyboardInput;

public class Main {

    /**
     * First method to be executed, initiates an instance of Game (Model), mainView (View) and keyboardInput
     * (Controller), and starts the game. First method to be executed.
     */
    public static void main(String[] args) {
        Game game = new Game(1000, 1);
        GameView mainView = new GameView(game, 1000, 800);
        KeyboardInput keyboardInput = new KeyboardInput(mainView.getFrameRootPane(), game.getPlayerInputList());

        game.startGame(); // The world comes to life!
    }
}