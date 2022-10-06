package Model.Entities;

/**
 * An interface for creating entities of that are subtypes of Entity, but not considered living entities.
 *                      In other words, not subtypes of MovingEntities.
 * @author Ida Altenstedt
 */
interface AddNonLivingObjects {
    /**
     * Creates a wall in a specific place according to inputted parameters.
     * @param positionX the wall's position on the x-axis.
     * @param positionY the wall's position on the y-axis.
     * @param wallRadiusX the wall's radius along the x-axis.
     * @param wallRadiusY the wall's radius along the y-axis.
     */
    void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY);

    /**
     * Creates walls according to the size of the world to act as a world Boarder.
     */
    void createWorldBorderWalls();
}