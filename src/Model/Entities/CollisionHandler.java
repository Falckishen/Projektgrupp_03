package Model.Entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.OnTick;
import Model.Position;

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
        checkCollisionProjectileNonLivingObjects();

        checkCollisionWithNonLivingObjects(enemies.iterator());
        checkCollisionWithNonLivingObjects(friendlies.iterator());
        removeDead(); //TODO make sure to remove from tickObervers
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
                    e.collidedWithFriendly();
                }
            }
        }
    }

    private void checkCollisionEnemyEnemy() {
        ArrayList<Position> entitiesCollidedWithMe = new ArrayList<>();
        Enemy e;
        Iterator<Enemy> itEnemies = enemies.iterator();
        while(itEnemies.hasNext()){ // don't use for-loop (not as accurate)
            e = itEnemies.next();
            addCollidedEnemiesPositionToList(entitiesCollidedWithMe, e);

            if (!entitiesCollidedWithMe.isEmpty()){
                e.collidedWithEnemy(entitiesCollidedWithMe.iterator());
                entitiesCollidedWithMe.clear();
            }
        }
    }

    private void addCollidedEnemiesPositionToList(List<Position> entitiesCollidedWithMe, Entity me){
        Entity e;
        Iterator<Enemy> itEnemies = enemies.iterator();
        while(itEnemies.hasNext()){ // don't use for-loop (not as accurate)
            e = itEnemies.next();
            if (me != e){
                if(hasCollided(me, e)){
                    entitiesCollidedWithMe.add(e.getPosition());
                }
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
     * checks for collision between projectiles and non-living objects.
     * And calls corresponding collision method for projectiles that has collided.
     */
    private void checkCollisionProjectileNonLivingObjects(){
        Entity n;
        Projectile p;

        Iterator<Entity> itNonLivingObjects = nonLivingObjects.iterator();
        while(itNonLivingObjects.hasNext()){
            n = itNonLivingObjects.next();

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
     * Takes an Iterator in which it checks all MovableEntities against all non-living objects to check for collisions.
     * @param movableEntityIterator which iterator of any subtype of MovableEntity is used.
     */
    private void checkCollisionWithNonLivingObjects(Iterator<? extends MovableEntity> movableEntityIterator){
        MovableEntity e;
        Direction pushedDirection;
        while (movableEntityIterator.hasNext()){
            e = movableEntityIterator.next();
            List<Entity> collidedObjects = getListOfCollidedWithNonLiving(e);
            if (collidedObjects.isEmpty()){
                continue;
            } else if (collidedObjects.size() == 1){
                pushedDirection = whichDirectionAmIPushed(e, collidedObjects.get(0));
            } else {
                Iterator<Entity> collidedObjectsIterator = collidedObjects.iterator();
                ArrayList<Direction> directionsPushed = new ArrayList<>();
                while (collidedObjectsIterator.hasNext()){
                    directionsPushed.add(whichDirectionAmIPushed(e, collidedObjectsIterator.next()));
                }
                pushedDirection = resultingPushedDirection(directionsPushed.iterator());
            }
            if (pushedDirection != null){
                e.collidedWithNonLiving(pushedDirection);
            }
        }
    }

    private List<Entity> getListOfCollidedWithNonLiving(Entity me){
        Entity n;
        ArrayList<Entity> entitiesCollidedWith = new ArrayList<>();
        Iterator<Entity> itEntities = nonLivingObjects.iterator();
        while(itEntities.hasNext()){ // don't use for-loop (not as accurate)
            n = itEntities.next();
            if (me != n){
                if(hasCollided(me, n)){
                    entitiesCollidedWith.add(n);
                }
            }
        }
        return entitiesCollidedWith;
    }

    private Direction whichDirectionAmIPushed(Entity me, Entity object){
        Direction xAxis = pushedDirectionXAxis(me, object);
        Direction yAxis = pushedDirectionYAxis(me, object);

        if (xAxis == null && yAxis == null) {
            return directionTowardsMiddle(me.getPosition());
        } else if (xAxis == null){
            return yAxis;
        } else if (yAxis == null){
            return xAxis;
        } else {
            return calculateDiagonalPushedDirection(xAxis, yAxis);
        }
    }

    private Direction pushedDirectionXAxis(Entity me, Entity object){
        int objectHitboxLeft = object.getPosition().getX() - object.getHitBoxRadiusX();
        int objectHitboxRight = object.getPosition().getX() + object.getHitBoxRadiusX();

        int myHitboxLeft = me.getPosition().getX() - me.getHitBoxRadiusX();
        int myHitboxRight = me.getPosition().getX() + me.getHitBoxRadiusX();

        if ( objectHitboxLeft < myHitboxLeft && myHitboxRight < objectHitboxRight){
            return null; // not pushed along the x-axis
        } else if (!(objectHitboxLeft < myHitboxLeft) && !(myHitboxRight < objectHitboxRight)) {
            return null; // not pushed along the x-axis
        }
        else if (objectHitboxLeft < myHitboxLeft){ //crashed on left side.
            return Direction.RIGHT;
        } else { // crashed on right side
            return Direction.LEFT;
        }

    }

    private Direction pushedDirectionYAxis(Entity me, Entity object){
        int objectHitboxUp = object.getPosition().getY() + object.getHitBoxRadiusY();
        int objectHitboxDown = object.getPosition().getY() - object.getHitBoxRadiusY();

        int myHitboxUp = me.getPosition().getY() + me.getHitBoxRadiusY();
        int myHitboxDown = me.getPosition().getY() - me.getHitBoxRadiusY();

        if ( objectHitboxDown < myHitboxDown && myHitboxUp < objectHitboxUp){
            return null; // not pushed along the y-axis
        } else if (!(objectHitboxDown < myHitboxDown) && !(myHitboxUp < objectHitboxUp)){
            return null; // not pushed along the y-axis
        }
        else if (objectHitboxDown < myHitboxDown){ //crashed below. //Y-axis is inverted => crashed above
            return Direction.DOWN;
        } else { // crashed above //Y-axis is inverted => crashed below
            return Direction.UP;
        }

    }

    private Direction calculateDiagonalPushedDirection(Direction xAxis, Direction yAxis){
        if (xAxis == Direction.LEFT){
            if (yAxis == Direction.UP){
                return Direction.LEFT_UP;
            } else {
                return Direction.LEFT_DOWN;
            }
        } else {
            if (yAxis == Direction.UP){
                return Direction.RIGHT_UP;
            } else {
                return Direction.RIGHT_DOWN;
            }
        }
    }

    private Direction directionTowardsMiddle(Position myPosition){
        //TODO WARNING Very closely related to code in enemy
        double deltaX = myPosition.getX();
        double deltaY = myPosition.getY() *-1; //fix inverted axis
        Direction xAxis;
        Direction yAxis;
        if (deltaX > 0){
            xAxis = Direction.LEFT;
        } else if (deltaX < 0){
            xAxis = Direction.RIGHT;
        } else {
            xAxis = null;
        }
        if (deltaY > 0){
            yAxis = Direction.DOWN;
        } else if (deltaY < 0){
            yAxis = Direction.UP;
        } else {
            yAxis = null;
        }
        double hyp = (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
        if (hyp/2 < Math.abs(deltaX) && hyp/2 < Math.abs(deltaY)){
            return calculateDiagonalPushedDirection(xAxis, yAxis);
        } else if (hyp/2 > Math.abs(deltaX)){
            return yAxis;
        } else {
            return xAxis;
        }
    }

    private Direction resultingPushedDirection(Iterator<Direction> directionsPushed){
        Direction resultingDirection = null;
        int directionCounter = 0;
        int nextDirectionCount;
        int difference;

        while (directionsPushed.hasNext()) {
            if (resultingDirection == null) {
                resultingDirection = directionsPushed.next();
                directionCounter = directionToInt(resultingDirection);
            }
            if (directionsPushed.hasNext()) {
                nextDirectionCount = directionToInt(directionsPushed.next());

                if (directionCounter - nextDirectionCount >= -4 && directionCounter - nextDirectionCount <= 4) {
                    difference = directionCounter - nextDirectionCount; //neg == counterclockwise, pos == clockwise
                } else {
                    switch (directionCounter - nextDirectionCount) {
                        case -5 -> difference = 3;
                        case -6 -> difference = 2;
                        case -7 -> difference = 1;
                        case 5 -> difference = -3;
                        case 6 -> difference = -2;
                        case 7 -> difference = -1;
                        default -> difference = 0;
                    }
                }

                if (difference == 0) {
                    continue;
                }
                if (difference == 1 || difference == -1) {
                    if (directionCounter % 2 == 0) {
                        directionCounter += difference;
                    }
                }
                if (difference == 2 || difference == -2) {
                    directionCounter += (difference / 2);
                }
                if (difference == 3 || difference == -3) {
                    if (difference < 0) {
                        directionCounter += 1;
                    } else {
                        directionCounter -= 1;
                    }
                }
                if (difference == 4 || difference == -4) {
                    resultingDirection = null;
                    directionCounter = 0;
                }

                if (resultingDirection != null) {
                    if (directionCounter < 1) {
                        directionCounter += 8;
                    }
                    if (directionCounter > 8) {
                        directionCounter -= 8;
                    }
                }
            }
        }

        return intToDirection(directionCounter);
    }

    /**
     * Transforms the direction to the corresponding int value.
     * <p>
     * 4 --- 3 --- 2 <br />
     * |           | <br />
     * 5     0     1 <br />
     * |           | <br />
     * 6 --- 7 --- 8 <br />
     * number to direction chart
     * </p>
     * @param direction the inputted direction.
     * @return the corresponding int that represents that direction.
     */
    private int directionToInt(Direction direction){
        switch (direction) {
            case RIGHT -> {return 1;}
            case RIGHT_UP -> {return 2;}
            case UP -> {return 3;}
            case LEFT_UP -> {return 4;}
            case LEFT -> {return 5;}
            case LEFT_DOWN -> {return 6;}
            case DOWN -> {return 7;}
            case RIGHT_DOWN -> {return 8;}
            default -> {return 0;}
        }
    }

    /**
     * transforms numbers back to directions with regard to the inverted y-axis
     * <p>
     * 6 --- 7 --- 8 <br />
     * |           | <br />
     * 5     0     1 <br />
     * |           | <br />
     * 4 --- 3 --- 2 <br />
     * number to direction chart
     * </p>
     * @param directionCounter the number which stands for the new direction.
     * @return the new direction, or null if you're not pushed anywhere.
     */
    private Direction intToDirection(int directionCounter){
        switch (directionCounter){
            case 1 -> {return Direction.RIGHT;}
            case 2 -> {return Direction.RIGHT_DOWN;}
            case 3 -> {return Direction.DOWN;}
            case 4 -> {return Direction.LEFT_DOWN;}
            case 5 -> {return Direction.LEFT;}
            case 6 -> {return Direction.LEFT_UP;}
            case 7 -> {return Direction.UP;}
            case 8 -> {return Direction.RIGHT_UP;}
            default -> {return null;}
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