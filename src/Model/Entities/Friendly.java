package Model.Entities;

abstract class Friendly extends Entity {
    protected Friendly(int x, int y, int hitBoxRadiusX, int hitBoxRadiusY, int speed) {
        super(x, y, hitBoxRadiusX, hitBoxRadiusY, speed);
    }
}
