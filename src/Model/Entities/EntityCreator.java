package Model.Entities;

import Model.OnTick;
import Utilities.*;

import java.util.ArrayList;
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

    }

    //used for testing
    public EntityCreator(){
        this.enemies = new ArrayList<Enemy>();
        this.friendlies = new ArrayList<Friendly>();
        this.projectiles = new ArrayList<Projectile>();
        this.tickObservers = new ArrayList<OnTick>();
    }

    public List<OnTick> getTickObservers() {
        return tickObservers;
    }

    @Override
    public void createMonster() {
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 10;
        int temp4 = 10;
        int temp5 = 5;
        enemies.add(new Monster(temp1, temp2, temp3, temp4, temp5));
    }

    @Override
    public Monster createMonster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius, int speed) {
        Monster m = new Monster(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius, speed);
        enemies.add(m);
        return m;
    }


    @Override
    public Player createPlayer(int coordX, int coordY, List keyboardInputs) {
        Player p = new Player(coordX, coordY, keyboardInputs);
        friendlies.add(p);
        return p;
    }


    @Override
    public void createSimpleProjectile(Direction direction, int velocity, int life) {
        projectiles.add(new SimpleProjectile(direction, velocity, life));
    }

    @Override
    public void createSimpleProjectile(double direction, int velocity, int life) {

    }
}
