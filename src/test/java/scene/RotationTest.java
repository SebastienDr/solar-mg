package scene;

import com.google.common.collect.Lists;
import com.jogamp.opengl.util.FPSAnimator;
import core.Camera;
import incubator.core.Renderer;
import incubator.core.Sphere;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static javax.media.opengl.glu.GLU.*;

public class RotationTest implements GLEventListener {

    // Fields
    private GLU glu;
    private GL2 gl;
    private List<Renderer> renderers;
    double speed, j;

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
        renderers = Lists.<Renderer>newArrayList();
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        new Camera(gl, glu, 50);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        speed = 0;
        j = 0;
//        // Sun
//        Sphere sun = new Sphere(2.0f, Position.POSITION_000, 1, axis(1.0, 0.0, 0.0));
//        renderers.add(rendererFor(sun));
//
//        // Planet and satellite
//        Sphere sat = new Sphere(0.3f, create(9, 0, 0), 0, axis(1.0, 1.0, 0.0));
//        Sphere planet = new Sphere(1.0f, create(7, 0, 0), 2, axis(1.0, 1.0, 0.0));
//        planet.addSatellite(sat);
//        renderers.add(rendererFor(planet));

    }

    private Renderer<Sphere> rendererFor(Sphere sphere) {
        Renderer<Sphere> renderer = new Renderer<Sphere>(sphere);
        renderer.inject(gl, glu);
        return renderer;
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    // To override
    public void render(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//        for (Renderer renderer : renderers) {
//            renderer.render();
//        }
        // rotate all the scene
        //rotateY(0.5);
        //solarSystem();
        gl.glLoadIdentity();
        sun();

        planet(2.0, speed/2, speed, 5.0);
        planet(2.0, speed, speed * 2, 12.0);

        speed = speed + 1;
    }

    private void sun() {
        sphere(0.2);
    }

    private void planet(double radius, double selfSpeed, double speedAroundSun, double positionFromSun) {
        gl.glLoadIdentity();
        rotateZ(selfSpeed);
        gl.glTranslated(positionFromSun, 0.0, 0.0);
        rotateZ(speedAroundSun);
        sphere(radius);
        //satellite(radius/3, 2*selfSpeed, 2*selfSpeed/3, 4.0);
        rotateZ(2*selfSpeed);
        gl.glTranslated(4.0, 0.0, 0.0);
        rotateZ(2*selfSpeed/3);
        sphere(radius/3);
    }

    private void satellite(double radius, double selfSpeed, double speedAroundPlanet, double positionFromPlanet) {
        rotateZ(selfSpeed);
        gl.glTranslated(positionFromPlanet, 0.0, 0.0);
        rotateZ(speedAroundPlanet);
        sphere(radius);
    }

    private void solarSystem() {
        // reset the scene
        gl.glLoadIdentity();
        // rotate the entire scene
//        gl.glRotated(45.0, 1.0, 0.0, 0.0);

        // Remember : openGL is a state machine => order is important !
        // self rotating sphere on z-axis with speed.
        selfRotatingSphere(6.0, speed);
        // rotating next object around sphere   (order is important !)
        gl.glLoadIdentity();
        gl.glRotated(speed * 3, 0.0, 0.0, 1.0);
        gl.glTranslated(15, 0, 0);
        selfRotatingSphere(2.5, j);

        gl.glTranslated(6, 0, 0);
        gl.glRotated(-speed, 0.0, 0.0, 1.0);
        selfRotatingSphere(0.5, j / 2);

        gl.glLoadIdentity();
        gl.glRotated(j / 12, 0.0, 0.0, 1.0);
        gl.glTranslated(19, 0, 0);
        selfRotatingSphere(1.1, j / 12);

        speed = speed + 0.1;
        j = j + 3;
    }

    private void selfRotatingSphere(double radius, double rotationSpeed) {
        gl.glRotated(rotationSpeed, 0.0, 0.0, 1.0);
        sphere(radius);
    }

    private void sphere(double radius) {
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU_LINE);
        glu.gluQuadricNormals(q, GLU_FLAT);
        glu.gluQuadricOrientation(q, GLU_OUTSIDE);
        glu.gluSphere(q, radius, 16, 16);
        glu.gluDeleteQuadric(q);
    }

    private void rotateX(double speed) {
        gl.glRotated(speed, 1.0, 0.0, 0.0);
    }

    private void rotateY(double speed) {
        gl.glRotated(speed, 0.0, 1.0, 0.0);
    }

    private void rotateZ(double speed) {
        gl.glRotated(speed, 0.0, 0.0, 1.0);
    }
}
