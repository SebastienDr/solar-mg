package navigation;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UtilTest {

    static Position ZERO_POSITION = new Position(0, 0, 0);

    @Test
    public void distance_between_position_zero_and_position_zero_should_be_0() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, ZERO_POSITION);
        assertThat(distance).isEqualTo(0);
    }

    @Test
    public void distance_between_position_zero_and_position001_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(0, 0, 1));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position010_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(0, 1, 0));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position100_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(1, 0, 0));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position_zero_and_position1000_should_be_10() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(10, 0, 0));
        assertThat(distance).isEqualTo(10);
    }

    @Test
    public void distance_between_position_1000_and_position_zero_should_be_10() throws Exception {
        Util util = new Util();
        double distance = util.distance(new Position(10, 0, 0), ZERO_POSITION);
        assertThat(distance).isEqualTo(10);
    }

    @Test
    public void distance_between_position_zero_and_position111_should_be_sqrt_3() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(1, 1, 1));
        assertThat(Double.valueOf(distance)).isEqualTo(Math.sqrt(3));
    }
}
