import Model.MainMenu;
import View.GameView;
import Controller.KeyboardInput;

/**
 * @author Samuel Fack
 */
public class Main {

    /**
     * First method to be executed, creates an instance of MainMenu (Model), GameView (View), KeyboardInput
     * (Controller), and starts the game.
     */
    public static void main(String[] args) {
        String gameName = "Projektgrupp 3 projekt";
        int worldMapRadius = 1000;
        int width = 1000;
        int height = 800;

        MainMenu mainMenu = new MainMenu(gameName, worldMapRadius);
        GameView mainView = new GameView(mainMenu, width, height, gameName);
        new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getMovementInputList(), mainMenu, mainMenu.getWeaponInputList());

        mainMenu.showMainMenu();
    }
}