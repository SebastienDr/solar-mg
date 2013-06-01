package navigation;


public class Util {

    public double distance(Position start, Position end) {
        double x = sq(end.getX() - start.getX());
        double y = sq(end.getY() - start.getY());
        double z = sq(end.getZ() - start.getZ());
        return Math.sqrt(x+y+z);
    }

    public double sq(double x) {
        return x * x;
    }
}
