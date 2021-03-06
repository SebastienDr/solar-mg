package scene;

import com.jogamp.opengl.util.FPSAnimator;
import core.Camera;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import java.awt.*;
import java.awt.event.*;


public class RayPickingTest implements GLEventListener, MouseListener, MouseMotionListener {

    // Fields
    private GLU glu;
    private GL2 gl;
    private Camera camera;
    private static GLCanvas canvas;
    public static double mouseX = 0.0;
    public static double mouseY = 0.0;
    public static boolean mouseClick = false;

    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);

        Frame frame = new Frame("Rotation test");
        frame.setSize(800, 600);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addGLEventListener(new RayPickingTest());
        canvas.addMouseListener(new RayPickingTest());
        canvas.addMouseMotionListener(new RayPickingTest());

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.add(canvas);
        animator.start();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        computeEvents(drawable);
        render(drawable);
    }

    private void computeEvents(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        if (mouseClick) {
            //System.out.println("Mouse click at " + mouseX + "," + mouseY + " !");
        }
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        camera = new Camera(10);
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    // To override
    public void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        camera.render(gl.getGL2());

        drawAxes();
        //sphere(4);

        if (mouseClick) {
            gl.glLoadIdentity();
            ray(drawable, (mouseX-400)/40, (mouseY-300)/30);
        }
    }

    private void drawAxes() {
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3d(0.0, 0.0, 0.0);
        gl.glVertex3d(1.0, 0.0, 0.0);

        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3d(0.0, 0.0, 0.0);
        gl.glVertex3d(0.0, 1.0, 0.0);

        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3d(0.0, 0.0, 0.0);
        gl.glVertex3d(0.0, 0.0, 1.0);
        gl.glEnd();
    }

    private void sphere(double radius) {
        GLUquadric q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_LINE);
        glu.gluQuadricNormals(q, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluSphere(q, radius, 16, 16);
        glu.gluDeleteQuadric(q);
    }

    private void ray(GLAutoDrawable drawable, double x, double y) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3d(camera.getCenter().getX(), camera.getCenter().getY(), 0.0f); // origin of the line
        gl.glVertex3d(x, -y, -1.0f); // ending point of the line
        gl.glEnd();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getLocationOnScreen().getX();
        mouseY = e.getLocationOnScreen().getY();
        mouseClick = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println("Mouse moved : " + mouseX + "," + mouseY);
    }
}
