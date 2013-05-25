package core;

/**
 * Created with IntelliJ IDEA.
 * User: sdrouard
 *
 * Define a route between planets
 */
public class Route {

    Planet startRoute;
    Planet endRoute;

    public Route(Planet startRoute, Planet endRoute) {
        this.startRoute = startRoute;
        this.endRoute = endRoute;
    }

    // Accessors
    public Planet getStartRoute() {
        return startRoute;
    }

    public Planet getEndRoute() {
        return endRoute;
    }

}
