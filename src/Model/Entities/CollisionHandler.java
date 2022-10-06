package Model.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import Model.OnTick;
import Utilities.Position;

/**
 * The class handling the collision between all the entities in a given game.
 *<p>
 *     This class specifically checks for collided objects and then calls for them to update according
 *     to which kind of collision that has occurred.
 *</p>
 * @author Ida Altenstedt
 */
class CollisionHandler implements OnTick {

    /**
     * An Iterable of objects that is subject to the abstract class Friendly's collision methods.
     * They are all subtypes of the abstract class Friendly.
     */
    private final Iterable<Friendly> friendlies;

    /**
     * An Iterable of objects that is subject to the abstract class Enemy's collision methods.
     * They are all subtypes of the abstract class Enemy.
     */
    private final Iterable<Enemy> enemies;

    /**
     * An Iterable of objects that is subject to the abstract class Projectile's collision methods.
     * They are all subtypes of the abstract class Projectile.
     */
    private final Iterable<Projectile> projectiles;

    /**
     * An Iterable of only non-living entities that isn't subject to any collision of its own.
     * They are all subtypes of the abstract class Entity,
     * and are needed for checking when other entities collided with them.
     */
    private final Iterable<Entity> nonLivingObjects;

    /**
     * When an instance of the collision class is made it is provided with aliases of all the lists needed
     * for checking collision.
     *
     * @param friendlies an instance of the list of friendlies saved as a higher abstraction (Iterable).
     * @param enemies an instance of the list of enemies saved as a higher abstraction (Iterable).
     * @param projectiles an instance of the list of projectiles saved as a higher abstraction (Iterable).
     * @param nonLivingObjects an instance of the list of non-living objects saved as a higher abstraction (Iterable).
     */
    CollisionHandler(Iterable<Friendly> friendlies, Iterable<Enemy> enemies, Iterable<Projectile> projectiles,
                     Iterable<Entity> nonLivingObjects){
        this.friendlies = friendlies;
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.nonLivingObjects = nonLivingObjects;
    }

    /**
     * Calls methods to check for collision and removes "dead" objects from the game.
     */
    @Override
    public void doOnTick() {
        checkCollisionFriendlyEnemy();
        checkCollisionEnemyEnemy();
        checkCollisionEnemyProjectile();

        checkCollisionWithNonLivingObjects();
        removeDead();
    }

