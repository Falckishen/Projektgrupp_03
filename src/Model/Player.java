package Model;

public class Player extends Entity {
    Player(int coordX, int coordY, int hitboxWidthRadius, int hitboxHeightRadius) {
        super(coordX, coordY, hitboxWidthRadius, hitboxHeightRadius);

    }

    /*
    Player(int coordX, int coordY) {
        super(coordX, coordY, 20, 20);
    }*/


    @Override
    void move() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        switch (getDirection()){
            case UP:
                y += 1;
                break;
            case DOWN:
                y -= 1;
                break;
            case LEFT:
                x -= 1;
                break;
            case RIGHT:
                x += 1;
                break;
        }
        setCoordX(x);
        setCoordY(y);
    }

    @Override
    public void doOnTick() {
        move();
    }
}
