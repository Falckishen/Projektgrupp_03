package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    MainMenu mainMenu;
    @BeforeEach
    void init(){
        mainMenu = new MainMenu("test", 1000);
    }

    @Test
    void setDifficulty() {
        mainMenu.setDifficulty(3);
        assertEquals(mainMenu.getDifficulty(), 3);
    }
}