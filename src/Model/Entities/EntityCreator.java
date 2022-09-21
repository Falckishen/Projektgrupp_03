package Model.Entities;

import Model.OnTick;
import Model.Weapons.Weapon;
import Utilities.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityCreator implements AddProjectile, AddEnemy, AddFriendly {
    List<Enemy> enemies;
    List<Friendly> friendlies;
    List<Projectile> projectiles;
    List<OnTick> tickObservers;

    public EntityCreator(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles, List<OnTick> tickObservers){
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.projectiles = projectiles;
        this.tickObservers= tickObservers;
        addCollisionHandler(enemies, friendlies, projectiles);
    }

    public EntityCreator(){
        this.enemies = new ArrayList<Enemy>();
        this.friendlies = new ArrayList<Friendly>();
        this.projectiles = new ArrayList<Projectile>();
        this.tickObservers = new ArrayList<OnTick>();
        addCollisionHandler(enemies, friendlies, projectiles);
    }

    private void addCollisionHandler(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles){
        tickObservers.add(new CollisionHandler(friendlies, enemies, projectiles));
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

    public boolean isAnyEnemiesAlive() {
        return !enemies.isEmpty();
    }

    /*--------------------------------------- AddEnemy (used by Game class) ----------------------------------------*/

    @Override
    public void createMonster(Player p) {
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
}
