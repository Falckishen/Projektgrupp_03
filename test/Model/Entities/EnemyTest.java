package Model.Entities;

import Model.OnTick;
import Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    Player player;
    Monster monster;
    List<Integer> playerInputs;
    List<Integer> weaponInputs;
    List<OnTick> tickObservers;
    Position playerStartPosition = new Position(1,1);
    Position monsterStartPosition = new Position(1,300);

    @BeforeEach
    void initEnemyAndPlayer(){
        playerInputs = new ArrayList<>();
        weaponInputs = new ArrayList<>();
        EntityCreator EC = new EntityCreator(1000,1);
        EC.createPlayer(playerStartPosition.getX(),playerStartPosition.getY(),playerInputs,weaponInputs);
        player = (Player) EC.getFriendlies().get(0);
        EC.createCustomMonster(monsterStartPosition.getX(),monsterStartPosition.getY(),5,2,1);
        monster = (Monster) EC.getEnemies().get(0);
        tickObservers = EC.getTickObservers();
    }
    @Test
    void walkTowardsPlayer(){
        monster.doOnTick();
        assertTrue(monster.getPosition().getY() == monsterStartPosition.getY()-monster.getSpeed());
    }
    @Test
    void monsterDealDamage(){
        while (player.getHealth() >= 10){
            for (OnTick tickObserver : tickObservers) {
                tickObserver.doOnTick();
            }
        }
        assertEquals(player.getHealth(), 9);
    }
    @Test
    void monsterTakeKnockbackAfterDealingDamage(){
        int monsterStart = monster.getPosition().getY()+1;
        int monsterEnd = monster.getPosition().getY();
        while (monsterStart>monsterEnd){
            monsterStart = monster.getPosition().getY();
            for (OnTick tickObserver : tickObservers){
                tickObserver.doOnTick();
            }
            monsterEnd = monster.getPosition().getY();
        }
        assertTrue(player.getHealth() != 10 && monster.getPosition().getY() == 100 + monster.getSpeed() );
    }
    @Test
    void monsterTakeDamageFromProjectileTest(){
        boolean test = false;
        weaponInputs.add(KeyEvent.VK_DOWN);
        ArrayList<OnTick> tickObserversLoop = new ArrayList<OnTick>(tickObservers);
        boolean loop = true;
        while (loop) {
            tickObserversLoop = new ArrayList<OnTick>(tickObservers);
            for (OnTick tickObserver : tickObserversLoop) {
                tickObserver.doOnTick();
            }
            if (monster.getIsDead()){
                test = true;
                loop = false;
            }
            else if (player.getIsDead()) {
                test = false;
                loop = false;
            }
        }
        assertTrue(test);
    }
}