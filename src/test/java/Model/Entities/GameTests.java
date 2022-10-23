package Model.Entities;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameTests {
    Game testGame;
    MainMenu mainMenu;
    @BeforeEach
    void InitGameTests() {
        int rad = 10;
        int diff = 1;
        List<Integer> playerInputList = new ArrayList<>();
        List<Integer> weaponInputList = new ArrayList<>();
        mainMenu = new MainMenu("TestGame", rad);
        mainMenu.startGame();
        testGame = mainMenu.getCurrentGame();
    }

    @Test
    void getPlayerPositionTest() {
        assertNotNull(testGame.getPlayerPosition());
    }

    @Test
    void getPlayerHealthTest() {
        assertTrue(testGame.getPlayerHealth() >= 1);
    }

    @Test
    void getAllEntitiesTest() {
        assertNotNull(testGame.getAllEntities().iterator());
    }

    @Test
    void getRoundTest() throws InterruptedException {
        Thread.sleep(10000);
        assertEquals(1,testGame.getRound());
    }

    @Test
    void pausGameTest() {
        assertFalse(testGame.isGamePaused());
        testGame.pauseGame();
        assertTrue(testGame.isGamePaused());
    }

    @Test
    void unpauseGameTest() {
        testGame.pauseGame();
        testGame.unPauseGame();
        assertFalse(testGame.isGamePaused());
    }

    @Test
    void nextRoundTest() throws InterruptedException {
        Thread.sleep(10000);
        assertEquals(1, testGame.getRound());
    }

    @Test
    void stopGameTest() {
        testGame.stopGame();
        assertNull(mainMenu.getCurrentGame());
    }


}
