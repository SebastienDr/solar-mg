package core;

import navigation.Position;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class TransportFactoryTest {

    TransportFactory tf = new TransportFactory();

    @Test
    public void should_defineAllPositions_with_0_set_no_position() {
        Route r = buildRoute(0, 10);
        tf.defineAllPositions(r, 0);
        assertThat(tf.getPositions()).hasSize(0);
    }

    @Test
    public void should_defineAllPositions_with_1_set_position_to_start_route_position() {
        Route r = buildRoute(0, 7);
        tf.defineAllPositions(r, 1);
        assertThat(tf.getPositions()).hasSize(1);
        assertThat(tf.getPositions().get(0)).isEqualTo(r.getStartRoute().getPosition());
    }

    @Test
    public void should_defineAllPositions_with_2_set_position_to_start_and_end_route_position() {
        Route r = buildRoute(0, 7);
        tf.defineAllPositions(r, 2);
        assertThat(tf.getPositions().size()).isEqualTo(2);
        assertThat(tf.getPositions().get(0)).isEqualTo(r.getStartRoute().getPosition());
        assertThat(tf.getPositions().get(1)).isEqualTo(r.getEndRoute().getPosition());
    }

    @Test
    public void should_defineAllPositions_with_4_set_position_to_start_and_each_third_position() {
        Route r = buildRoute(0, 7);
        tf.defineAllPositions(r, 4);
        assertThat(tf.getPositions().size()).isEqualTo(4);
        assertThat(tf.getPositions().get(0)).isEqualTo(r.getStartRoute().getPosition());
        assertThat(tf.getPositions().get(1)).isEqualTo(new Position(3.5, 0.0, 0.0));
        assertThat(tf.getPositions().get(2)).isEqualTo(new Position(6.0, 0.0, 0.0));
        assertThat(tf.getPositions().get(3)).isEqualTo(new Position(3.5, 0.0, 0.0));
    }

    @Test
    public void should_calculateDistance_between_planet_at_000_and_planet_at_1000_with_both_radius_1_be_8() {
        Planet p1 = new Planet(1.0f, new Position(0.0, 0.0, 0.0));
        Planet p2 = new Planet(1.0f, new Position(10.0, 0.0, 0.0));
        Route r = new Route(p1, p2);
        double v = tf.calculateDistance(r);
        assertThat(v).isEqualTo(8.0);
    }

    @Test
    public void should_calculateDistance_between_planet_at_055_and_planet_at_1024_be_8_4880() {
        Planet p1 = new Planet(1.0f, new Position(0.0, 5.0, 5.0));
        Planet p2 = new Planet(1.0f, new Position(10.0, 2.0, 4.0));
        Route r = new Route(p1, p2);
        double v = tf.calculateDistance(r);
        assertThat(v).isEqualTo(8.488088481701515);
    }

    private Route buildRoute(int s, int e) {
        Planet start = new Planet(1.0f, new Position(s + 1, 0, 0));
        Planet end = new Planet(1.0f, new Position(e - 1, 0, 0));
        return new Route(start, end);
    }

}