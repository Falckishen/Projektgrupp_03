package Controller;

import Model.MainMenu;
import View.GameView;
import org.junit.jupiter.api.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardInputTest {
    List playerInputList;
    @BeforeEach
    void initClasses(){
        MainMenu mainMenu = new MainMenu("testWorld", 1000);
        GameView mainView = new GameView(mainMenu, 1000, 800);
        var keyboardInput = new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getPlayerInputList(), mainMenu, mainView, mainMenu.getWeaponInputList());
        playerInputList = mainMenu.getPlayerInputList();
        mainView.showMainMenu();
    }
    @Test
    void wInputTest() throws InterruptedException {
        boolean loop = true;
        System.out.println("Press and hold W");
        while (loop){
            for (Object o : playerInputList) {
                if ((int)o == KeyEvent.VK_W){
                    loop = false;
                }
            }
            Thread.sleep(100);
        }
    }
    @Test
    void aInputTest() throws InterruptedException {
        boolean loop = true;
        System.out.println("Press and hold A");
        while (loop){
            for (Object o : playerInputList) {
                if ((int)o == KeyEvent.VK_A){
                    loop = false;
                }
            }
            Thread.sleep(100);
        }
    }
    @Test
    void sInputTest() throws InterruptedException {
        boolean loop = true;
        System.out.println("Press and hold S");
        while (loop){
            for (Object o : playerInputList) {
                if ((int)o == KeyEvent.VK_S){
                    loop = false;
                }
            }
            Thread.sleep(100);
        }
    }
    @Test
    void dInputTest() throws InterruptedException {
        boolean loop = true;
        System.out.println("Press and hold D");
        while (loop){
            for (Object o : playerInputList) {
                if ((int)o == KeyEvent.VK_D){
                    loop = false;
                }
            }
            Thread.sleep(100);
        }
    }
}