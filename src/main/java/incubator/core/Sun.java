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
        setSelfRotation(selfRotation);
    }

    public void render(GL2 gl) {
        gl.glLoadIdentity();
        gl.glRotated(this.getSelfRotation(), 0.0, 0.0, 1.0);
        render(new GLU());
    }
}
