package core;

import navigation.Position;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class TransportFactoryTest {

    TransportFactory tf = new TransportFactory();


    private Route buildRoute(int s, int e) {
        Planet start = new Planet(1.0f, new Position(s + 1, 0, 0));
        Planet end = new Planet(1.0f, new Position(e - 1, 0, 0));
        return new Route(start, end);
    }

}