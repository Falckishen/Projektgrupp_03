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
    static Robot robot;
    static MainMenu mainMenu;
    static GameView mainView;
    static KeyboardInput input;
    @BeforeAll
    static void initClasses() throws AWTException {
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
    @Test
    void upInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_UP);
        Thread.sleep(100);
        for (Object o : mainMenu.getWeaponInputList()) {
            if ((int) o == KeyEvent.VK_UP) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_UP);
        assertTrue(test);
    }
    @Test
    void leftInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_LEFT);
        Thread.sleep(100);
        for (Object o : mainMenu.getWeaponInputList()) {
            if ((int) o == KeyEvent.VK_LEFT) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_LEFT);
        assertTrue(test);
    }
    @Test
    void downInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(100);
        for (Object o : mainMenu.getWeaponInputList()) {
            if ((int) o == KeyEvent.VK_DOWN) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_DOWN);
        assertTrue(test);
    }
    @Test
    void rightInputTest() throws InterruptedException {
        boolean test = false;
        robot.keyPress(KeyEvent.VK_RIGHT);
        Thread.sleep(100);
        for (Object o : mainMenu.getWeaponInputList()) {
            if ((int) o == KeyEvent.VK_RIGHT) {
                test = true;
                break;
            }
        }
        robot.keyRelease(KeyEvent.VK_RIGHT);
        assertTrue(test);
    }
}