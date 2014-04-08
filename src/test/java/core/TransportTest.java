package core;

import navigation.Position;
import org.junit.Before;
import org.junit.Test;

import static core.TransportStatus.*;
import static navigation.Position.POSITION_000;
import static navigation.Position.POSITION_100;
import static org.fest.assertions.api.Assertions.assertThat;

public class TransportTest {

    Transport transport;

    @Before
    public void setup() {
        Route route = route(POSITION_000, POSITION_100);
        transport = new TransportFactory().createTransport(route);
    }

    @Test
    public void should_afterHalfway_be_false_for_going_and_position_be_01_0_0() {
        transport(GOING, position(0.1, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isFalse();
    }

    @Test
    public void should_afterHalfway_be_true_for_going_and_position_be_600() {
        transport(GOING, position(0.6, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isTrue();
    }

    @Test
    public void should_afterHalfway_be_true_if_returning_and_position_be_300() {
        transport(RETURNING, position(0.3, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isTrue();
    }

    @Test
    public void should_afterHalfway_be_false_if_returning_and_position_be_halfway() {
        transport(RETURNING, position(0.5, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isFalse();
    }

    @Test
    public void should_afterHalfway_be_false_if_going_and_position_be_halfway() {
        transport(GOING, position(0.5, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isFalse();
    }

    @Test
    public void should_afterHalfway_be_false_if_returning_and_position_be_600() {
        transport(RETURNING, position(0.6, 0.0, 0.0));
        assertThat(transport.afterHalfway()).isFalse();
    }

    @Test
    public void should_update_while_GOING_increase_transport_position() {
        transport(GOING, position(0.3, 0.0, 0.0));
        // When
        transport.update();
        // Then
        assertThat(transport.getPosition().getX()).isGreaterThan(0.3);
    }

    @Test
    public void should_update_while_RETURNING_decrease_transport_position() {
        transport(RETURNING, position(0.3, 0.0, 0.0));
        // When
        transport.update();
        // Then
        assertThat(transport.getPosition().getX()).isLessThan(0.3);
    }

    @Test
    public void should_update_when_transport_has_reached_target_change_status_to_DELIVERING() {
        transport(GOING, POSITION_100);
        transport.setResources(Transport.MAX_CAPACITY);
        // When
        transport.update();
        // Then
        assertThat(transport.getStatus()).isEqualTo(DELIVERING);
        assertThat(transport.getResources()).isEqualTo(Transport.MAX_CAPACITY);
    }

    @Test
    public void should_update_keep_status_to_DELIVERING_and_reduce_resources_if_transport_has_not_finished_delivering() {
        transport(DELIVERING, POSITION_100);
        transport.setResources(30);
        // When
        transport.update();
        // Then
        assertThat(transport.getStatus()).isEqualTo(DELIVERING);
        assertThat(transport.getResources()).isEqualTo(29);
    }

    @Test
    public void should_update_when_transport_has_finished_DELIVERING_change_status_to_RETURNING() {
        transport(DELIVERING, POSITION_100);
        transport.setResources(0);
        // When
        transport.update();
        // Then
        assertThat(transport.getStatus()).isEqualTo(RETURNING);
        assertThat(transport.getDirection()).isEqualTo(position(-1.0, 0.0, 0.0));
        assertThat(transport.getPosition()).isEqualTo(POSITION_100);
    }

    @Test
    public void should_update_when_transport_has_reached_start_change_status_to_LOADING() {
        transport(RETURNING, POSITION_000);
        transport.update();
        assertThat(transport.getStatus()).isEqualTo(LOADING);
    }

    @Test
    public void should_update_move_transport_if_RETURNING_from_target() {
        transport(RETURNING, POSITION_100);
        // When
        transport.update();
        // Then
        assertThat(transport.getStatus()).isEqualTo(RETURNING);
        assertThat(transport.getPosition().getX()).isLessThan(1.0);
    }

    @Test
    public void should_update_keep_status_to_LOADING_and_increase_resources_if_transport_has_not_finished_loading() {
        transport(LOADING, POSITION_000);
        transport.setResources(30);
        transport.update();
        assertThat(transport.getStatus()).isEqualTo(LOADING);
        assertThat(transport.getResources()).isEqualTo(31);
    }

    @Test
    public void should_update_when_transport_has_finished_LOADING_change_status_to_GOING() {
        transport(LOADING, POSITION_000);
        transport.setResources(Transport.MAX_CAPACITY);
        // When
        transport.update();
        // Then
        assertThat(transport.getStatus()).isEqualTo(GOING);
        assertThat(transport.getDirection()).isEqualTo(POSITION_100);
        assertThat(transport.getPosition()).isEqualTo(POSITION_000);
    }


    // Helpers
    private Route route(Position start, Position end) {
        return new Route(new Planet(1.0f, start), new Planet(1.0f, end));
    }

    private void transport(TransportStatus status, Position position) {
        if (status == GOING) {
            transport.setDirection(POSITION_100);
        }
        if (status == RETURNING) {
            transport.setDirection(position(-1.0, 0.0, 0.0));
        }
        transport.setPosition(position);
        transport.setStatus(status);
    }

    private Position position(double x, double y, double z) {
        return new Position(x, y, z);
    }
}
