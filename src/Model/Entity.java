package Model;

public abstract class Entity {
    private int id;
    private int coordX;
    private int coordY;
    private int hitboxWidthRadius;
    private int hitboxHeightRadius;
    private int direction;
    private int velocity;
    private int health;


    Entity(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius){
        this.coordX = coordX;
        this.coordY = coordY;
        this.hitboxWidthRadius = hitboxWidthRadius;
        this.hitboxHeightRadius = hitboxHeightRadius;

        this.direction = 0;
        this.velocity = 0;
        this.health = 1;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getDirection() {
        return direction;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
}
