package Model.Entities;

public interface AddNonLivingObjects {
    void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY);
    void createWorldBorderWalls(int worldRadiusX, int worldRadiusY);
}
