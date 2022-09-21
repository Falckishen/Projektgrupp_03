package Model.Entities;

import Utilities.EntityType;

class Wall extends AllObjects {
    Wall(int hitBoxRadiusX, int hitBoxRadiusY, int x, int y){
        super(EntityType.wall, hitBoxRadiusX, hitBoxRadiusY, x, y);
    }
}
