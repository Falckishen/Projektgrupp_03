package Model.Entities;

import Model.OnTick;

import java.util.ArrayList;
import java.util.Objects;

public class EntitiesHandler {
    private ArrayList<Entity> completeEntityList = new ArrayList<Entity>();
    private ArrayList<OnTick> completeList = new ArrayList<OnTick>();
    private Player player;

    /*
    void addPlayer(){
        Player p = new Player();
        completeList.add((Action)p);
        completeEntityList.add((Entity)p);
    }*/

    void addEnemy(String type){
        if (Objects.equals(type, "Monster")){
            addMonster();
        }
    }

    private void addMonster() {
        /*
        Monster m = new Monster();
        completeList.add((Action) m);
        completeEntityList.add((Entity) m);
        */
    }

    void updateEntities(){
        for (OnTick entity: completeList) {
            entity.doOnTick();
        }
    }

    private boolean isPositionOccupied(Position position){
        for (Entity entity: completeEntityList){
            if (entity.getCurrentPosition().getX() == position.getX() && entity.getCurrentPosition().getY() == position.getY()) {
                return true;
            }
        }
        return false;
    }

    public Position getPlayerPosition(){
        return player.getCurrentPosition();
    }
}