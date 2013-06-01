package scene;

import com.jogamp.opengl.util.awt.TextRenderer;
import core.*;
import navigation.Position;
import templates.Basic3DWindow;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import java.awt.*;
import java.util.*;
import java.util.List;

import static javax.media.opengl.GL2.*;

public class Scene implements GLEventListener {

    protected TextRenderer renderer;
    private GL2 gl;
    private GLU glu;

    // Objects
    Player player;
    Planet p;
    Planet p2;
    List<Transport> ts;

    private ShapeBuilder shapeBuilder;
    private int wallet = 10000;

    public Scene() {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        gl = drawable.getGL().getGL2();
        this.shapeBuilder = new ShapeBuilder(glu, gl);

        renderer = new TextRenderer(new Font("Console", Font.PLAIN, 18));
        buildObjects();
    }

    private void buildObjects() {
        p = new Planet(6.378f, position(0, 0, 0));
        p2 = new Planet(5f, position(40, 0, 0));
        Route route = new Route(p, p2);
        ts = new TransportFactory().createTransports(route, 3);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        update();
        new Camera(drawable.getGL().getGL2(), glu, 100);
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
        for (int i = 0; i < ts.size(); i++) {
            ts.get(i).update();
            if (ts.get(i).hasDelivered()) {
                updateWallet(1000);
            }
        }
    }

    private void updateWallet(int i) {
        wallet = wallet + i;
    }

    private void render() {
        clearScreen();
        renderText();

        shapeBuilder.newSphere(p, color(0.3f, 0.5f, 1f));
        shapeBuilder.newSphere(p2, color(0f, 1f, 0f));
        for (int i = 0; i < ts.size(); i++) {
            shapeBuilder.newTransport(ts.get(i), color(1f, 0f, 0f));
        }
    }

    private void renderText() {
        renderer.beginRendering(Basic3DWindow.DEFAULT_WIDTH, Basic3DWindow.DEFAULT_HEIGHT);
        renderer.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        renderer.draw("$ "+ wallet, 10, 10);
        renderer.endRendering();
    }

    private Float[] color(float red, float green, float blue) {
        Float[] color = new Float[3];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        return color;
    }

    private Position position(double x, double y, double z) {
        return new Position(x, y, z);
    }

    private void clearScreen() {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}