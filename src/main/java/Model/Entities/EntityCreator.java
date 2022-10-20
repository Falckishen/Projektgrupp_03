package Model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Direction;
import Model.EntityType;
import Model.OnTick;
import Model.Position;
import Model.Entities.Weapons.WeaponFactory;

/**
 * @author Ida Altenstedt
 */
public class EntityCreator implements AddProjectile, AddEnemy {

    private final WeaponFactory weaponFactory;
    private final List<Enemy> enemies;
    private final List<Friendly> friendlies;
    private final List<Projectile> projectiles;
    private final List<OnTick> tickObservers;
    private final List<Entity> nonLivingObjects;
    private final int worldMapRadius;
    private final int difficulty;

    public EntityCreator(int worldMapRadius, int difficulty) {
        this.weaponFactory = new WeaponFactory();
        this.enemies = new ArrayList<>();
        this.friendlies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.tickObservers = new ArrayList<>();
        this.nonLivingObjects = new ArrayList<>();
        this.worldMapRadius = worldMapRadius;
        this.difficulty = difficulty;
        CreateCollisionHandler();
    }

    private void CreateCollisionHandler(){
        tickObservers.add(new CollisionHandler(friendlies, enemies, projectiles, nonLivingObjects));
    }

    /*------------------------------------------------ Getters ------------------------------------------------------*/

    public WeaponFactory getWeaponFactory(){
        return weaponFactory;
    }

    //temporary used for the testing constructor
    public List<OnTick> getTickObservers() {
        return tickObservers;
    }

    public List<Entity> getAllEntities(){
        ArrayList<Entity> allEntities = new ArrayList<>();
        allEntities.addAll(enemies);
        allEntities.addAll(friendlies);
        allEntities.addAll(projectiles);
        allEntities.addAll(nonLivingObjects);
        return allEntities;
    }

    /**
     * Returns the player or null if player doesn't exist.
     * @return player or null if player doesn't exist.
     */
    public MovableEntity getPlayer() {
        for (MovableEntity e : friendlies) {
            if (e.getEntityType() == EntityType.player){
                return e;
            }
        }
        return null;
    }

    //used for tests
    public List<? extends MovableEntity> getEnemies() {
        return enemies;
    }
    //used for tests
    public List<? extends MovableEntity> getFriendlies() {
        return friendlies;
    }
    //used for tests
    public List<? extends MovableEntity> getProjectiles() {
        return projectiles;
    }


    public boolean isAnyEnemiesAlive() {
        return !enemies.isEmpty();
    }

    public boolean isPlayerAlive() {
        for (Friendly f : friendlies){
            if (f.getEntityType() == EntityType.player){
                return true;
            }
        }
        return false;
    }

    /*--------------------------------------- AddEnemy (used by Game class) ----------------------------------------*/

    /**
     * checks for a random x coordinate that isn't too close to the player.
     * @return int x
     */
    private int getXCoordinateInWorld(){
        Random rand = new Random();
        int spawnX;
        while(true){
            boolean toClose = false;
            spawnX = rand.nextInt(worldMapRadius-100);
            if (spawnX % 2 == 0) {
                spawnX = spawnX *(-1);
            }

            if (friendlies.isEmpty()) {
                break;
            }
            else {
                for (Entity e: friendlies) {
                    if (e.getPosition().getX() > spawnX - 200 && e.getPosition().getX() < spawnX + 200) {
                        toClose = true;
                        break;
                    }
                }
                if (!toClose){
                    break;
                }
            }
        }
        return spawnX;
    }

    /**
     * checks for a random y coordinate that isn't too close to the player.
     * @return int y
     */
    private int getYCoordinateInWorld(){
        Random rand = new Random();
        int spawnY;
        while(true){
            boolean toClose = false;
            spawnY = rand.nextInt(worldMapRadius-100);
            if (spawnY % 2 == 0) {
                spawnY = spawnY *(-1);
            }
            if (friendlies.isEmpty()) {
                break;
            }
            else {
                for (Entity e: friendlies) {
                    if (e.getPosition().getY() > spawnY - 200 && e.getPosition().getY() < spawnY + 200) {
                        toClose = true;
                        break;
                    }
                }
                if (!toClose){
                    break;
                }
            }
        }
        return spawnY;
    }

    @Override
    public void createWeakMonster() {
        Monster m = new Monster(getXCoordinateInWorld(), getYCoordinateInWorld(), friendlies, 3,
                difficulty, difficulty);
        enemies.add(m);
        tickObservers.add(m);
    }
    /*----------------------------------- (used for Testing) -----------------------------------------*/
    public void createCustomMonster(int x,int y,int speed,int health,int attackPower){
        Monster m = new Monster(x, y, friendlies, speed,
                health, attackPower);
        enemies.add(m);
        tickObservers.add(m);
    }

    /*----------------------------------- AddFriendly (used by Game class) -----------------------------------------*/

    public void createPlayer(int x, int y, List<Integer> keyboardInputs, List<Integer> weaponKeyboardInputs) {
        Player p = new Player(x, y, keyboardInputs);

        p.getNewWeapon(weaponFactory.getGun(this, p.getPosition(), weaponKeyboardInputs));
        friendlies.add(p);
        tickObservers.add(p);
    }

    /*----------------------------------- AddProjectile (used by Weapon class) --------------------------------------*/

    @Override
    public void createSimpleProjectile(Position position, Direction direction, int velocity, int life,
                                       int attackPower) {
        Projectile p = new Projectile(EntityType.simpleProjectile, 10, 10, position.getX(),
                position.getY(), velocity, 1, direction, life, attackPower);
        projectiles.add(p);
        tickObservers.add(p);
    }

    /*----------------------------- AddNonLivingEntities (called when game created) ---------------------------------*/

    /**
     * Creates a wall in a specific place according to inputted parameters.
     * @param positionX the wall's position on the x-axis.
     * @param positionY the wall's position on the y-axis.
     * @param wallRadiusX the wall's radius along the x-axis.
     * @param wallRadiusY the wall's radius along the y-axis.
     */
    public void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY) {
        nonLivingObjects.add(new Wall(wallRadiusX, wallRadiusY, positionX, positionY));
    }

    /**
     * Creates walls according to the size of the world to act as a world Boarder.
     */
    public void createWorldBorderWalls() {
        int wallThicknessRadius = 70;
        int distanceFromCentre = worldMapRadius+wallThicknessRadius;
        int wallLengthRadius = worldMapRadius+ wallThicknessRadius*2;
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallLengthRadius,(distanceFromCentre*(-1)), 0)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallLengthRadius,distanceFromCentre,0)); //x right
        nonLivingObjects.add(new Wall(wallLengthRadius, wallThicknessRadius,0,distanceFromCentre)); //y bottom
        nonLivingObjects.add(new Wall(wallLengthRadius, wallThicknessRadius,0,(distanceFromCentre*(-1)) )); //y top
    }
}