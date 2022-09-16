package Model.Entities;

import Utilities.*;
import Utilities.AddEnemy;
import Utilities.AddFriendly;

import java.util.ArrayList;
import java.util.List;

public class EntityCreator implements AddProjectile, AddEnemy, AddFriendly {
    List<Enemy> enemies;
    List<Friendly> friendlies;
    List<Projectile> projectiles;

    public EntityCreator(List<Enemy> enemies, List<Friendly> friendlies, List<Projectile> projectiles){
        this.enemies = enemies;
        this.friendlies = friendlies;
        this.projectiles = projectiles;
    }

    //used for testing
    public EntityCreator(){
        this.enemies = new ArrayList<Enemy>();
        this.friendlies = new ArrayList<Friendly>();
        this.projectiles = new ArrayList<Projectile>();
    }

    @Override
    public void createEnemy(EntityTypes enemyType) {

    }

    @Override
    public void createMonster() {
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 10;
        int temp4 = 10;
        enemies.add(new Monster(temp1, temp2, temp3, temp4));
    }

    @Override
    public Monster createMonster(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        Monster m = new Monster(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);
        enemies.add(m);
        return m;
    }

    @Override
    public void createFriendly(EntityTypes friendlyType) {

    }

    @Override
    public Player createPlayer(int coordX, int coordY, int hitBoxWidthRadius, int hitBoxHeightRadius, List keyboardInputs) {
        Player p = new Player(coordX, coordY, hitBoxWidthRadius, hitBoxHeightRadius, keyboardInputs);
        friendlies.add(p);
        return p;
    }

    @Override
    public void createProjectile(EntityTypes projectileType, Direction direction, int velocity, int life) {
        projectiles.add(new Projectile(direction, velocity, life));
    }

    @Override
    public void createProjectile(EntityTypes projectileType, double direction, int velocity, int life) {

    }
}
