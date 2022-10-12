package Model.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    Player player;
    Monster monster;
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    Iterable<Friendly> friendlyIterator;

    @BeforeEach
    void initEnemyAndPlayer(){
        playerInputs = new ArrayList<>();
        weaponInputs = new ArrayList<>();
        EntityCreator EC = new EntityCreator(1000,1);
        EC.createPlayer(1,1,playerInputs,weaponInputs);
        player = (Player) EC.getFriendlies().get(0);
        EC.createCustomMonster(1,11,5);
        monster = (Monster) EC.getEnemies().get(0);
    }
    @Test
    void walkTowardsPlayer(){
        monster.doOnTick();
        assertTrue(monster.getPosition().getY() == 11-monster.getSpeed());
    }
}