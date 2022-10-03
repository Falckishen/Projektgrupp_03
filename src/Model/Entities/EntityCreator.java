package Model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.OnTick;
import Model.Weapons.WeaponFactory;
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
        this.worldMapRadius = worldMapRadius;
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

    @Override
    public void createMonster() {
         Random rand = new Random();
         int spawnX = 0;
         int spawnY = 0;
         while(true){
             boolean toClose = false;
             spawnX = rand.nextInt(worldMapRadius);
             if (spawnX % 2 == 0) {
                 spawnX = spawnX *(-1);
             }

             if (friendlies.isEmpty()) {
                 break;
             } else {
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
         while(true){
             boolean toClose = false;
             spawnY = rand.nextInt(worldMapRadius);
             if (spawnY % 2 == 0) {
                 spawnY = spawnY *(-1);
             }

             if (friendlies.isEmpty()) {
                 break;
             } else {
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




        int temp1 = 0; // TODO fixa random startvärde (får dock inte spawna på player)
        int temp2 = 0; // TODO fixa random startvärde (får dock inte spawna på player)
        Monster m = new Monster(spawnX, spawnY, friendlies);
        enemies.add(m);
        tickObservers.add(m);
    }

    /*----------------------------------- AddFriendly (used by Game class) -----------------------------------------*/

    @Override
    public void createPlayer(int coordX, int coordY, List<Integer> keyboardInputs) {
        Player p = new Player(coordX, coordY, keyboardInputs);
        p.getNewWeapon(WeaponFactory.getGun(this, p.getPosition()));
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
    public void createWorldBorderWalls() {
        int wallThicknessRadius = 10;
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldMapRadius,(worldMapRadius*(-1)), 0)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldMapRadius,worldMapRadius,0)); //x right
        nonLivingObjects.add(new Wall(worldMapRadius, wallThicknessRadius,0,worldMapRadius)); //y top
        nonLivingObjects.add(new Wall(worldMapRadius, wallThicknessRadius,0,(worldMapRadius*(-1)) )); //y bottom
    }
}