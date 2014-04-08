package core;

import templates.Basic3DWindow;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Camera {

    private final int width;
    private final int height;

    public Camera(GL2 gl, GLU glu, float distance) {
        width = Basic3DWindow.DEFAULT_WIDTH;
        height = Basic3DWindow.DEFAULT_HEIGHT;

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
}