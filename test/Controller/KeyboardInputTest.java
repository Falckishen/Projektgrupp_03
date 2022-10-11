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
        mainView = new GameView(mainMenu, 1000, 800, "badoo");
        input = new KeyboardInput(mainView.getFrameRootPane(), mainMenu.getPlayerInputList(), mainMenu, mainView, mainMenu.getWeaponInputList());
        //playerInputList = mainMenu.getPlayerInputList();
        robot = new Robot();
        mainView.showMainMenu();
    }
    @Test
    void wInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_W);
        Thread.sleep(100);
        for (Object o : mainMenu.getPlayerInputList()) {
            if ((int) o == KeyEvent.VK_W) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_W);
        assertTrue(test);
    }
    @Test
    void aInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_A);
        Thread.sleep(100);
        for (Object o : mainMenu.getPlayerInputList()) {
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
        boolean test = false;
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(100);
        for (Object o : mainMenu.getPlayerInputList()) {
            if ((int) o == KeyEvent.VK_S) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_S);
        assertTrue(test);
    }
    @Test
    void dInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(100);
        for (Object o : mainMenu.getPlayerInputList()) {
            if ((int) o == KeyEvent.VK_D) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_D);
        assertTrue(test);
    }
}