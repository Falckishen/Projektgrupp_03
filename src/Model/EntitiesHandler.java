package Model;

import java.util.ArrayList;

public class EntitiesHandler {
    private ArrayList<Entity> completeEntityList = new ArrayList<Entity>();
    private ArrayList<OnTick> completeList = new ArrayList<OnTick>();

 /*   void addPlayer(){
        Player p = new Player();
        completeList.add((Action)p);
        completeEntityList.add((Entity)p);
    }*/

    void addEnemy(String type){
        if (type == "Monster"){
            addMonster();
        }
    }

    private void addMonster(){
 /*       Monster m = new Monster();
        completeList.add((Action) m);
        completeEntityList.add((Entity) m);
    */}

    void updateEntities(){
        for (OnTick entity: completeList) {
            entity.doOnTick();
        }
    }

    private boolean isPositionOccupied(Position position){
        for (Entity entity: completeEntityList){
            if (entity.getCoordX() == position.getX() && entity.getCoordY() == position.getY()) {
                return true;
            }
        }
        return false;
    }

}