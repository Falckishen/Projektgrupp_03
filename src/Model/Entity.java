package Model;

public abstract class Entity implements OnTick{
    private int id;
    private Position coordinate;
    private int hitboxRadiusX;
    private int hitboxRadiusY;
    private Direction direction;


    Entity(int coordX, int coordY, int hitboxRadiusX, int hitboxRadiusY){
        this.coordinate = new Position(coordX, coordY);
        this.hitboxRadiusX = hitboxRadiusX;
        this.hitboxRadiusY = hitboxRadiusY;

        this.direction = Direction.LEFT;
    }

    Position getCurrentPosition() {
        return coordinate;
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    protected void setCoordX(int coordX) {
        this.coordinate.setX(coordX);
    }

    protected void setCoordY(int coordY) {
        this.coordinate.setY(coordY);
    }

    protected int getHitboxRadiusX() {
        return hitboxRadiusX;
    }

    protected int getHitboxRadiusY() {
        return hitboxRadiusY;
    }

    abstract void move();
}
