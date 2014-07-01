package incubator.core;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import static javax.media.opengl.glu.GLU.*;

public class Renderer<T extends Sphere> {

    T target;
    private GL2 gl;
    private GLU glu;

    public Renderer(T target) {
        this.target = target;
    }

    public void render() {
        render(target);
        for (Sphere sat : target.getSatellites()) {
            render(sat);
            update(sat);
        }
        update(target);
    }

    private void update(Sphere sat) {
        gl.glPopMatrix();
        sat.updateSelfRotation();
    }

    private void render(Sphere target) {
        gl.glLoadIdentity();
        gl.glPushMatrix();
        gl.glTranslated(target.getPosition().getX(), target.getPosition().getY(), target.getPosition().getZ());
        gl.glRotated(90.0, target.getAxeRotation().getX(), target.getAxeRotation().getY(), target.getAxeRotation().getZ());
        gl.glRotated(target.getSelfRotation(), 0.0, 0.0, 1.0);
        GLUquadric sphereQuadric = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(sphereQuadric, GLU_LINE);
        glu.gluQuadricNormals(sphereQuadric, GLU_FLAT);
        glu.gluQuadricOrientation(sphereQuadric, GLU_OUTSIDE);
        glu.gluSphere(sphereQuadric, target.getRadius(), 16, 16);
        glu.gluDeleteQuadric(sphereQuadric);
    }

    public void inject(GL2 gl, GLU glu) {
        this.gl = gl;
        this.glu = glu;
    }

}
