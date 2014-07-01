package scene;

import core.Planet;
import core.Transport;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import static javax.media.opengl.glu.GLU.*;

public class ShapeBuilder {

    private final GLU glu;
    private final GL2 gl;

    public ShapeBuilder(GLU glu, GL2 gl) {
        this.glu = glu;
        this.gl = gl;
    }

    private void setColorTo(Float[] color) {
        gl.glColor3f(color[0], color[1], color[2]);
    }

    public void newPlanet(Planet p) {
        newPlanet(p, color(0.3f, 0.5f, 1f));
    }

    public void newPlanet(Planet p, Float[] color) {
        gl.glPushMatrix();
        setColorTo(color);
        gl.glTranslated(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
        createSphere(p.getRadius());
        gl.glPopMatrix();
    }

    public void newTransport(Transport t, Float[] color) {
        gl.glPushMatrix();
        setColorTo(color);
        gl.glTranslated(t.getPosition().getX(), t.getPosition().getY(), t.getPosition().getZ());
        createSphere(0.5f, 5, 5);
        gl.glPopMatrix();
    }

    private void createSphere(float radius, int slices, int stacks) {
        GLUquadric sphere = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(sphere, GLU_LINE);
        glu.gluQuadricNormals(sphere, GLU_FLAT);
        glu.gluQuadricOrientation(sphere, GLU_OUTSIDE);
        glu.gluSphere(sphere, radius, slices, stacks);
        glu.gluDeleteQuadric(sphere);
    }

    private void createSphere(float radius) {
        createSphere(radius, 16, 16);
    }

    private Float[] color(float red, float green, float blue) {
        Float[] color = new Float[3];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        return color;
    }
}