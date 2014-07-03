package incubator.core;

import com.google.common.collect.Lists;
import navigation.Position;

import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.util.List;

import static javax.media.opengl.glu.GLU.*;

public class Sphere {

    // Fields
    protected double radius;
    protected Position position;
    protected double selfRotation;
    protected double selfRotationSpeed;

    // Constructor
    public Sphere() {
    }

    /**
     * Sphere creation
     *
     * @param radius
     * @param position
     * @param selfRotationSpeed
     */
    public Sphere(double radius, Position position, double selfRotationSpeed) {
        this.radius = radius;
        this.position = position;
        this.selfRotationSpeed = selfRotationSpeed;
    }

    // Methods
    public void render(GLU glu) {
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU_LINE);
        glu.gluQuadricNormals(q, GLU_FLAT);
        glu.gluQuadricOrientation(q, GLU_OUTSIDE);
        glu.gluSphere(q, radius, 50, 50);
        glu.gluDeleteQuadric(q);
    }

    // toString


    @Override
    public String toString() {
        return "{" +
                "radius=" + radius +
                ", position=" + position +
                ", selfRotationSpeed=" + selfRotationSpeed +
                '}';
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

    public double getSelfRotation() {
        return selfRotation;
    }

    public void setSelfRotation(double selfRotation) {
        this.selfRotation = selfRotation;
    }

    public double getSelfRotationSpeed() {
        return selfRotationSpeed;
    }

    public void setSelfRotationSpeed(double selfRotationSpeed) {
        this.selfRotationSpeed = selfRotationSpeed;
    }

    public void updateSelfRotation(double rot) {
        this.selfRotation = this.selfRotation + rot;
    }

    public void updateSelfRotation() {
        this.selfRotation = this.selfRotation + this.selfRotationSpeed;
    }

}