package scene;


import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import static javax.media.opengl.GL2.*;
import static javax.media.opengl.glu.GLU.*;

public class Scene implements GLEventListener {

    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 480;

    private GL2 gl;
    private GLU glu;

    private final int width;
    private final int height;

    private double theta = 0;
    private double positionTransport = 7;

    public Scene() {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        gl = drawable.getGL().getGL2();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        update();
        setCamera(100);
        //setLight();
        render();
    }

    private void setLight() {
        // Prepare light parameters.
        float SHINE_ALL_DIRECTIONS = 1;
        float[] lightPos = {-30, 0, 0, SHINE_ALL_DIRECTIONS};
        float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
        float[] lightColorSpecular = {0.8f, 0.8f, 0.8f, 1f};

        // Set light parameters.
        gl.glLightfv(GL_LIGHT1, GL_POSITION, lightPos, 0);
        gl.glLightfv(GL_LIGHT1, GL_AMBIENT, lightColorAmbient, 0);
        gl.glLightfv(GL_LIGHT1, GL_SPECULAR, lightColorSpecular, 0);

        // Enable lighting in GL.
        gl.glEnable(GL_LIGHT1);
        gl.glEnable(GL_LIGHTING);

        // Set material properties.
        float[] rgba = {0.3f, 0.5f, 1f};
        gl.glMaterialfv(GL_FRONT, GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL_FRONT, GL_SHININESS, 0.5f);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    private void update() {
        positionTransport += 0.1;
    }

    private void setCamera(float distance) {
        // Change to projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) width / (float) height;
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private void render() {

        clearScreen();

        // Draw sphere (possible styles: FILL, LINE, POINT).
        newSphere(6.378f, position(0, 0, 0), color(0.3f, 0.5f, 1f));
        newSphere(5f, position(40, 0, 0), color(0f, 1f, 0f));

        if (positionTransport <= 34) {
            newSphere(1f, position(positionTransport, 0, 0), color(1f, 0f, 0f));
        }
    }

    private Float[] color(float red, float green, float blue) {
        Float[] color = new Float[3];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        return color;
    }

    private Double[] position(double x, double y, double z) {
        Double[] position = new Double[3];
        position[0] = x;
        position[1] = y;
        position[2] = z;
        return position;
    }

    private void newSphere(float radius, Double[] position, Float[] color) {
        gl.glPushMatrix();
        setColorTo(color);
        gl.glTranslated(position[0], position[1], position[2]);
        drawSphere(radius);
        gl.glPopMatrix();
    }

    private void setColorTo(Float[] color) {
        gl.glColor3f(color[0], color[1], color[2]);
    }

    private void drawSphere(float radius) {
        GLUquadric sphere = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(sphere, GLU_FILL);
        glu.gluQuadricNormals(sphere, GLU_FLAT);
        glu.gluQuadricOrientation(sphere, GLU_OUTSIDE);
        final int slices = 16;
        final int stacks = 16;
        glu.gluSphere(sphere, radius, slices, stacks);
        glu.gluDeleteQuadric(sphere);
    }

    private void clearScreen() {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}