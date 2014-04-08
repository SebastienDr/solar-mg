package navigation;

import navigation.Position;

public class PositionFactory {

    public static Position position(double x, double y, double z) {
        return new Position(x, y, z);
    }

}
