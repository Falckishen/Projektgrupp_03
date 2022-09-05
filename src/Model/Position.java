package Model;

class Position {

    private final int x;
    private final int y;

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