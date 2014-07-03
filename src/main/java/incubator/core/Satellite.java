package incubator.core;

import navigation.Position;

import javax.media.opengl.GL2;

public class Satellite extends Planet {

    public Satellite(double radius, double selfRotation, double rotationAround, Position positionFromPlanet) {
        super(radius, selfRotation, rotationAround, positionFromPlanet);
    }

    public void context(GL2 gl) {
        gl.glRotated(selfRotation, 0.0, 0.0, 1.0);
        gl.glTranslated(position.getX(), position.getY(), position.getZ());
        gl.glRotated(getRotationAround(), 0.0, 0.0, 1.0);
    }

    public String toString() {
        return "Sat ["+radius +", "+position+", "+getSelfRotationSpeed()+"] ";
    }

}
