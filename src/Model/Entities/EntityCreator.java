package Model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.OnTick;
import Model.Position;
import Model.Weapons.WeaponFactory;

/**
 * @author Ida Altenstedt
 */
public class EntityCreator implements AddProjectile, AddEnemy, AddFriendly, AddNonLivingObjects {

    private final List<Enemy> enemies;
    private final List<Friendly> friendlies;
    private final List<Projectile> projectiles;
    private final List<OnTick> tickObservers;
    private final List<Entity> nonLivingObjects;
    private final int worldMapRadius;
    private final int difficulty;

    /*
    public EntityCreator(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles,
                        List<OnTick> tickObservers, List<Entity> nonLivingObjects){
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.projectiles = projectiles;
        this.tickObservers= tickObservers;
        this.nonLivingObjects = nonLivingObjects;
        addCollisionHandler();
    }
    */

    public EntityCreator(int worldMapRadius, int difficulty) {
        this.enemies = new ArrayList<>();
        this.friendlies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.tickObservers = new ArrayList<>();
        this.nonLivingObjects = new ArrayList<>();
        this.worldMapRadius = worldMapRadius;
        this.difficulty = difficulty;
        addCollisionHandler();
    }

    private void addCollisionHandler(){
        tickObservers.add(new CollisionHandler(friendlies, enemies, projectiles, nonLivingObjects));
    }

    /*------------------------------------------------ Getters ------------------------------------------------------*/

    //temporary used for the testing constructor
    public List<OnTick> getTickObservers() {
        return tickObservers;
    }

    public List<MovableEntity> getMovableEntities(){
        ArrayList<MovableEntity> allMovableEntities = new ArrayList<>();
        allMovableEntities.addAll(enemies);
        allMovableEntities.addAll(friendlies);
        allMovableEntities.addAll(projectiles);
        return allMovableEntities;
    }

    public List<? extends MovableEntity> getEnemies() {
        return enemies;
    }

    public List<? extends MovableEntity> getFriendlies() {
        return friendlies;
    }

    public List<? extends MovableEntity> getProjectiles() {
        return projectiles;
    }

    public List<? extends Entity> getNonLivingObjects(){
        return nonLivingObjects;
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

    public Position getPlayerPosition(){
        for (Friendly f : friendlies){
            if (f.getEntityType() == EntityType.player){
                return f.getPosition();
            }
        }
        return null;
    }

    /*--------------------------------------- AddEnemy (used by Game class) ----------------------------------------*/

    /**
     * checks for a random x coordinate that isn't too close to the player.
     * @return int x
     */
    private int getXCoordinateInWorld(){
        Random rand = new Random();
        int spawnX = 0;
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
        int spawnY = 0;
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

    @Override
    public void createPlayer(int x, int y, List<Integer> keyboardInputs,
                List<Integer> weaponKeyboardInputs) {
        Player p = new Player(x, y, keyboardInputs);

        p.getNewWeapon(WeaponFactory.getGun(this, p.getPosition(), weaponKeyboardInputs));
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

    @Override
    public void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY) {
        nonLivingObjects.add(new Wall(wallRadiusX, wallRadiusY, positionX, positionY));
    }

   /* @Override
    public void createWorldBorderWalls() {
        int wallThicknessRadius = 10;
        int wallSectionRadius = 100; //how wide the wall parts of the border are.

        int wallWidth = wallSectionRadius *2;
        int currentWallPosition = worldMapRadius*(-1) + wallSectionRadius - (wallThicknessRadius*2); //makes clean corner
        for (; currentWallPosition < worldMapRadius- wallSectionRadius +(wallThicknessRadius*2);
             currentWallPosition = currentWallPosition + wallWidth){

            createBorderWallSegment(wallThicknessRadius, wallSectionRadius, currentWallPosition);
        }
        currentWallPosition = worldMapRadius- wallSectionRadius +(wallThicknessRadius*2); //Make last clean corner
        createBorderWallSegment(wallThicknessRadius, wallSectionRadius, currentWallPosition);
    }

    private void createBorderWallSegment(int wallThicknessRadius, int wallSectionRadius, int currentWallPosition){
        int distanceFromCentre = worldMapRadius+wallThicknessRadius; //world radius + wall radius thickness
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallSectionRadius,(distanceFromCentre*(-1)), currentWallPosition)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallSectionRadius,distanceFromCentre,currentWallPosition)); //x right
        nonLivingObjects.add(new Wall(wallSectionRadius, wallThicknessRadius,currentWallPosition,distanceFromCentre)); //y top
        nonLivingObjects.add(new Wall(wallSectionRadius, wallThicknessRadius,currentWallPosition,(distanceFromCentre*(-1)) )); //y bottom
    }*/

    @Override
    public void createWorldBorderWalls() {
        int wallThicknessRadius = 70;
        int distanceFromCentre = worldMapRadius+wallThicknessRadius;
        int wallLengthRadius = worldMapRadius+ wallThicknessRadius*2;
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallLengthRadius,(distanceFromCentre*(-1)), 0)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, wallLengthRadius,distanceFromCentre,0)); //x right
        nonLivingObjects.add(new Wall(wallLengthRadius, wallThicknessRadius,0,distanceFromCentre)); //y top
        nonLivingObjects.add(new Wall(wallLengthRadius, wallThicknessRadius,0,(distanceFromCentre*(-1)) )); //y bottom
    }
}