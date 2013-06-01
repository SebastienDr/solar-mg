package core;

import com.google.common.collect.Lists;
import navigation.Position;
import navigation.Util;

import java.util.List;


public class TransportFactory {

    List<Position> positions;
    Util util = new Util();

    public List<Transport> createTransports(Route r, int nb) {
        List<Transport> list = Lists.newArrayList();
        defineAllPositions(r, nb);
        System.out.println(positions);
        Transport t;
        for (int i = 0; i < nb; i++) {
            t = new Transport(r);
            t.setPosition(positions.get(i));
            list.add(t);
        }
        return list;
    }

    public void defineAllPositions(Route r, int nb) {
        positions = Lists.newArrayList();
        positions.addAll(r.calculatePositionForTransports(nb));
    }

    public double calculateDistance(Route r) {
        Planet end = r.getEndRoute();
        Planet start = r.getStartRoute();
        return util.distance(end.getPosition(), start.getPosition()) - end.getRadius() - start.getRadius();
    }

    public List<Position> getPositions() {
        return positions;
    }
}
