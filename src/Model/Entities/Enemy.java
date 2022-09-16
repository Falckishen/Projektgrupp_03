package Model.Entities;

abstract class Enemy extends Entity {
    protected Enemy(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed) {
        super(x, y, hitBoxRadiusX, hitBoxRadiusY, speed);
    }
}
