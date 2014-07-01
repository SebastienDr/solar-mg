package incubator.core;

import navigation.Position;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Planet extends Sphere {

    private double rotationAroundSun;

    public Planet(double radius, double selfSpeed, double speedAroundSun, Position positionFromSun) {
        super(radius, positionFromSun, 0, null);
        setSelfRotation(selfSpeed);
        this.rotationAroundSun = speedAroundSun;
    }

    public void updateRotationAroundSun(double v) {
        setRotationAroundSun(rotationAroundSun + v);
    }

    public void render(GL2 gl) {
        gl.glLoadIdentity();
        gl.glRotated(selfRotation, 0.0, 0.0, 1.0);
        gl.glTranslated(position.getX(), position.getY(), position.getZ());
        gl.glRotated(rotationAroundSun, 0.0, 0.0, 1.0);
        render(new GLU());
    }

    // Accessors
    public double getRotationAroundSun() {
        return rotationAroundSun;
    }

    public void setRotationAroundSun(double rotationAroundSun) {
        this.rotationAroundSun = rotationAroundSun;
    }
}
