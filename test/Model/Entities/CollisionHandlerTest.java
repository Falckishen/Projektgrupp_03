package Model.Entities;

import Model.OnTick;
import Utilities.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerTest {

    EntityCreator EC;
    //monster hitBox radius = 50;

    @BeforeEach
    void init(){
        EC = new EntityCreator(1000,2);

    }

    MovableEntity createTestEnemy(int positionX, int positionY){
        EC.createCustomMonster(positionX, positionY, 10, 2, 1);
        return EC.getEnemies().get(EC.getEnemies().size()-1);

    }
    MovableEntity createTestProjectile(int positionX, int positionY, Direction direction){
        EC.createSimpleProjectile(new Position(positionX, positionY), direction, 10, 20, 1);
        return EC.getProjectiles().get(EC.getProjectiles().size()-1);

    }
    MovableEntity createTestPlayer(int positionX, int positionY){
        List<Integer> playerInputs = new ArrayList<>();
        List<Integer> weaponInputs = new ArrayList<>();
        EC.createPlayer(positionX, positionY, playerInputs, weaponInputs);
        return EC.getFriendlies().get(EC.getFriendlies().size()-1);

    }

    void doATick(){
        Iterator<OnTick> tickIterator = EC.getTickObservers().iterator();
        while (tickIterator.hasNext()){
            tickIterator.next().doOnTick();
        }
    }

    @Test
    void testTest(){
        assertTrue(true);
    }

    @Test
    void testRegularEnemyEnemyCollision(){
        System.out.println("Check that both enemies move during an enemy-enemy collision.");
        Entity e1 = createTestEnemy(20, 0);
        Entity e2 = createTestEnemy(0, 0);
        doATick();

        System.out.println("New Position e1: " + e1.getPosition().getX() + ", " + e1.getPosition().getY());
        System.out.println("New Position e2: " + e2.getPosition().getX() + ", " + e2.getPosition().getY());

        assertTrue(e1.getPosition().getY() == 0 && e2.getPosition().getY() == 0 && //neither should have moved in y-led
                e1.getPosition().getX() > 20 && e2.getPosition().getX() < 0); //both should have moved in x-led
    }
    @Test
    void testEnemyEnemySamePositionCollision(){
        System.out.println("Both enemies thrown into random directions.");
        Entity e1 = createTestEnemy(0, 0);
        Entity e2 = createTestEnemy(0, 0);
        doATick();

        System.out.println("New Position e1: " + e1.getPosition().getX() + ", " + e1.getPosition().getY());
        System.out.println("New Position e2: " + e2.getPosition().getX() + ", " + e2.getPosition().getY());

        assertTrue(e1.getPosition().getY() != 0 && e2.getPosition().getY() != 0 &&
                e1.getPosition().getX() != 0 && e2.getPosition().getX() != 0);
    }
    @Test
    void testEnemyEnemyMultipleCollision(){
        System.out.println("Resulting position when starting on specified placement.");
        System.out.println("The collisions along the X-axis will take out each other.");
        Entity e1 = createTestEnemy(0, 0);
        createTestEnemy(20, 0);
        createTestEnemy(-20, 0);
        createTestEnemy(0, -20);
        doATick();

        System.out.println("New Position e1: " + e1.getPosition().getX() + ", " + e1.getPosition().getY());

        assertTrue(e1.getPosition().getX() == 0 && e1.getPosition().getY() > 0);
    }

    @Test
    void testEnemyFriendlyCollision(){
        MovableEntity player = createTestPlayer(0,0);
        MovableEntity enemy = createTestEnemy(90, 90);
        boolean playerDidNotMove = false;
        boolean enemyDidMove = false;
        boolean playerTookDamage = false;
        doATick();

        if (player.getPosition().getX() == 0 && player.getPosition().getY() == 0){ //Player did not get pushed back
            playerDidNotMove = true;
        }
        if (enemy.getPosition().getX() > 90 && enemy.getPosition().getY() > 90){ //enemy pushed back
            enemyDidMove = true;
            //problem with the inverted y-axis ask Smaug how she made it
        }
        if (player.getHealth() != 10){ //friendly takes damage //not full health
            playerTookDamage = true;
        }

        System.out.println("The player did not move: " + playerDidNotMove);
        System.out.println("The enemy got pushed backwards: " + enemyDidMove);
        System.out.println("   New enemy position: " + enemy.getPosition().getX() + ", " + enemy.getPosition().getY());
        System.out.println("The player took damage: " + playerTookDamage);

        assertTrue(playerDidNotMove && enemyDidMove && playerTookDamage);
    }
    @Test
    void testEnemyProjectileCollision(){
        fail();
        //enemy takes damage
        //projectile minus one life(dies)
    }
    @Test
    void testProjectileWallCollision(){
        fail();
        //projectile disappears
    }
    @Test
    void testMovableEntityWallCollisions(){
        fail();
        //calls multiple methods
    }
    @Test
    void testHeadOnCollisionWall(){
        fail();
    }
    @Test
    void testAngledCollisionWall(){
        fail();
    }
    @Test
    void testSideCollisionWall(){
        fail();
    }
    @Test
    void testMultipleWallCollision(){
        fail();
    }
}