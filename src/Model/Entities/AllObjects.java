package Model.Entities;

import Utilities.EntityType;

abstract class AllObjects {
    private final EntityType entityType;
    private final int hitBoxRadiusX;
    private final int hitBoxRadiusY;

    private Position coordinate;
    private boolean isDead = false;

    AllObjects(EntityType entityType, int hitBoxRadiusX, int hitBoxRadiusY, int x, int y){
        this.entityType = entityType;
        this.hitBoxRadiusX = hitBoxRadiusX;
        this.hitBoxRadiusY = hitBoxRadiusY;

        this.coordinate = new Position(x, y);
    }

    public EntityType getEntityType(){
        return entityType;
    }

    public Position getPosition() {
        return coordinate;
    }

    protected int getHitBoxRadiusX() {
        return hitBoxRadiusX;
    }

    protected int getHitBoxRadiusY() {
        return hitBoxRadiusY;
    }

    protected void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    protected boolean getIsDead() {
        return isDead;
    }

}
