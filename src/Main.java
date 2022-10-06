import Model.MainMenu;
import View.GameView;
import Controller.KeyboardInput;

/**
 * @author
 */
public class Main {

    /**
     * First method to be executed, initiates an instance of MainMenu (Model), GameView (View) and KeyboardInput
     * (Controller), and starts the game. First method to be executed.
     */
    public static void main(String[] args) {
        String gameName = "Projektgrupp 3 projekt";
        int worldMapRadius = 1000;

        MainMenu mainMenu = new MainMenu(gameName, worldMapRadius);
        GameView mainView = new GameView(mainMenu, 1000, 800);
        new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getPlayerInputList(), mainMenu);

        mainMenu.showMainMenu();
    }
}