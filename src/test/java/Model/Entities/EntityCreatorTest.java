package Model.Entities;

import Model.Position;
import Model.TickObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityCreatorTest {
    Player player;
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    List<TickObserver> tickObservers;
    EntityCreator EC;


    @BeforeEach
    void initPlayerAndVariables(){
        EC = new EntityCreator(1000,2);
        playerInputs = new ArrayList<>();
        weaponInputs = new ArrayList<>();
        EC.createPlayer(1,1,playerInputs,weaponInputs);
        EC.createWeakMonster();
        player = (Player) EC.getFriendlies().get(0);
        tickObservers = EC.getTickObservers();
    }

    @Test
    void createWeakMonsterTest(){
        assertFalse(EC.getEnemies().isEmpty());
    }
    @Test
    void createPlayer(){
        //Player created in init
        assertTrue(player.getPosition().getX() == 1 && player.getPosition().getY() == 1 && EC.getFriendlies().contains(player));
    }

    @Test
    void createWorldWalls(){
        EC.createWorldBorderWalls();
        int nrWalls = 0;
        for (Entity entity : EC.getAllEntities()) {
            if (entity.getEntityType() == EntityType.wall) {
                nrWalls += 1;
            }
        }
        assertEquals(4, nrWalls);
    }

    @Test
    void getPlayerTest() {
        MovableEntity pl = EC.getPlayer();
        assertSame(pl.getEntityType(), EntityType.player);
    }

    @Test
    void getPlayerPositionTest() {
        Position startPosition = EC.getPlayerPosition();
        Position expectedPosition = new Position(1,1);
        assertEquals(startPosition.getX(), expectedPosition.getX());
        assertEquals(startPosition.getY(), expectedPosition.getY());
    }

    @Test
    void getPlayerHealthTest() {
        int health = EC.getPlayerHealth();
        int expectedHealth = 10;
        assertEquals(health, expectedHealth);
    }

    @Test
    void isAnyEnemiesAliveTest() {
        assertTrue(EC.isAnyEnemiesAlive());
    }

    @Test
    void isPlayerAliveTest() {
        assertTrue(EC.isPlayerAlive());
    }


}