package Model;

class Position {

    private int x;
    private int y;

    // TODO fixa bägränsningar för parametrarna
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}