package Model.Entities;

import java.util.ArrayList;
import java.util.List;
import Model.OnTick;
import Model.Weapons.Weapon;
import Utilities.*;

public class EntityCreator implements AddProjectile, AddEnemy, AddFriendly, AddNonLivingObjects {

    private final List<Enemy> enemies;
    private final List<Friendly> friendlies;
    private final List<Projectile> projectiles;
    private final List<OnTick> tickObservers;
    private final List<Entity> nonLivingObjects;
    private final int worldMapRadius;

    /*
    public EntityCreator(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles, List<OnTick> tickObservers, List<Entity> nonLivingObjects){
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.projectiles = projectiles;
        this.tickObservers= tickObservers;
        this.nonLivingObjects = nonLivingObjects;
        addCollisionHandler();
    }
    */

    public EntityCreator(int worldMapRadius) {
        this.enemies = new ArrayList<>();
        this.friendlies = new ArrayList<>();
        this.projectiles = new ArrayList<>();
        this.tickObservers = new ArrayList<>();
        this.nonLivingObjects = new ArrayList<>();
        addCollisionHandler();
        this.worldMapRadius = worldMapRadius;
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

    /*--------------------------------------- AddEnemy (used by Game class) ----------------------------------------*/

    @Override
    public void createMonster() {
        /* Random rand = new Random();
        int worldWidthRadius = rand.nextInt(WorldWidthRadius);
        if (worldWidthRadius % 2 == 0){
            worldWidthRadius = worldWidthRadius *(-1);}
        int worldHeightRadius = rand.nextInt(WorldHeightRadius);
        if (worldHeightRadius % 2 == 0){
            worldHeightRadius = worldHeightRadius *(-1);} */

        int temp1 = 0; // TODO fixa random startvärde (får dock inte spawna på player)
        int temp2 = 0; // TODO fixa random startvärde (får dock inte spawna på player)
        Monster m = new Monster(temp1, temp2, friendlies);
        enemies.add(m);
        tickObservers.add(m);
    }

    /*----------------------------------- AddFriendly (used by Game class) -----------------------------------------*/

    @Override
    public void createPlayer(int coordX, int coordY, List<Integer> keyboardInputs, Weapon weapon) {
        Player p = new Player(coordX, coordY, keyboardInputs, weapon);
        friendlies.add(p);
        tickObservers.add(p);
    }

    /*----------------------------------- AddProjectile (used by Weapon class) --------------------------------------*/

    @Override
    public void createSimpleProjectile(Position position, Direction direction, int velocity, int life, int attackPower) {
        SimpleProjectile p = new SimpleProjectile(position.getX(), position.getY(), direction, velocity, life, attackPower);
        projectiles.add(p);
        tickObservers.add(p);
    }

    @Override
    public void createSimpleProjectile(Position position, double direction, int velocity, int life, int attackPower) {

    }

    /*----------------------------- AddNonLivingObjects (called then game created) ---------------------------------*/

    @Override
    public void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY) {
        nonLivingObjects.add(new Wall(wallRadiusX, wallRadiusY, positionX, positionY));
    }

    @Override
    public void createWorldBorderWalls(int worldRadiusX, int worldRadiusY) {
        int wallThicknessRadius = 10;
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldRadiusY,(worldRadiusX*(-1)), 0)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldRadiusY,worldRadiusX,0)); //x right
        nonLivingObjects.add(new Wall(worldRadiusX, wallThicknessRadius,0,worldRadiusY)); //y top
        nonLivingObjects.add(new Wall(worldRadiusX, wallThicknessRadius,0,(worldRadiusY*(-1)) )); //y bottom
    }
}