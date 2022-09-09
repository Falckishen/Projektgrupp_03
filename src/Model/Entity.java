package Model;

public abstract class Entity implements OnTick{
    private int id;
    private Position coordinate;
    private int hitboxRadiusX;
    private int hitboxRadiusY;
    private Direction direction;
    private int velocity;


    Entity(int coordX, int coordY, int hitboxRadiusX, int hitboxRadiusY){
        this.coordinate = new Position(coordX, coordY);
        this.hitboxRadiusX = hitboxRadiusX;
        this.hitboxRadiusY = hitboxRadiusY;

        this.direction = Direction.LEFT;
        this.velocity = 10;
    }

    Position getCurrentPosition() {
        return coordinate;
    }

    Direction getDirection() {
        return direction;
    }

    public int getVelocity() {
        return velocity;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
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
