package core;

import navigation.Position;

public class Planet {

    // Fields
    private float radius;
    private Position position;

    // Constructor
    public Planet(float radius, Position position) {
        this.radius = radius;
        this.position = position;
    }

    // Accessor
    public float getRadius() {
        return radius;
    }

    public Position getPosition() {
        return position;
    }

}
