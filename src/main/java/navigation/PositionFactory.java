package navigation;

import navigation.Position;

public class PositionFactory {

    public static Position create(double x, double y, double z) {
        return new Position(x, y, z);
    }

    public static Position axis(double x, double y, double z) {
        return new Position(x, y, z);
    }
}
