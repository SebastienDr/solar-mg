package navigation;

import core.Planet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Maths {

    public static double distance(Position start, Position end) {
        double x = sq(end.getX() - start.getX());
        double y = sq(end.getY() - start.getY());
        double z = sq(end.getZ() - start.getZ());
        return racine(x + y + z);
    }

    public static double racine(double nb) {
        return round(Math.sqrt(nb));
    }

    public static double sq(double x) {
        return x * x;
    }

    public static double round(double number) {
        return BigDecimal.valueOf(number).setScale(9, RoundingMode.HALF_UP).doubleValue();
    }

    public static double distance(Planet startRoute, Planet endRoute) {
        return distance(startRoute.getPosition(), endRoute.getPosition());
    }

    public static Position norm(Position position) {
        if (position.getX() < 0) {
            position.setX(-position.getX());
        }
        if (position.getY() < 0) {
            position.setY(-position.getY());
        }
        if (position.getZ() < 0) {
            position.setZ(-position.getZ());
        }
        return position;
    }
}
