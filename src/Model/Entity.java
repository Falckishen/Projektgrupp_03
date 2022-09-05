package Model;

public abstract class Entity {
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
    }
}