package navigation;


public class Util {

    public double distance(Position start, Position end) {
        double x = sq(end.getX()) - sq(start.getX());
        double y = sq(end.getY()) - sq(start.getY());
        double z = sq(end.getZ()) - sq(start.getZ());
        return Math.sqrt(x+y+z);
    }

    public double sq(double x) {
        return x * x;
    }
}
