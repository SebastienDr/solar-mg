package game;

import com.google.common.collect.Lists;
import core.*;
import scene.ShapeBuilder;

import java.util.List;

import static navigation.PositionFactory.create;

public class Game {

    private Player player;
    private List<Transport> transports;
    private Route route;
    private List<Planet> planets;

    public void init() {
        player = new Player();
        Planet p = new Planet(7f, create(0, 0, 0));
        Planet p2 = new Planet(4f, create(50, 0, 0));
        planets = Lists.newArrayList(p, p2);
        route = new Route(p, p2);
        transports = Lists.newArrayList();
    }

    private TransportFactory factory() {
        return new TransportFactory();
    }

    public void update() {
        updateTransports();
    }

    public void updateTransports() {
        for (Transport t : transports) {
            t.update();
            player.removeMoney(t.getUpkeep());
            if (t.hasArrived()) {
                player.addMoney(10);
            }
        }
    }

    public void render(ShapeBuilder shapeBuilder) {
        Float[] color;
        for (Planet p : planets) {
            color = color(0.3f, 0.5f, 1f);
            shapeBuilder.newPlanet(p, color);
        }
        for (Transport t : transports) {
            shapeBuilder.newTransport(t, color(1f, 0.5f, 0f));
        }
    }

    private Float[] color(float red, float green, float blue) {
        Float[] color = new Float[3];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        return color;
    }

    public void addTransport() {
        Transport transport = factory().createTransport(route);
        if (player.getWallet().getMoney() >= transport.getPrice()) {
            player.removeMoney(transport.getPrice());
            transports.add(transport);
        }
    }

    // Accessors
    public Player getPlayer() {
        return player;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public Route getRoute() {
        return route;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

}