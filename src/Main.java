import Model.MainMenu;
import View.GameView;
import Controller.KeyboardInput;

public class Main {

    /**
     * First method to be executed, initiates an instance of Game (Model), mainView (View) and keyboardInput
     * (Controller), and starts the game. First method to be executed.
     */
    public static void main(String[] args) {
        String gameName = "Projektgrupp 3 projekt";
        int worldMapRadius = 1000;

        MainMenu mainMenu = new MainMenu(gameName, worldMapRadius);
        GameView mainView = new GameView(mainMenu, 1000, 800);
        new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getPlayerInputList());

        mainMenu.startGame();
    }
}