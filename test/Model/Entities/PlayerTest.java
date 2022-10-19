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
    List<Friendly> playerList;
    List<Enemy> enemyList;
    List<Projectile> projectileList;
    List<Entity> nonLivingObjectList;

    Player player;
    Monster monster;
    CollisionHandler collisionHandler;


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

    @Test
    void playerTakesDamageTest(){
        playerList = new ArrayList<>();
        enemyList = new ArrayList<>();
        projectileList = new ArrayList<>();
        nonLivingObjectList = new ArrayList<>();
        playerList.add(player);
        int startHealth = player.getHealth();
        Monster testMonster = new Monster(1,1, playerList,1,1,1);
        enemyList.add(testMonster);
        CollisionHandler collisionHandler = new CollisionHandler(playerList, enemyList,projectileList, nonLivingObjectList);
        collisionHandler.doOnTick();
        assertEquals(startHealth - testMonster.getAttackPower(), player.getHealth());
        player.takeDamage(10);
        assertTrue(player.getIsDead());
    }
}