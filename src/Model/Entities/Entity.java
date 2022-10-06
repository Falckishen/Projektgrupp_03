package Model.Entities;

import Utilities.EntityType;
import Utilities.Position;

/**
 * @author Ida Altenstedt
 */
public abstract class Entity {

    private final EntityType entityType;
    private final int hitBoxRadiusX;
    private final int hitBoxRadiusY;
    private Position position;
    private boolean isDead;

    Entity(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y){
        this.entityType = entityType;
        this.hitBoxRadiusX = hitBoxRadiusX;
        this.hitBoxRadiusY = hitBoxRadiusY;
        this.position = new Position(x, y);
        this.isDead = false;
    }

    public EntityType getEntityType(){
        return entityType;
    }

    public Position getPosition() {
        return position;
    }

    public int getHitBoxRadiusX() {
        return hitBoxRadiusX;
    }

    public int getHitBoxRadiusY() {
        return hitBoxRadiusY;
    }

    protected void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    protected boolean getIsDead() {
        return isDead;
    }
}