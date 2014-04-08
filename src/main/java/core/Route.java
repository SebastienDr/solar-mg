package core;

import navigation.Position;

/**
 * Define a route between planets
 */
public class Route {

    Planet startRoute;
    Planet endRoute;

    public Route(Planet startRoute, Planet endRoute) {
        this.startRoute = startRoute;
        this.endRoute = endRoute;
    }

    /**
     * Renvoie la difference de position entre le départ et l'arrivée de la Route.
     * Equivalent de l'axe de la droite passant par ces 2 points.
     *
     * @return le différentiel de position
     */
    public Position distance() {
        return endRoute.getPosition().minus(startRoute.getPosition());
    }

    public Position distanceInv() {
        return startRoute.getPosition().minus(endRoute.getPosition());
    }

    /**
     * Rerturn halway position.
     *
     * @return
     */
    public Position halfway() {
        return startRoute.getPosition().add(endRoute.getPosition()).mult(0.5);
    }

    public Position calculateAR() {
        double x = (distance().getX() - startRoute.getRadius() - endRoute.getRadius()) * 2;
        double y = (distance().getY() - startRoute.getRadius() - endRoute.getRadius()) * 2;
        double z = (distance().getZ() - startRoute.getRadius() - endRoute.getRadius()) * 2;
        return new Position(x, y, z);
    }

    // Accessors
    public Planet getStartRoute() {
        return startRoute;
    }

    public Planet getEndRoute() {
        return endRoute;
    }

}
