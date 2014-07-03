package core;

import navigation.Position;
import navigation.PositionFactory;
import templates.Basic3DWindow;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Camera {

    private final int width;
    private final int height;

    private Position eye;
    private Position center;
    private Position up;
    private float distance;
    private float initDistance;
    private GLU glu = new GLU();

    // Constructor
    public Camera(float distance) {
        this.width = Basic3DWindow.DEFAULT_WIDTH;
        this.height = Basic3DWindow.DEFAULT_HEIGHT;
        this.initDistance = distance;
        this.distance = distance;
        this.eye = PositionFactory.create(0, 0, distance);
        this.center = PositionFactory.create(0, 0, 0);
        this.up = PositionFactory.create(0, 1, 0);
    }

    // Methods
    public void render(GL2 gl) {
        // Change to projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) width / (float) height;
        glu.gluPerspective(45, widthHeightRatio, 1, 200000);

        Position eye = PositionFactory.create(0, 0, distance);
        Position center = PositionFactory.create(0, 0, 0);
        Position up = PositionFactory.create(0, 1, 0);
        glu.gluLookAt(eye.getX(), eye.getY(), eye.getZ(), center.getX(), center.getY(), center.getZ(), up.getX(), up.getY(), up.getZ());

        // Change back to model view matrix.
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void reset() {
        setDistance(getInitDistance());
    }

    public void zoomIn() {
        setDistance(getDistance() - 300);
    }

    public void zoomOut() {
        setDistance(getDistance() + 300);
    }

    // Accessors
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getInitDistance() {
        return initDistance;
    }

    public Position getEye() {
        return eye;
    }

    public void setEye(Position eye) {
        this.eye = eye;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public Position getUp() {
        return up;
    }

    public void setUp(Position up) {
        this.up = up;
    }
}