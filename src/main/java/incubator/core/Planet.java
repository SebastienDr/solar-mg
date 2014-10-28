package incubator.core;

import com.google.common.collect.Lists;
import navigation.Position;
import navigation.PositionFactory;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import java.util.List;

public class Planet extends Sphere {

    private double rotationAround;
    private double rotationAroundSpeed;
    private List<Satellite> satellites;

    /**
     * Constructor
     *
     * @param radius
     * @param selfRotationSpeed
     * @param rotationAroundSpeed
     * @param positionFromSun
     */
    public Planet(double radius, double selfRotationSpeed, double rotationAroundSpeed, Position positionFromSun) {
        super(radius, positionFromSun, selfRotationSpeed);
        this.rotationAroundSpeed = rotationAroundSpeed;
        this.satellites = Lists.newArrayList();
    }

    public void update() {
        this.updateSelfRotation();
        this.updateRotationAround();
        for (Satellite s : satellites) {
            s.update();
        }
    }

    public void updateRotationAround() {
        setRotationAround(rotationAround + rotationAroundSpeed);
    }

    public void render(GL2 gl) {
        context(gl);
        super.render();
        for (Satellite s : satellites) {
            context(gl);
            s.context(gl);
            s.render();
        }
    }

    public void context(GL2 gl) {
        gl.glLoadIdentity();
        gl.glRotated(selfRotation, 0.0, 0.0, 1.0);
        gl.glTranslated(position.getX(), position.getY(), position.getZ());
        gl.glRotated(rotationAround, 0.0, 0.0, 1.0);
    }

    // toString
    public String toString() {
        return "Planet "+ super.toString() + "\n" + "with satellites "+satellites;
    }

    // Accessors
    public double getRotationAround() {
        return rotationAround;
    }

    public void setRotationAround(double rotationAround) {
        this.rotationAround = rotationAround;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void addSatellite(Satellite s) {
        satellites.add(s);
    }
}