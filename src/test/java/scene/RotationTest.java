package scene;

import com.jogamp.opengl.util.FPSAnimator;
import core.Camera;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.media.opengl.glu.GLU.*;

public class RotationTest implements GLEventListener {

    // Fields
    private GLU glu;
    private GL2 gl;
    double speed, j;
    private Camera camera;

    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("Rotation test");
        frame.setSize(800, 600);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new RotationTest());

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.add(canvas);
        animator.start();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        render(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        camera = new Camera(30);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        speed = 0;
        j = 0;
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    // To override
    public void render(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        camera.render(gl);
        sun(2.0, speed / 7);

        planet(0.5, speed / 2, speed, 7.0);

        planet(0.8, speed / 3, 3 / speed / 5, 10.0);

        // 1st satellite for first planet
        contextPlanet(speed / 2, speed, 7.0);
        rotateZ(speed);
        gl.glTranslated(2.0, 0.0, 0.0);
        rotateZ(2 * speed / 3);
        sphere(0.5 / 3);

        // 2nd satellite for first planet
        contextPlanet(speed / 2, speed, 7.0);
        rotateZ(speed / 3);
        gl.glTranslated(4.0, 0.0, 0.0);
        rotateZ(2 * speed);
        sphere(0.1);

        // 1st satellite for second planet
        contextPlanet(speed / 3, 3 / speed / 5, 10.0);
        rotateZ(3 * speed);
        gl.glTranslated(2.0, 0.0, 0.0);
        rotateZ(speed);
        sphere(0.2);

        speed = speed + 1;
    }

    private void sun(double radius, double angle) {
        gl.glLoadIdentity();
        rotateZ(angle);
        sphere(radius);
    }

    private void planet(double radius, double selfSpeed, double speedAroundSun, double positionFromSun) {
        contextPlanet(selfSpeed, speedAroundSun, positionFromSun);
        sphere(radius);
    }

    private void contextPlanet(double selfSpeed, double speedAroundSun, double positionFromSun) {
        gl.glLoadIdentity();
        rotateZ(selfSpeed);
        gl.glTranslated(positionFromSun, 0.0, 0.0);
        rotateZ(speedAroundSun);
    }

    private void sphere(double radius) {
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU_LINE);
        glu.gluQuadricNormals(q, GLU_FLAT);
        glu.gluQuadricOrientation(q, GLU_OUTSIDE);
        glu.gluSphere(q, radius, 16, 16);
        glu.gluDeleteQuadric(q);
    }

    private void rotateZ(double speed) {
        gl.glRotated(speed, 0.0, 0.0, 1.0);
    }
}
