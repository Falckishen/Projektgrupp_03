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
            case up:
                y += this.getVelocity();
                break;
            case down:
                y -= this.getVelocity();
                break;
            case left:
                x -= this.getVelocity();
                break;
            case right:
                x += this.getVelocity();
                break;
        }
        setCoordX(x);
        setCoordY(y);
    }
}
