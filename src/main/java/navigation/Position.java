package navigation;

public class Position {

    Double x, y, z;

    public Position(int x, int y, int z) {
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
        this.z = Double.valueOf(z);
    }

    public Position(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

}
