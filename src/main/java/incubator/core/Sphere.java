package incubator.core;

import com.google.common.collect.Lists;
import navigation.Position;

import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.List;

import static javax.media.opengl.glu.GLU.*;

public class Sphere {

    // Constants
    public static final int INIT_ROTATION_POSITION = 1;

    // Fields
    protected double radius;
    protected Position position;
    protected double rotationSpeed;
    protected Position axeRotation;
    protected double selfRotation;
    protected List<Sphere> satellites;

    // Constructor
    public Sphere() {

    }

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
    public void render(GLU glu) {
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU_LINE);
        glu.gluQuadricNormals(q, GLU_FLAT);
        glu.gluQuadricOrientation(q, GLU_OUTSIDE);
        glu.gluSphere(q, radius, 16, 16);
        glu.gluDeleteQuadric(q);
    }

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

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
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

    public void updateSelfRotation(double rot) {
        this.selfRotation = this.selfRotation + rot;
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
