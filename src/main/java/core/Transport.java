package core;

import navigation.Position;

public class Transport {

    public static final float DEFAULT_SPEED = 0.5f;

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

    // Method
    public void update() {
        switch (getStatus()) {
            case CARRYING:
                position.setX(position.getX() + speed);
                if (position.getX() >= route.getEndRoute().getPosition().getX() - route.getEndRoute().getRadius())
                    setStatus(TransportStatus.RETURNING);
                break;
            case RETURNING:
                position.setX(position.getX() - speed);
                if (position.getX() < route.getStartRoute().getPosition().getX() + route.getStartRoute().getRadius())
                    setStatus(TransportStatus.CARRYING);
                break;
        }
    }

    // Accessors
    public Position getPosition() {
        return position;
    }

    public TransportStatus getStatus() {
        return status;

    }

    public void setStatus(TransportStatus status) {
        this.status = status;
    }

}