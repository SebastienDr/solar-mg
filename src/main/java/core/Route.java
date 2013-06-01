package core;

import com.google.common.collect.Lists;
import navigation.Position;

import java.util.List;

/**
 * Define a route between planets
 */
public class Route {

    Planet startRoute;
    Planet endRoute;
    List<Position> positions;

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
        double x = (endRoute.getPosition().getX() - startRoute.getPosition().getX()) * 2;
        double y = (endRoute.getPosition().getY() - startRoute.getPosition().getY()) * 2;
        double z = (endRoute.getPosition().getZ() - startRoute.getPosition().getZ()) * 2;
        return new Position(x, y, z);
    }

    public List<Position> calculatePositionForTransports(int nb) {
        positions = Lists.newArrayList();
        Position startPos = startRoute.getPosition();
        Position endPos = endRoute.getPosition();
        if (nb >= 1)
            positions.add(startPos);
        if (nb >= 2) {
            Position pos = calculateAR();
            Position movement = new Position(pos.getX() / nb, pos.getY() / nb, pos.getZ() / nb);
            Position posMoved = startPos.add(movement);
            if (nb % 2 == 0)
                pair(nb, endPos, posMoved);
            else
                impair(nb, movement, posMoved);
        }

        return positions;
    }

    private void impair(int nb, Position movement, Position posMoved) {
        positions.add(posMoved);
        if (nb > 3) {
            impair(nb - 2, movement, posMoved);
        }
        positions.add(posMoved);
    }

    private void pair(int nb, Position endPos, Position posMoved) {
        if (nb == 2) {
            positions.add(endPos);
        } else {
            positions.add(posMoved);
            pair(nb - 2, endPos, posMoved);
            positions.add(posMoved);
        }
    }

    public void addPositions(Position movementToPos, int nb) {
        if (nb % 2 == 0) {
            positions.add(endRoute.getPosition());
        } else {
            positions.add(startRoute.getPosition().add(movementToPos));
            addPositions(movementToPos, nb - 2);
            positions.add(startRoute.getPosition().add(movementToPos));
        }
        startRoute.getPosition().add(movementToPos);
    }
}
