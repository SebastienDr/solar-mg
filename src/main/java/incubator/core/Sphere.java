package incubator.core;

import com.google.common.collect.Lists;
import navigation.Position;

import java.util.List;

public class Sphere {

    // Constants
    public static final int INIT_ROTATION_POSITION = 1;

    // Fields
    private double radius;
    private Position position;
    private int rotationSpeed;
    private Position axeRotation;
    private double selfRotation;
    private List<Sphere> satellites;

    // Constructor

    /**
     * Sphere creation
     *
     * @param radius
     * @param position
     * @param rotationSpeed
     * @param axeRotation
     */
    public Sphere(double radius, Position position, int rotationSpeed, Position axeRotation) {
        this.radius = radius;
        this.position = position;
        this.rotationSpeed = rotationSpeed;
        this.axeRotation = axeRotation;
        this.selfRotation = INIT_ROTATION_POSITION;
        this.setSatelites(Lists.<Sphere>newArrayList());
    }

    // Methods
    public boolean hasSatellites() {
        return satellites.size() > 0;
    }

    public void addSatellite(Sphere sat) {
        satellites.add(sat);
    }

    // Accessors
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public Position getAxeRotation() {
        return axeRotation;
    }

    public void setAxeRotation(Position axeRotation) {
        this.axeRotation = axeRotation;
    }

    public double getSelfRotation() {
        return selfRotation;
    }

    public void setSelfRotation(double selfRotation) {
        this.selfRotation = selfRotation;
    }

    public void updateSelfRotation() {
        this.selfRotation = this.selfRotation + rotationSpeed;
    }

    public List<Sphere> getSatellites() {
        return satellites;
    }

    public void setSatelites(List<Sphere> satellites) {
        this.satellites = satellites;
    }

}
