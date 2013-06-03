package core;

import com.google.common.collect.Lists;
import navigation.Position;

import java.util.List;

import static core.TransportStatus.CARRYING;
import static core.TransportStatus.RETURNING;

/**
 * Define a route between planets
 */
public class Route {

    Planet startRoute;
    Planet endRoute;
    List<RouteDirection> positions;

    public Route(Planet startRoute, Planet endRoute) {
        this.startRoute = startRoute;
        this.endRoute = endRoute;
    }

    public Position calculatePositionDifference() {
        double x = endRoute.getPosition().getX() - startRoute.getPosition().getX();
        double y = endRoute.getPosition().getY() - startRoute.getPosition().getY();
        double z = endRoute.getPosition().getZ() - startRoute.getPosition().getZ();
        return new Position(x, y, z);
    }

    // Accessors
    public Planet getStartRoute() {
        return startRoute;
    }

    public Planet getEndRoute() {
        return endRoute;
    }

    public Position calculateAR() {
        double x = (endRoute.getPosition().getX() - startRoute.getPosition().getX() - startRoute.getRadius() - endRoute.getRadius()) * 2;
        double y = (endRoute.getPosition().getY() - startRoute.getPosition().getY()) * 2;
        double z = (endRoute.getPosition().getZ() - startRoute.getPosition().getZ()) * 2;
        return new Position(x, y, z);
    }

    public List<RouteDirection> calculatePositionForTransports(int nb) {
        positions = Lists.newArrayList();
        Position startPos = startRoute.getPosition().add(new Position((double) startRoute.getRadius(), 0.0, 0.0));
        Position endPos = endRoute.getPosition().minus(new Position((double) endRoute.getRadius(), 0.0, 0.0));
        if (nb >= 1)
            positions.add(new RouteDirection(startPos, CARRYING));
        if (nb >= 2) {
            Position aR = calculateAR();
            System.out.println("AR : " + aR);
            Position movement = new Position(aR.getX() / nb, aR.getY() / nb, aR.getZ() / nb);
            System.out.println("Distance divisée par " + nb + " : " + movement);
            Position newPos = startPos.add(movement);
            if (nb % 2 == 0)
                pair(nb, movement, endPos, newPos);
            else
                impair(nb, movement, newPos);
        }

        return positions;
    }

    private void impair(int nb, Position movement, Position newPos) {
        positions.add(new RouteDirection(newPos, CARRYING));
        if (nb > 3) {
            impair(nb - 2, movement, newPos.add(movement));
        }
        positions.add(new RouteDirection(newPos, RETURNING));
    }

    private void pair(int nb, Position movement, Position endPos, Position newPos) {
        if (nb == 2) {
            positions.add(new RouteDirection(endPos, RETURNING));
        } else {
            positions.add(new RouteDirection(newPos, CARRYING));
            pair(nb - 2, movement, endPos, newPos.add(movement));
            positions.add(new RouteDirection(newPos, RETURNING));
        }
    }

    public void addPositions(Position movementToPos, int nb) {
        if (nb % 2 == 0) {
            positions.add(new RouteDirection(endRoute.getPosition(), CARRYING));
        } else {
            positions.add(new RouteDirection(startRoute.getPosition().add(movementToPos), CARRYING));
            addPositions(movementToPos, nb - 2);
            positions.add(new RouteDirection(startRoute.getPosition().add(movementToPos), RETURNING));
        }
        startRoute.getPosition().add(movementToPos);
    }
}
