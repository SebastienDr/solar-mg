package incubator.core;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class Sun extends Sphere {

    /**
     * Basic sun on zero position creation
     *
     * @param radius       Radius of the sphere
     * @param selfRotation self rotating speed
     */
    public Sun(double radius, double selfRotation) {
        setRadius(radius);
        setSelfRotationSpeed(selfRotation);
    }

    public void render(GL2 gl) {
        gl.glLoadIdentity();
        gl.glRotated(this.getSelfRotation(), 0.0, 0.0, 1.0);
        super.render();
    }

    public void update() {
        updateSelfRotation();
    }

    // toString
    public String toString() {
        return "Sun "+super.toString();
    }
}