    /**
     * checks for collision between friendlies and enemies.
     * And calls objects that has collided, with the specific collision that has occurred.
     */
    private void checkCollisionFriendlyEnemy() {
        Friendly f;
        Enemy e;
        Iterator<Friendly> itFriendlies = friendlies.iterator();
        while(itFriendlies.hasNext()){
            f = itFriendlies.next();
            Iterator<Enemy> itEnemies = enemies.iterator();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(f, e)){
                    f.CollidedWithEnemy(e.getAttackPower());
                    e.collidedWithFriendly(f.getPosition());
                }
            }
        }
    }

    /**
     * checks for collision between enemies and enemies.
     * And calls objects that has collided, with the specific collision that has occurred.
     *              Can handle collision with multiple enemies at once.
     */
    private void checkCollisionEnemyEnemy() {
        ArrayList<Position> enemiesCollidedWithMe = new ArrayList<>();
        Enemy e1;
        Enemy e2;
        Iterator<Enemy> itEnemies1 = enemies.iterator();
        while(itEnemies1.hasNext()){ // don't use for-loop (not as accurate)
            e1 = itEnemies1.next();
            Iterator<Enemy> itEnemies2 = enemies.iterator();
            while(itEnemies2.hasNext()){ // don't use for-loop (not as accurate)
                e2 = itEnemies2.next();
                if (e1 != e2){
                    if(hasCollided(e1, e2)){
                        enemiesCollidedWithMe.add(e2.getPosition());
                    }
                }
            }
            if (!enemiesCollidedWithMe.isEmpty()){
                e1.collidedWIthEnemy(enemiesCollidedWithMe.iterator());
                enemiesCollidedWithMe.clear();
            }
        }
    }

    /**
     * checks for collision between enemies and projectiles.
     * And calls objects that has collided, with the specific collision that has occurred.
     */
    private void checkCollisionEnemyProjectile() {
        Projectile p;
        Enemy e;
        Iterator<Projectile> itProjectiles = projectiles.iterator();
        while(itProjectiles.hasNext()){
            p = itProjectiles.next();
            Iterator<Enemy> itEnemies = enemies.iterator();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(p, e)){
                    p.CollidedWithEnemy();
                    e.collidedWithProjectile(p.getAttackPower());
                }
            }
        }
    }

    /**
     * checks for collision between living objects (friendlies, enemies and projectiles) and non-living objects.
     * And calls living objects that has collided, with the specific collision that has occurred.
     */
    private void checkCollisionWithNonLivingObjects(){
        Entity n;
        Friendly f;
        Enemy e;
        Projectile p;

        Iterator<Entity> itNonLivingObjects = nonLivingObjects.iterator();
        while(itNonLivingObjects.hasNext()){
            n = itNonLivingObjects.next();

            Iterator<Friendly> itFriendlies = friendlies.iterator();
            while(itFriendlies.hasNext()){
                f = itFriendlies.next();
                if(hasCollided(n, f)){
                    f.collidedWithNonLivingObject(n);
                }
            }
            Iterator<Enemy> itEnemies = enemies.iterator();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(n, e)){
                    e.collidedWithNonLivingObject(n);
                }
            }
            Iterator<Projectile> itProjectiles = projectiles.iterator();
            while(itProjectiles.hasNext()){
                p = itProjectiles.next();
                if(hasCollided(n, p)){
                    p.collidedWithNonLivingObject();
                }
            }
        }
    }

    /**
     * The method that actually checks if a collision has occurred between two objects.
     *          This method only checks along the X-axis and then calls hasCollidedYAxis() for checking the Y-axis.
     * @param entity1 first Entity being checked.
     * @param entity2 second Entity being checked.
     * @return true if a collision has occurred, false if it hasn't.
     */
    private boolean hasCollided(Entity entity1, Entity entity2) {
        //collided on x-axis
        if (entity1.getPosition().getX() < entity2.getPosition().getX()) {//is entity1 to the left of entity2
            if ( (entity1.getPosition().getX() + entity1.getHitBoxRadiusX() ) >
                    (entity2.getPosition().getX() - entity2.getHitBoxRadiusX() )){
                return hasCollidedYAxis(entity1, entity2);
            }
        }
        else{
            if ( (entity1.getPosition().getX() - entity1.getHitBoxRadiusX() ) <
                    (entity2.getPosition().getX() + entity2.getHitBoxRadiusX() )){
                return hasCollidedYAxis(entity1, entity2);
            }
        }
        return false;
    }

    /**
     * The method that checks collision along the Y-axis,
     *              Is specifically used as a subpart of the hasCollided() method.
     * @param entity1 first Entity being checked.
     * @param entity2 second Entity being checked.
     * @return true if a collision has occurred, false if it hasn't.
     */
    private Boolean hasCollidedYAxis(Entity entity1, Entity entity2){
        //collided on y-axis (Also simplified to return result)
        if (entity1.getPosition().getY() < entity2.getPosition().getY()) {//is entity1 lower than entity2
            return (entity1.getPosition().getY() + entity1.getHitBoxRadiusY()) >
                    (entity2.getPosition().getY() - entity2.getHitBoxRadiusY());
        }
        else{
            return (entity1.getPosition().getY() - entity1.getHitBoxRadiusY()) <
                    (entity2.getPosition().getY() + entity2.getHitBoxRadiusY());
        }
    }

    /**
     * Calls all Iterable to remove dead Entities from.
     */
    private void removeDead() {
        removeDeadFromList(friendlies.iterator());
        removeDeadFromList(enemies.iterator());
        removeDeadFromList(projectiles.iterator());
    }

    /**
     * Removes all dead from the input iterator.
     * @param itList an iterator of any subclass of the abstract class Entity.
     */
    private void removeDeadFromList(Iterator<? extends Entity> itList){
        while (itList.hasNext()){
            if (itList.next().getIsDead()) {
                itList.remove();}
        }
    }
}