package navigation;

import com.google.common.base.Objects;

public class Position {

    public static final Position ZERO = new Position(0, 0, 0);
    Double x, y, z;

    public Position() {
    }

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

    public Position add(Position pos) {
        return new Position(this.getX() + pos.getX(), this.getY() + pos.getY(), this.getZ() + pos.getZ());
    }

    public Position minus(Position pos) {
        return new Position(this.getX() - pos.getX(), this.getY() - pos.getY(), this.getZ() - pos.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Position position = (Position) o;
        return Objects.equal(position.getX(), this.getX()) && Objects.equal(position.getY(), this.getY()) && Objects.equal(position.getZ(), this.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return "(" + x +
                "," + y +
                "," + z +
                ')';
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
