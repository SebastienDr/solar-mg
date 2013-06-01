package core;

import navigation.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class RouteTest {

    Route r;

    @Test
    public void should_position_difference_be_639() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position position = r.calculatePositionDifference();
        assertThat(position).isEqualTo(new Position(6, 3, 9));
    }

    @Test
    public void should_position_difference_be_555() {
        Planet start = new Planet(1.0f, new Position(0, 0, 0));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position position = r.calculatePositionDifference();
        assertThat(position).isEqualTo(new Position(5, 5, 5));
    }

    @Test
    public void should_calculate_AR_return_12_6_18() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        Position ar = r.calculateAR();
        assertThat(ar).isEqualTo(new Position(12, 6, 18));
    }

    @Test
    public void should_calculate_position_for_2_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(2);
        assertThat(positions).hasSize(2);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(1)).isEqualTo(new Position(5, 5, 5));
    }

    @Test
    public void should_calculate_position_for_3_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(3);
        assertThat(positions).hasSize(3);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(1)).isEqualTo(new Position(3, 4, 2));
        assertThat(positions.get(2)).isEqualTo(new Position(3, 4, 2));
    }

    @Test
    public void should_calculate_position_for_4_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(4);
        assertThat(positions).hasSize(4);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(1)).isEqualTo(new Position(2.0, 3.5, 0.5));
        assertThat(positions.get(2)).isEqualTo(new Position(5, 5, 5));
        assertThat(positions.get(3)).isEqualTo(positions.get(1));
    }

    @Test
    public void should_calculate_position_for_5_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(5);
        assertThat(positions).hasSize(5);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(1)).isEqualTo(positions.get(4));
        assertThat(positions.get(2)).isEqualTo(positions.get(3));
    }

    @Test
    public void should_calculate_position_for_6_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(6);
        assertThat(positions).hasSize(6);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(3)).isEqualTo(new Position(5, 5, 5));
        assertThat(positions.get(1)).isEqualTo(positions.get(5));
        assertThat(positions.get(2)).isEqualTo(positions.get(4));
    }

    @Test
    public void should_calculate_position_for_7_transports() {
        Planet start = new Planet(1.0f, new Position(-1, 2, -4));
        Planet end = new Planet(1.0f, new Position(5, 5, 5));
        r = new Route(start, end);

        List<Position> positions = r.calculatePositionForTransports(7);
        assertThat(positions).hasSize(7);
        assertThat(positions.get(0)).isEqualTo(new Position(-1, 2, -4));
        assertThat(positions.get(1)).isEqualTo(positions.get(6));
        assertThat(positions.get(2)).isEqualTo(positions.get(5));
        assertThat(positions.get(3)).isEqualTo(positions.get(4));
    }
}