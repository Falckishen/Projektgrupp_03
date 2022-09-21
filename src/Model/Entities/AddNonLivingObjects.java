package Model.Entities;

public interface AddNonLivingObjects {
    void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY);
    void createWorldWalls(int worldRadiusX, int worldRadiusY);
}
