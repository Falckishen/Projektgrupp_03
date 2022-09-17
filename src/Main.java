import Model.Game;
import View.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();                                     // Model
        //KeyboardInput keyboardInput = new KeyboardInput(game);    // Controller
        GameView mainView = new GameView(game, 1000, 800);                     // View

        game.startGame();                                           // The world comes to life!
    }
}