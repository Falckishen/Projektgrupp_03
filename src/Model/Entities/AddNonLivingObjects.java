package Model.Entities;

interface AddNonLivingObjects {
    void createWall(int positionX, int positionY, int wallRadiusX, int wallRadiusY);
    void createWorldBorderWalls();
}