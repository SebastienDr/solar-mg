package navigation;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionTest {

    private static final Position POSITION_200 = new Position(2.0, 0.0, 0.0);
    private static final Position POSITION_100 = new Position(1.0, 0.0, 0.0);
    private static final Position POSITION_010 = new Position(0.0, 1.0, 0.0);
    private static final Position POSITION_001 = new Position(0.0, 0.0, 1.0);
    private static final Position POSITION_111 = POSITION_100.add(POSITION_010).add(POSITION_001);
    private static final Position POSITION_000 = new Position(0.0, 0.0, 0.0);
    private static final Position DIRECTION_100 = new Position(1.0, 0.0, 0.0);
    private static final Position NO_DIRECTION = new Position(0.0, 0.0, 0.0);
    private static final Position DIRECTION_010 = new Position(0.0, 1.0, 0.0);
    private static final Position DIRECTION_001 = new Position(0.0, 0.0, 1.0);
    private static final Position DIRECTION_101 = DIRECTION_001.add(DIRECTION_100);

    @Test
    public void should_BigDecimal_rounding_return_what_we_expect() {
        BigDecimal bigDecimal = BigDecimal.valueOf(1.002511561478612316551).setScale(6, RoundingMode.HALF_UP);
        assertThat(bigDecimal.toString()).isEqualTo("1.002512");

        bigDecimal = BigDecimal.valueOf(1.002511561478612316551).setScale(6, RoundingMode.DOWN);
        assertThat(bigDecimal.toString()).isEqualTo("1.002511");

        bigDecimal = BigDecimal.valueOf(1.002511561478612316551).setScale(12, RoundingMode.HALF_UP);
        assertThat(bigDecimal.toString()).isEqualTo("1.002511561479");

        double result = BigDecimal.valueOf(1.002511561478612316551).setScale(12, RoundingMode.HALF_UP).doubleValue();
        assertThat(result).isEqualTo(1.002511561479);
    }

    @Test
    public void should_position_be_close_of_itself() {
        assertThat(POSITION_100.isCloseTo(POSITION_100)).isTrue();
    }

    @Test
    public void should_position_100_be_close_of_position_000() {
        assertThat(position(0.1,0.0,0.0).isCloseTo(POSITION_000)).isTrue();
    }

    @Test
    public void should_position_010_be_close_of_position_000() {
        assertThat(position(0.0,0.1,0.0).isCloseTo(POSITION_000)).isTrue();
    }

    @Test
    public void should_position_001_be_close_of_position_000() {
        assertThat(position(0.0,0.0,0.1).isCloseTo(POSITION_000)).isTrue();
    }

    @Test
    public void should_position_111_be_close_of_position_000() {
        assertThat(position(0.1,0.1,0.1).isCloseTo(POSITION_000)).isTrue();
    }

    @Test
    public void should_position_200_not_be_close_of_position_000() {
        assertThat(position(0.2,0.0,0.0).isCloseTo(POSITION_000)).isFalse();
    }

    @Test
    public void should_toSpherical_works() {
        assertThat(position(0.2,0.0,0.0).toSpherical()).isEqualTo(position(0.2,1.5707963267948966,0.0));
    }

    @Test
    public void should_toSpherical_works2() {
        assertThat(position(1.0,1.0,1.0).toSpherical()).isEqualTo(position(1.7320508075688772,0.9553166181245092,0.7853981633974483));
    }

    // Helpers
    private Position position(double x, double y, double z) {
        return new Position(x, y, z);
    }

}