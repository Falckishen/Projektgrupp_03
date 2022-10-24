import Controller.ButtonHandler;
import Model.MainMenu;
import View.GameView;
import Controller.KeyboardInput;

/**
 * @author Samuel Fack
 */
public class Main {

    /**
     * First method to be executed, creates an instance of MainMenu (Model), GameView (View), KeyboardInput
     * (Controller) and starts the game.
     */
    public static void main(String[] args) {
        String gameName = "Projektgrupp 3 spel";
        int worldMapRadius = 1000;
        int width = 1000;
        int height = 800;

        MainMenu mainMenu = new MainMenu(gameName, worldMapRadius);
        ButtonHandler buttonHandler = new ButtonHandler(mainMenu);
        GameView gameView = new GameView(mainMenu, buttonHandler, width, height, gameName);
        new KeyboardInput(mainMenu, gameView);

        mainMenu.showMainMenu();
    }
}