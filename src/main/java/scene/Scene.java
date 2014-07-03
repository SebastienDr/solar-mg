package scene;

import core.Camera;
import game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.media.opengl.GL2.*;

public class Scene implements GLEventListener, KeyListener {

    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(Scene.class);

    // Constants
    public static final Font CONSOLE_PLAIN_18 = new Font("Console", Font.PLAIN, 18);
    public static final Font CONSOLE_PLAIN_14 = new Font("Console", Font.PLAIN, 14);

    // Fields
    protected SceneTextRenderer textRenderer;
    private GL2 gl;
    private GLU glu;
    private int sec;
    private int time = 0;
    private Game game;
    private ShapeBuilder shapeBuilder;
    private Camera camera;

    public Scene() {
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        game = new Game();
        glu = new GLU();
        gl = drawable.getGL().getGL2();
        initCamera();
        initLights();
        shapeBuilder = new ShapeBuilder(glu, gl);
        textRenderer = new SceneTextRenderer(CONSOLE_PLAIN_14, game);
        game.init();
        sec = 0;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        update();
        render(drawable.getGL().getGL2());
    }

    private void initCamera() {
        camera = new Camera(100);
    }

    private void initLights() {
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
        sec++;
        game.update();
    }

    private void render(GL2 gl) {
        clearScreen();
        camera.render(gl);
        textRenderer.render();
        game.render(shapeBuilder);
    }

    private void time() {
        if (sec % 60 == 0) {
            time++;
            sec = 0;
        }
    }

    private void clearScreen() {
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("key : " + keyEvent.getKeyCode());
        if (keyEvent.getKeyCode() == KeyEvent.VK_T) {
            game.addTransport();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Game getGame() {
        return game;
    }
}