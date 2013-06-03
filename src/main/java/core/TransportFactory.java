package core;

import com.google.common.collect.Lists;
import navigation.Util;

import java.util.List;


public class TransportFactory {

    List<RouteDirection> routeDirections;
    Util util = new Util();

    public List<Transport> createTransports(Route r, int nb) {
        List<Transport> list = Lists.newArrayList();
        defineAllPositions(r, nb);
        System.out.println(routeDirections);
        for (int i = 0; i < nb; i++) {
            Transport t = new Transport(r);
            RouteDirection route = routeDirections.get(i);
            t.setPosition(route.getPosition());
            t.setStatus(route.getDirection());
            list.add(t);
        }
        return list;
    }

    public void defineAllPositions(Route r, int nb) {
        routeDirections = Lists.newArrayList(r.calculatePositionForTransports(nb));
    }

    public double calculateDistance(Route r) {
        Planet end = r.getEndRoute();
        Planet start = r.getStartRoute();
        return util.distance(end.getPosition(), start.getPosition()) - end.getRadius() - start.getRadius();
    }

    public List<RouteDirection> getRouteDirections() {
        return routeDirections;
    }
}
