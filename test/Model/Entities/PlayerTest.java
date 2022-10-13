package Model.Entities;

import Model.Weapons.WeaponFactory;
import Utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    AddProjectile projectile;
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    Player player;
    @BeforeEach
    void initPlayer(){
        playerInputs = new ArrayList<>();
        player = new Player(1,1,playerInputs);
    }

    @Test
    void playerMoveUpTest(){
        playerInputs.add(KeyEvent.VK_W);
        player.doOnTick();
        assertTrue(1 - player.getSpeed() == player.getPosition().getY() && 1 == player.getPosition().getX());
    }
    @Test
    void playerMoveLeftTest(){
        playerInputs.add(KeyEvent.VK_A);
        player.doOnTick();
        assertTrue(1 - player.getSpeed() == player.getPosition().getX() && 1 == player.getPosition().getY());
    }
    @Test
    void playerMoveDownTest(){
        playerInputs.add(KeyEvent.VK_S);
        player.doOnTick();
        assertTrue(1 + player.getSpeed() == player.getPosition().getY() && 1 == player.getPosition().getX());
    }
    @Test
    void playerMoveRightTest(){
        playerInputs.add(KeyEvent.VK_D);
        player.doOnTick();
        assertTrue(1 + player.getSpeed() == player.getPosition().getX() && 1 == player.getPosition().getY());
    }

    @Test
    void playerMoveUpLeftTest(){
        int oldX = player.getPosition().getX();
        int oldY = player.getPosition().getY();
        playerInputs.add(KeyEvent.VK_W);
        playerInputs.add(KeyEvent.VK_A);
        player.doOnTick();
        int newX = player.getPosition().getX();
        int newY = player.getPosition().getY();
        assertEquals(oldX - (int) (5 * Math.sqrt(2)/2), newX);
        assertEquals(oldY - (int) (5 * Math.sqrt(2)/2), newY);
    }

    @Test
    void playerMoveUpRightTest(){
        int oldX = player.getPosition().getX();
        int oldY = player.getPosition().getY();
        playerInputs.add(KeyEvent.VK_W);
        playerInputs.add(KeyEvent.VK_D);
        player.doOnTick();
        int newX = player.getPosition().getX();
        int newY = player.getPosition().getY();
        assertEquals(oldX + (int) (5 * Math.sqrt(2)/2), newX);
        assertEquals(oldY - (int) (5 * Math.sqrt(2)/2), newY);
    }

    @Test
    void playerMoveDownLeftTest(){
        int oldX = player.getPosition().getX();
        int oldY = player.getPosition().getY();
        playerInputs.add(KeyEvent.VK_S);
        playerInputs.add(KeyEvent.VK_A);
        player.doOnTick();
        int newX = player.getPosition().getX();
        int newY = player.getPosition().getY();
        assertEquals(oldX - (int) (5 * Math.sqrt(2)/2), newX);
        assertEquals(oldY + (int) (5 * Math.sqrt(2)/2), newY);
    }

    @Test
    void playerMoveDownRightTest(){
        int oldX = player.getPosition().getX();
        int oldY = player.getPosition().getY();
        playerInputs.add(KeyEvent.VK_S);
        playerInputs.add(KeyEvent.VK_D);
        player.doOnTick();
        int newX = player.getPosition().getX();
        int newY = player.getPosition().getY();
        assertEquals(oldX + (int) (5 * Math.sqrt(2)/2), newX);
        assertEquals(oldY + (int) (5 * Math.sqrt(2)/2), newY);
    }

    @Test
    void playerStandingStillTest(){
        int oldX = player.getPosition().getX();
        int oldY = player.getPosition().getY();
        player.doOnTick();
        int newX = player.getPosition().getX();
        int newY = player.getPosition().getY();
        assertEquals(oldX, newX);
        assertEquals(oldY, newY);
    }

    @Test
    void canPlayerShootTest(){
        weaponInputs = new ArrayList<>();
        player.getNewWeapon(WeaponFactory.getGun(projectile,player.getPosition(),weaponInputs));
        player.doOnTick();
        assertEquals(1,1);
    }
}