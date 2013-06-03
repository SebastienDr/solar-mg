package core;

import navigation.Position;

public class Transport {

    private static final float DEFAULT_SPEED = 0.2f;

    private Position position;
    private TransportStatus status;
    private Route route;
    private float speed = DEFAULT_SPEED;

    public Transport(Position position) {
        this.position = position;
        this.status = TransportStatus.CARRYING;
    }

    public Transport(Route route, Position position) {
        this(position);
        this.route = route;
    }

    public Transport(Route route) {
        this.route = route;
        this.status = TransportStatus.CARRYING;
        this.position = new Position(route.getStartRoute().getPosition());
    }

    // Method
    public void update() {
        switch (getStatus()) {
            case CARRYING:
                position.setX(position.getX() + speed);
                if (hasDelivered())
                    setStatus(TransportStatus.RETURNING);
                break;
            case RETURNING:
                position.setX(position.getX() - speed);
                if (hasReturned())
                    setStatus(TransportStatus.CARRYING);
                break;
        }
    }

    public boolean hasReturned() {
        Planet start = route.getStartRoute();
        return position.getX() < start.getPosition().getX() + start.getRadius();
    }

    public boolean hasDelivered() {
        Planet target = route.getEndRoute();
        return position.getX() >= target.getPosition().getX() - target.getRadius();
    }

    // Accessors
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = new Position(position);
    }

    public TransportStatus getStatus() {
        return status;

    }

    public void setStatus(TransportStatus status) {
        this.status = status;
    }

}