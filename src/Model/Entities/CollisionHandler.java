package Model.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import Model.OnTick;
import Utilities.Position;

class CollisionHandler implements OnTick {

    private final Iterable<Friendly> friendlies;
    private final Iterable<Enemy> enemies;
    private final Iterable<Projectile> projectiles;
    private final Iterable<Entity> nonLivingObjects;

    CollisionHandler(Iterable<Friendly> friendlies, Iterable<Enemy> enemies, Iterable<Projectile> projectiles, Iterable<Entity> nonLivingObjects){
        this.friendlies = friendlies;
        this.enemies = enemies;
        this.projectiles = projectiles;
        this.nonLivingObjects = nonLivingObjects;
    }

    @Override
    public void doOnTick() {
        checkCollisionFriendlyEnemy();
        checkCollisionEnemyEnemy();
        checkCollisionEnemyProjectile();

        checkCollisionWithNonLivingObjects();
        removeDead();
    }

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

    private boolean hasCollided(Entity entity1, Entity entity2) {
        //collided on x-axis
        if (entity1.getPosition().getX() < entity2.getPosition().getX()) {//is entity1 to the left of entity2
            if ( (entity1.getPosition().getX() + entity1.getHitBoxRadiusX() ) >
                    (entity2.getPosition().getX() - entity2.getHitBoxRadiusX() )){return hasCollidedYAxis(entity1, entity2);}
        }
        else{
            if ( (entity1.getPosition().getX() - entity1.getHitBoxRadiusX() ) <
                    (entity2.getPosition().getX() + entity2.getHitBoxRadiusX() )){return hasCollidedYAxis(entity1, entity2);}
        }
        return false;
    }

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

    private void removeDead() {
        removeDeadFromList(friendlies.iterator());
        removeDeadFromList(enemies.iterator());
        removeDeadFromList(projectiles.iterator());
    }

    private void removeDeadFromList(Iterator<? extends MovableEntity> list){
        while (list.hasNext()){
            if (list.next().getIsDead()) {list.remove();} // Check that it removes correct object
        }
    }
}