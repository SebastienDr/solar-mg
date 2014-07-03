package navigation;

import com.google.common.base.Objects;

import java.text.DecimalFormat;

public class Position {

    public static final Position POSITION_100 = new Position(1.0, 0.0, 0.0);
    public static final Position POSITION_MINUS100 = new Position(-1.0, 0.0, 0.0);
    public static final Position POSITION_010 = new Position(0.0, 1.0, 0.0);
    public static final Position POSITION_001 = new Position(0.0, 0.0, 1.0);
    public static final Position POSITION_111 = POSITION_100.add(POSITION_010).add(POSITION_001);
    public static final Position POSITION_000 = new Position(0.0, 0.0, 0.0);

    private Double x, y, z;
    public static final DecimalFormat FORMAT = new DecimalFormat("#0.00");

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

    public Position mult(double coeff) {
        return new Position(this.getX() * coeff, this.getY() * coeff, this.getZ() * coeff);
    }

    public boolean isCloseTo(Position position) {
        if (this.equals(position)) {
            return true;
        }
        Position diff = Maths.norm(this.minus(position));
        if ((diff.getX() <= 0.1) && (diff.getY() <= 0.1) && (diff.getZ() <= 0.1)) {
            return true;
        }
        return false;
    }

    public void rotateFrom(double x, double y, double z, double speed) {

    }

    public Position toSpherical() {
        double rho = Math.sqrt(x * x + y * y + z * z);
        double theta = Math.acos(z / rho);
        double psi = Math.atan(y / x);
        return new Position(rho, theta, psi);
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
        return "(" + getX() +
                "," + getY() +
                "," + getZ() +
                ')';
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Double z) {
        this.z = z;
    }

}