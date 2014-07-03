package templates;

import com.jogamp.opengl.util.FPSAnimator;
import scene.Scene;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Basic3DWindow<T> extends Frame {

    // Constants
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    protected FPSAnimator animator;
    private T scene;

    public Basic3DWindow() {
        super("Basic3DWindow");

        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(canvas);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Scene scene = new Scene();
        canvas.addGLEventListener(scene);
        canvas.addKeyListener(scene);

        animator = new FPSAnimator(canvas, 60);
        animator.add(canvas);
        animator.start();
    }

    public Basic3DWindow(String title, int width, int height) {
        this();
        setTitle(title);
        setSize(width, height);
    }

}
