package Model.Entities;

import Model.OnTick;
import Model.Weapons.Weapon;
import Utilities.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityCreator implements AddProjectile, AddEnemy, AddFriendly, AddNonLivingObjects {
    List<Enemy> enemies;
    List<Friendly> friendlies;
    List<Projectile> projectiles;
    List<OnTick> tickObservers;
    List<AllObjects> nonLivingObjects;

    public EntityCreator(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles, List<OnTick> tickObservers, List<AllObjects> nonLivingObjects){
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.projectiles = projectiles;
        this.tickObservers= tickObservers;
        this.nonLivingObjects = nonLivingObjects;
        addCollisionHandler();
    }

    public EntityCreator(){
        this.enemies = new ArrayList<Enemy>();
        this.friendlies = new ArrayList<Friendly>();
        this.projectiles = new ArrayList<Projectile>();
        this.tickObservers = new ArrayList<OnTick>();
        this.nonLivingObjects = new ArrayList<AllObjects>();
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

    public List<? extends Entity> getEnemies() {
        return enemies;
    }

    public List<? extends Entity> getFriendlies() {
        return friendlies;
    }

    public List<? extends Entity> getProjectiles() {
        return projectiles;
    }

    public List<? extends AllObjects> getNonLivingObjects(){
        return nonLivingObjects;
    }

    public boolean isAnyEnemiesAlive() {
        return !enemies.isEmpty();
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
    public Player createPlayer(int coordX, int coordY, List<Integer> keyboardInputs, Weapon weapon) {
        Player p = new Player(coordX, coordY, keyboardInputs, weapon);
        friendlies.add(p);
        tickObservers.add(p);
        return p;
    }

    /*----------------------------------- AddProjectile (used by Weapon class) --------------------------------------*/

    @Override
    public void createSimpleProjectile(Direction direction, int velocity, int life, int attackPower) {
        SimpleProjectile p = new SimpleProjectile(direction, velocity, life, attackPower);
        projectiles.add(p);
        tickObservers.add(p);
    }

    @Override
    public void createSimpleProjectile(double direction, int velocity, int life, int attackPower) {

    }

    /*----------------------------- AddNonLivingObjects (called then game created) ---------------------------------*/

    @Override
    public void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY) {
        nonLivingObjects.add(new Wall(wallRadiusX, wallRadiusY, positionX, positionY));
    }

    @Override
    public void createWorldWalls(int worldRadiusX, int worldRadiusY) {
        int wallThicknessRadius = 10;
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldRadiusY,(worldRadiusX*(-1)), 0)); //x left
        nonLivingObjects.add(new Wall(wallThicknessRadius, worldRadiusY,worldRadiusX,0)); //x right
        nonLivingObjects.add(new Wall(worldRadiusX, wallThicknessRadius,0,worldRadiusY)); //y top
        nonLivingObjects.add(new Wall(worldRadiusX, wallThicknessRadius,0,(worldRadiusY*(-1)) )); //y bottom
    }
}
