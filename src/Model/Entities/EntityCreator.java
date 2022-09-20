package Model.Entities;

import Model.OnTick;
import Model.Weapons.Weapon;
import Utilities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //used for testing (not connected to collision)
    public EntityCreator(){
        this.enemies = new ArrayList<Enemy>();
        this.friendlies = new ArrayList<Friendly>();
        this.projectiles = new ArrayList<Projectile>();
        this.tickObservers = new ArrayList<OnTick>();
        addCollisionHandler(enemies, friendlies, projectiles);
    }
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

    public Boolean getEnemiesExist() {
        return (!enemies.isEmpty());
    }

    private void addCollisionHandler(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles){
        tickObservers.add(new CollisionHandler(friendlies, enemies, projectiles));
    }

    //temporary
    @Override
    public void createMonster() {
       /* Random rand = new Random();
        int worldWidthRadius = rand.nextInt(WorldWidthRadius);
        if (worldWidthRadius % 2 == 0){
            worldWidthRadius = worldWidthRadius *(-1);}
        int worldHeightRadius = rand.nextInt(WorldHeightRadius);
        if (worldHeightRadius % 2 == 0){
            worldHeightRadius = worldHeightRadius *(-1);} */

        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 10;
        int temp4 = 10;
        int temp5 = 5;
        int temp6 = 1;
        Monster m = new Monster(temp1, temp2, temp3, temp4, temp5, temp6);
        enemies.add(m);
        tickObservers.add(m);
    }

    @Override
    public void createMonster(Player p) {
       /* Random rand = new Random();
        int worldWidthRadius = rand.nextInt(WorldWidthRadius);
        if (worldWidthRadius % 2 == 0){
            worldWidthRadius = worldWidthRadius *(-1);}
        int worldHeightRadius = rand.nextInt(WorldHeightRadius);
        if (worldHeightRadius % 2 == 0){
            worldHeightRadius = worldHeightRadius *(-1);} */

        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 10;
        int temp4 = 10;
        int temp5 = 5;
        int temp6 = 1;
        Monster m = new Monster(temp1, temp2, temp3, temp4, temp5, temp6);
        m.setCurrentPlayer(p);
        enemies.add(m);
        tickObservers.add(m);
    }



    // TESTING PURPOSES
    @Override
    public Monster createMonster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius, int speed, int attackPower) {
        Monster m = new Monster(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius, speed, attackPower);
        enemies.add(m);
        tickObservers.add(m);
        return m;
    }


    @Override
    public Player createPlayer(int coordX, int coordY, List<Integer> keyboardInputs, Weapon weapon) {
        Player p = new Player(coordX, coordY, keyboardInputs, weapon);
        friendlies.add(p);
        tickObservers.add(p);
        return p;
    }


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
