package navigation;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MathsTest {

    static Position ZERO_POSITION = new Position(0, 0, 0);

    @Test
    public void distance_between_position_zero_and_position_zero_should_be_0() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, ZERO_POSITION);
        assertThat(distance).isEqualTo(0);
    }

    @Test
    public void distance_between_position_zero_and_position001_should_be_1() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(0, 0, 1));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position010_should_be_1() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(0, 1, 0));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position100_should_be_1() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(1, 0, 0));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position_110_should_be_sqrt_2() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(1, 1, 0));
        assertThat(Double.valueOf(distance)).isEqualTo(Maths.racine(2));
    }

    @Test
    public void distance_between_position_zero_and_position_10_0_0_should_be_10() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(10, 0, 0));
        assertThat(distance).isEqualTo(10);
    }

    @Test
    public void distance_between_position_10_0_0_and_position_zero_should_be_10() throws Exception {
        double distance = Maths.distance(new Position(10, 0, 0), ZERO_POSITION);
        assertThat(distance).isEqualTo(10);
    }

    @Test
    public void distance_between_position_zero_and_position111_should_be_sqrt_3() throws Exception {
        double distance = Maths.distance(ZERO_POSITION, new Position(1, 1, 1));
        assertThat(Double.valueOf(distance)).isEqualTo(Maths.racine(3));
    }

    @Test
    public void norm_position000_should_be_position000() {
        Position norm = Maths.norm(Position.POSITION_000);
        assertThat(norm).isEqualTo(Position.POSITION_000);
    }

    @Test
    public void norm_position100_should_be_position100() {
        Position norm = Maths.norm(Position.POSITION_100);
        assertThat(norm).isEqualTo(Position.POSITION_100);
    }

    @Test
    public void norm_minus_position100_should_be_position000() {
        Position norm = Maths.norm(new Position(-1.0, 0.0, 0.0));
        assertThat(norm).isEqualTo(Position.POSITION_100);
    }
}
