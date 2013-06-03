package core;

import navigation.Position;

/**
 * Basically, it's a position in space and a direction GOING to target or RETURNING to start
 */
public class RouteDirection {

    private Position position;
    private TransportStatus direction;

    public RouteDirection(Position position, TransportStatus direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TransportStatus getDirection() {
        return direction;
    }

    public void setDirection(TransportStatus direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return position +","+ direction;
    }
}
