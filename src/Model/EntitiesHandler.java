package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EntitiesHandler {
    private ArrayList<Entity> completeEntityList = new ArrayList<Entity>();
//    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

 /*   void addPlayer(){
        Player p = new Player();
        players.add(p);
        completeEntityList.add(p);
    }*/

    void addEnemy(String type){
        if (type == "Monster"){
            addMonster();
        }
    }

    private void addMonster(){
 /*       Monster m = new Monster();
        enemies.add((Enemy) m);
        completeEntityList.add((Entity) m);
    */}

    void updateEntities(){

    }

    private void checkCollisionForMe(Entity self){
        for (Entity e: completeEntityList){


        }
    }

    private void updateEnemies(){

    }

    private void updatePlayers(){

    }


}