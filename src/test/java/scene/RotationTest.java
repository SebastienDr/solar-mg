package scene;

import com.jogamp.opengl.util.FPSAnimator;
import core.Camera;
import core.Planet;
import navigation.Position;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RotationTest implements GLEventListener{

    ShapeBuilder builder;
    GLU glu;
    GL2 gl;

    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("Rotation test");
        frame.setSize(500, 500);
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
        update();
        render(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        new Camera(gl, glu, 20);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

    // To override
    public void update() {
        //gl.glRotated(2.0,2.0,2.0,2.0);
    }

    // To override
    public void render(GLAutoDrawable drawable) {
        builder = new ShapeBuilder(glu, gl);

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        builder.newPlanet(new Planet(2.0f, Position.POSITION_000));
        gl.glRotated(2.0,5.0,2.0,0.0);
        //gl.glRotated(0.0,2.0,1.0,2.0);

    }
}
