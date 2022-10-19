package Model.Entities;

import Model.OnTick;
import Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntityCreatorTest {
    Monster monster;
    Player player;
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    List<OnTick> tickObservers;
    EntityCreator EC;


    @BeforeEach
    void initPlayerAndVariables(){
        EC = new EntityCreator(1000,2);
        playerInputs = new ArrayList<>();
        weaponInputs = new ArrayList<>();
        EC.createPlayer(1,1,playerInputs,weaponInputs);
        player = (Player) EC.getFriendlies().get(0);
        tickObservers = EC.getTickObservers();
    }

    @Test
    void createWeakMonsterTest(){
        EC = new EntityCreator(1000,1);
        EC.createWeakMonster();
        assertFalse(EC.getEnemies().isEmpty());
    }
    @Test
    void createPlayer(){
        //Player created in init
        assertTrue(player.getPosition().getX() == 1 && player.getPosition().getY() == 1 && EC.getFriendlies().contains(player));
    }
}