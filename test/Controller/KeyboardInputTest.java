package Controller;

import Model.MainMenu;
import View.GameView;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeyboardInputTest {
    List playerInputList;
    Robot robot;
    MainMenu mainMenu;
    GameView mainView;
    KeyboardInput input;
    @BeforeEach
    void initClasses() throws AWTException {
        mainMenu = new MainMenu("testWorld", 1000);
        mainView = new GameView(mainMenu, 1000, 800);
        input = new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getPlayerInputList(), mainMenu, mainView, mainMenu.getWeaponInputList());
        playerInputList = mainMenu.getPlayerInputList();
        robot = new Robot();
        mainView.showMainMenu();
    }
    @Test
    void wInputTest() throws InterruptedException {
        robot.keyPress(KeyEvent.VK_W);
        for (Object o : playerInputList) {
            if ((int)o == KeyEvent.VK_W){
                assertEquals(1,1);
            }
        }
    }
    @Test
    void aInputTest() throws InterruptedException {
        boolean test = false;
        for (Object o : mainMenu.getPlayerInputList()) {
            System.out.println(o);
            if ((int) o == KeyEvent.VK_A) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_A);
        assertTrue(test);
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