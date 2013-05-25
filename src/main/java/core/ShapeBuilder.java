package core;

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

    public void newSphere(Planet p, Float[] color) {
        gl.glPushMatrix();
        setColorTo(color);
        gl.glTranslated(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
        createSphere(p.getRadius());
        gl.glPopMatrix();
    }

    public void newTransport(Transport p, Float[] color) {
        gl.glPushMatrix();
        setColorTo(color);
        gl.glTranslated(p.getPosition().getX(), p.getPosition().getY(), p.getPosition().getZ());
        createSphere(0.5f);
        gl.glPopMatrix();
    }

    private void createSphere(float radius) {
        GLUquadric sphere = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(sphere, GLU_FILL);
        glu.gluQuadricNormals(sphere, GLU_FLAT);
        glu.gluQuadricOrientation(sphere, GLU_OUTSIDE);
        final int slices = 16;
        final int stacks = 16;
        glu.gluSphere(sphere, radius, slices, stacks);
        glu.gluDeleteQuadric(sphere);
    }

}