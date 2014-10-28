package incubator.scene;

import com.google.common.collect.Lists;
import com.jogamp.opengl.util.FPSAnimator;
import incubator.core.Planet;
import incubator.core.Satellite;
import incubator.core.Sun;
import navigation.Position;
import navigation.PositionFactory;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class SolarSystem extends SceneEvents {

    // Fields
    private GL2 gl;
    private Sun sun;
    private List<Planet> planets = Lists.newArrayList();

    // ************** INIT *************
    public void init(GLAutoDrawable drawable) {
        super.init(drawable);
        gl = drawable.getGL().getGL2();
        theSolarSystem();
        //aSolarSystem();
        //generate(6);
    }

    private void theSolarSystem() {
        sun = new Sun(1392.684, 0.037);
        planets.add(new Planet(2.439, 0.00707, 0.01137, pos(sun.getRadius() + 15000, 0.0, 0.0)));
    }

    private void aSolarSystem() {
        sun = new Sun(2.0, 0.1);
        Planet planet1 = new Planet(0.5, 0.5, 1.0, pos(7.0, 0.0, 0.0));
        Planet planet2 = new Planet(0.8, 0.33, 0.2, pos(13.0, 0.0, 0.0));
        Satellite s11 = new Satellite(0.5 / 3, 1.0, 2.0 / 3, pos(2.0, 0.0, 0.0));
        Satellite s12 = new Satellite(0.1, 1 / 3, 2.0, pos(3.0, 0.0, 0.0));
        planet1.addSatellite(s11);
        planet1.addSatellite(s12);
        Satellite s21 = new Satellite(0.2, 3.0, 1.0, pos(2.0, 0.0, 0.0));
        planet2.addSatellite(s21);
        planets.add(planet1);
        planets.add(planet2);
    }

    /**
     * Generate a solar system
     */
    public void generate(int nbMaxPlanet) {
        sun = new Sun(5 + random(5), random(1));
        double pos = 0.0;
        // planets
        for (int i = 0; i <= random(1, nbMaxPlanet); i++) {
            Planet p = new Planet(random(1), random(1), random(2), pos(random(5) + sun.getRadius() * 2 + pos, 0.0, 0.0));
            double posSat = 0.0;
            for (int j = 0; j < random(0, 4); j++) {
                Satellite s = new Satellite(p.getRadius() / 2, random(1), random(1) * 2, pos(random(1) + p.getRadius() + posSat, 0.0, 0.0));
                p.addSatellite(s);
                posSat = posSat + s.getPosition().getX();
            }
            pos = pos + p.getPosition().getX();
            System.out.println(p);
            planets.add(p);
        }
    }

    private double random(double min, double max) {
        return min + Math.floor(Math.random() * (max - min));
    }

    private double random(double max) {
        return Math.random() * max;
    }

    // *************** RENDER **************
    @Override
    public void display(GLAutoDrawable drawable) {
        render();
    }

    public void render() {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        // Update
        updateObjects();
        camera.setEye(planets.get(0).getPosition());
        // Render
        camera.render(gl);
        renderObjects();
    }

    // ************** OBJECTS in the scene : UPDATE & RENDER **************
    private void updateObjects() {
        sun.update();
        for (Planet p : planets) {
            p.update();
        }
    }

    private void renderObjects() {
        sun.render(gl);
        for (Planet p : planets) {
            p.render(gl);
        }
    }

    // **************** OTHERS ****************
    private Position pos(double x, double y, double z) {
        return PositionFactory.create(x, y, z);
    }

    // **************** MAIN PROGRAM **************
    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);

        Frame frame = new Frame("Solar system");
        frame.setSize(1200, 800);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setFocusableWindowState(true);
        frame.setAutoRequestFocus(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        SolarSystem solarSystem = new SolarSystem();
        canvas.addGLEventListener(solarSystem);
        canvas.addKeyListener(solarSystem);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.add(canvas);
        animator.start();
    }

}
