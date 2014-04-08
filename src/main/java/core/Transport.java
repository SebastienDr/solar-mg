package core;

import navigation.Moveable;
import navigation.Position;
import navigation.Updatable;

import static navigation.Maths.distance;

public class Transport implements Moveable, Updatable {

    public static final int MAX_CAPACITY = 100;
    public static final Double DEFAULT_UPKEEP = 0.1;

    private Position direction;
    private Position position;
    private TransportStatus status;
    private Route route;
    private int resources;
    private float speed;
    private int price;
    private Double upkeep;

    public Transport(Route route) {
        this.route = route;
        this.status = TransportStatus.LOADING;
        this.position = new Position(route.getStartRoute().getPosition());
        this.direction = new Position(1.0, 0.0, 0.0);
        this.upkeep = DEFAULT_UPKEEP;
    }

    // Method
    public void update() {
        switch (status) {
            case GOING:
                changeSpeed();
                move(direction);
                if (hasArrived()) {
                    stop();
                    setPosition(route.getEndRoute().getPosition());
                    setStatus(TransportStatus.DELIVERING);
                }
                break;
            case DELIVERING:
                if (getResources() == 0.0) {
                    setStatus(TransportStatus.RETURNING);
                    setDirection(Position.POSITION_MINUS100);
                } else {
                    deliver();
                }
                break;
            case RETURNING:
                changeSpeed();
                move(direction);
                if (hasReturned()) {
                    stop();
                    setPosition(route.getStartRoute().getPosition());
                    setStatus(TransportStatus.LOADING);
                }
                break;
            case LOADING:
                if (getResources() == MAX_CAPACITY) {
                    setStatus(TransportStatus.GOING);
                    setDirection(Position.POSITION_100);
                } else {
                    load();
                }
                break;
            default:
                break;
        }
    }

    private void load() {
        this.status = TransportStatus.LOADING;
        loadResource();
    }

    private void deliver() {
        this.status = TransportStatus.DELIVERING;
        deliverResource();
    }

    private void loadResource() {
        this.setResources(getResources() + 1);
    }

    private void deliverResource() {
        this.setResources(getResources() - 1);
    }

    private void stop() {
        this.speed = 0.0f;
    }

    boolean afterHalfway() {
        return (returning() && isCloserToStartRoute()) || (going() && isCloserToEndRoute());
    }

    private boolean isCloserToEndRoute() {
        return distance(route.getEndRoute().getPosition(), position) < distance(route.getStartRoute().getPosition(), position);
    }

    private boolean isCloserToStartRoute() {
        return distance(route.getEndRoute().getPosition(), position) > distance(route.getStartRoute().getPosition(), position);
    }

    private boolean returning() {
        return status.equals(TransportStatus.RETURNING);
    }

    private boolean going() {
        return status.equals(TransportStatus.GOING);
    }

    public boolean hasReturned() {
        Planet start = route.getStartRoute();
        return position.getX() <= start.getPosition().getX();
    }

    public boolean hasArrived() {
        Planet target = route.getEndRoute();
        return position.getX() >= target.getPosition().getX();
    }

    public Position move(Position direction) {
        setPosition(position.add(direction.mult(speed)));
        return position;
    }

    public void changeSpeed() {
        if (afterHalfway()) {
            slowDown();
        } else {
            accelerate();
        }
    }

    public void accelerate() {
        this.speed = this.speed + 0.001f;
    }

    public void slowDown() {
        if (speed > 0) {
            this.speed = this.speed - 0.001f;
        }
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

    public void setDirection(Position direction) {
        this.direction = direction;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public Position getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Double getUpkeep() {
        return this.upkeep;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}