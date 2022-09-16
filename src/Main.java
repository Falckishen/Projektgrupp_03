import Model.Game;
import View.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();                                     // Model
        //KeyboardInput keyboardInput = new KeyboardInput(game);    // Controller
        GameView mainView = new GameView(game);                     // View
        mainView.addKeyListener(game);

        game.startGame();                                           // The world comes to life!
    }
}