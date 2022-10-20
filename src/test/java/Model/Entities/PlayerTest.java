/*package Model.Entities;

import Model.Entities.*;
import Model.OnTick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    List<Friendly> playerList;
    List<Enemy> enemyList;
    List<Projectile> projectileList;
    List<Entity> nonLivingObjectList;

    Player player;
    EntityCreator EC;
    Monster monster;
    CollisionHandler collisionHandler;

    @BeforeEach
    void initPlayer(){
        playerInputs = new ArrayList<>();
        weaponInputs = new ArrayList<>();
        EC = new EntityCreator(1000,2);
        EC.createPlayer(1,1,playerInputs,weaponInputs);
        player = (Player) EC.getFriendlies().get(0);
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
    void canPlayerShootTest() throws InterruptedException {
        weaponInputs.add(KeyEvent.VK_RIGHT);
        List<OnTick> tickObserversLoop;
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(EC.getTickObservers());
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (EC.getProjectiles().size() > 0){
                loop = false;
            }
        }
        assertTrue(true);
    }
    @Test
    void playerShootUp(){
        boolean test = false;
        weaponInputs.add(KeyEvent.VK_UP);
        List<OnTick> tickObserversLoop;
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(EC.getTickObservers());
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (tickObserversLoop.size() > 3){
                loop = false;
                if (EC.getProjectiles().get(0).getPosition().getY() < -10){
                    test = true;
                }
            }
        }
        assertTrue(test);
    }
    @Test
    void playerShootLeft(){
        boolean test = false;
        weaponInputs.add(KeyEvent.VK_LEFT);
        List<OnTick> tickObserversLoop;
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(EC.getTickObservers());
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (tickObserversLoop.size() > 3){
                loop = false;
                if (EC.getProjectiles().get(0).getPosition().getX() < -10){
                    test = true;
                }
            }
        }
        assertTrue(test);
    }
    @Test
    void playerShootDown(){
        boolean test = false;
        weaponInputs.add(KeyEvent.VK_DOWN);
        List<OnTick> tickObserversLoop;
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(EC.getTickObservers());
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (tickObserversLoop.size() > 3){
                loop = false;
                if (EC.getProjectiles().get(0).getPosition().getY() > 10){
                    test = true;
                }
            }
        }
        assertTrue(test);
    }
    @Test
    void playerShootRight(){
        boolean test = false;
        weaponInputs.add(KeyEvent.VK_RIGHT);
        List<OnTick> tickObserversLoop;
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(EC.getTickObservers());
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (tickObserversLoop.size() > 3){
                loop = false;
                if (EC.getProjectiles().get(0).getPosition().getX() > 10){
                    test = true;
                }
            }
        }
        assertTrue(test);
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
}*/