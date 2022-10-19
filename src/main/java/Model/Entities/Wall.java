package Model.Entities;

import Model.EntityType;

/**
 * A class for creating walls which is nothing more than stationary, non-living objects.
 * @author Ida Altenstedt
 */
class Wall extends Entity {
    /**
     * Creates a wall in a specific place according to inputted parameters.
     * @param hitBoxRadiusX the wall's radius along the x-axis.
     * @param hitBoxRadiusY the wall's radius along the y-axis.
     * @param x the wall's position on the x-axis.
     * @param y the wall's position on the y-axis.
     */
    Wall(int hitBoxRadiusX, int hitBoxRadiusY, int x, int y){
        super(EntityType.wall, hitBoxRadiusX, hitBoxRadiusY, x, y);
    }
}