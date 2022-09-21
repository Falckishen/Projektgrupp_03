package Model.Entities;


import Model.OnTick;

import java.util.Iterator;

class CollisionHandler implements OnTick {
    Iterable<Friendly> friendlies;
    Iterable<Enemy> enemies;
    Iterable<Projectile> projectiles;

    CollisionHandler(Iterable<Friendly> friendlies, Iterable<Enemy> enemies, Iterable<Projectile> projectiles){
        this.friendlies = friendlies;
        this.enemies = enemies;
        this.projectiles = projectiles;
    }

    @Override
    public void doOnTick() {
        checkCollisionPlayerEnemy();
        checkCollisionEnemyEnemy();
        checkCollisionEnemyProjectile();
        removeDead();
    }

    private void checkCollisionPlayerEnemy() {
        Iterator<Friendly> itFriendlies = friendlies.iterator();
        Iterator<Enemy> itEnemies = enemies.iterator();
        Friendly p;
        Enemy e;
        while(itFriendlies.hasNext()){
            p = itFriendlies.next();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(p, e)){
                    p.CollidedWithEnemy(e.getAttackPower());
                    e.collidedWithFriendly(p.getCurrentPosition());
                }
            }
        }
    }

    private void checkCollisionEnemyEnemy() {
        Iterator<Enemy> itEnemies1 = enemies.iterator();
        Iterator<Enemy> itEnemies2 = enemies.iterator();
        Enemy e1;
        Enemy e2;
        while(itEnemies1.hasNext()){
            e1 = itEnemies1.next();
            while(itEnemies2.hasNext()){
                e2 = itEnemies2.next();
                if(hasCollided(e1, e2)){
                    e1.collidedWIthEnemy(e2.getCurrentPosition());
                    e2.collidedWIthEnemy(e1.getCurrentPosition());
                }
            }
        }
    }

    private void checkCollisionEnemyProjectile() {
        Iterator<Projectile> itProjectiles = projectiles.iterator();
        Iterator<Enemy> itEnemies = enemies.iterator();
        Projectile p;
        Enemy e;
        while(itProjectiles.hasNext()){
            p = itProjectiles.next();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(p, e)){
                    p.CollidedWithEnemy();
                    e.collidedWithProjectile(p.getAttackPower());
                }
            }
        }
    }

    private boolean hasCollided(Entity entity1, Entity entity2) {
        //collided on x-axis
        if (entity1.getCurrentPosition().getX() < entity2.getCurrentPosition().getX()) {//is entity1 to the left of entity2
            if ( (entity1.getCurrentPosition().getX() + entity1.getHitBoxRadiusX() ) >
                    (entity2.getCurrentPosition().getX() - entity2.getHitBoxRadiusX() )){return true;}
        }
        else{
            if ( (entity1.getCurrentPosition().getX() - entity1.getHitBoxRadiusX() ) <
                    (entity2.getCurrentPosition().getX() + entity2.getHitBoxRadiusX() )){return true;}
        }

        //collided on y-axis (Also simplified to return result)
        if (entity1.getCurrentPosition().getY() < entity2.getCurrentPosition().getY()) {//is entity1 lower than entity2
            return (entity1.getCurrentPosition().getY() + entity1.getHitBoxRadiusY()) >
                    (entity2.getCurrentPosition().getY() - entity2.getHitBoxRadiusY());
        }
        else{
            return (entity1.getCurrentPosition().getY() - entity1.getHitBoxRadiusY()) <
                    (entity2.getCurrentPosition().getY() + entity2.getHitBoxRadiusY());
        }
    }

/*    private Direction hasCollided(Entity entity1, Entity entity2) {
        //collided on x-axis
        if (entity1.getCurrentPosition().getX() < entity2.getCurrentPosition().getX()) {//is entity1 to the left of entity2
            if ( (entity1.getCurrentPosition().getX() + entity1.getHitboxRadiusX() ) >
                    (entity2.getCurrentPosition().getX() - entity2.getHitboxRadiusX() )){return Direction.;}
        }
        else{
            if ( (entity1.getCurrentPosition().getX() - entity1.getHitboxRadiusX() ) <
                    (entity2.getCurrentPosition().getX() + entity2.getHitboxRadiusX() )){return Direction.;}
        }

        //collided on y-axis (Also simplified to return result)
        if (entity1.getCurrentPosition().getY() < entity2.getCurrentPosition().getY()) {//is entity1 lower than entity2
            if ( (entity1.getCurrentPosition().getY() + entity1.getHitboxRadiusY()) >
                    (entity2.getCurrentPosition().getY() - entity2.getHitboxRadiusY()) ) {return Direction.;}
        }
        else{
            if ( (entity1.getCurrentPosition().getY() - entity1.getHitboxRadiusY()) <
                    (entity2.getCurrentPosition().getY() + entity2.getHitboxRadiusY()) ) {return Direction.;}
        }
        return null;
    }*/


    private void removeDead() {
        removeDeadFromList(friendlies.iterator());
        removeDeadFromList(enemies.iterator());
        removeDeadFromList(projectiles.iterator());
    }

    private void removeDeadFromList(Iterator<? extends Entity> list){
        while (list.hasNext()){
            if (list.next().getIsDead()) {list.remove();} // Check that it removes correct object
        }
    }
}
