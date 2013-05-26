package navigation;

import static org.fest.assertions.Assertions.*;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sdrouard
 * Date: 26/05/13
 * Time: 01:28
 * To change this template use File | Settings | File Templates.
 */
public class UtilTest {

    static Position ZERO_POSITION = new Position(0, 0, 0);

    @Test
    public void distance_between_position000_and_position000_should_be_0() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, ZERO_POSITION);
        assertThat(distance).isEqualTo(0);
    }

    @Test
    public void distance_between_position000_and_position001_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(0, 0, 1));
        assertThat(distance).isEqualTo(1);
    }
    @Test
    public void distance_between_position000_and_position010_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(0, 1, 0));
        assertThat(distance).isEqualTo(1);
    }
    @Test
    public void distance_between_position000_and_position100_should_be_1() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(1, 0, 0));
        assertThat(distance).isEqualTo(1);
    }

    @Test
    public void distance_between_position000_and_position111_should_be_sqrt_3() throws Exception {
        Util util = new Util();
        double distance = util.distance(ZERO_POSITION, new Position(1, 1, 1));
        assertThat(Double.valueOf(distance)).isEqualTo(Math.sqrt(3));
    }
}
