package core;

import navigation.Position;
import navigation.Updatable;

public class Planet implements Updatable {

    // Fields
    private float radius;
    private Position position;

    // Constructor
    public Planet(float radius, Position position) {
        this.radius = radius;
        this.position = position;
    }

    @Override
    public void update() {
        position.rotateFrom(0.0, 0.0, 0.0, 1.0);
    }

    // Accessor
    public float getRadius() {
        return radius;
    }

    public Position getPosition() {
        return position;
    }

    public String toString() {
        return "Planet : (" + radius + "," + position + ")";
    }

}
