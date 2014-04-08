package core;

import navigation.Position;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;


public class RouteTest {

    Route r;

    @Test
    public void should_distance_be_639() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position position = r.distance();
        assertThat(position).isEqualTo(new Position(6, 3, 9));
    }

    @Test
    public void should_distance_be_555() {
        Planet start = new Planet(1.0f, new Position(0, 0, 0));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position position = r.distance();
        assertThat(position).isEqualTo(new Position(5, 5, 5));
    }

    @Test
    public void should_calculate_AR_return_8_2_14() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position ar = r.calculateAR();
        assertThat(ar).isEqualTo(new Position(8, 2, 14));
    }

}