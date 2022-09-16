package Model.Entities;


import Model.OnTick;

import java.util.Iterator;

public class CollisionHandler implements OnTick {
    Iterable<Player> players;
    Iterable<Enemy> enemies;
    Iterable<Projectile> projectiles;

    CollisionHandler(Iterable players, Iterable enemies, Iterable projectiles){
        this.players = players;
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
        Iterator<Player> itPlayers = players.iterator();
        Iterator<Enemy> itEnemies = enemies.iterator();
        Player p;
        Enemy e;
        while(itPlayers.hasNext()){
            p = itPlayers.next();
            while(itEnemies.hasNext()){
                e = itEnemies.next();
                if(hasCollided(p, e)){
                    p.CollidedWithEnemy(e.getAttackPower());
                    e.collidedWithPlayer(p.getCurrentPosition());
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
    }
}
