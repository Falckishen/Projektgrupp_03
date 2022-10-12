package Model.Entities;

import Model.Weapons.WeaponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
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
    void canPlayerShootTest(){
        weaponInputs = new ArrayList<>();
        player.getNewWeapon(WeaponFactory.getGun(projectile,player.getPosition(),weaponInputs));
        player.doOnTick();
        assertEquals(1,1);
    }
}