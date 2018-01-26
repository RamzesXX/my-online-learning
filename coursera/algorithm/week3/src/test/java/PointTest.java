import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PointTest {

  private Point zeroPoint;
  private Point first;
  private Point second;
  private Point third;
  private Point third2;

  @Before
  public void setUp() {
    zeroPoint = new Point(0, 0);
    first = new Point(0, 10);
    second = new Point(10, 0);
    third = new Point(10, 10);
    third2 = new Point(10, 10);
  }

  @Test
  public void toStringTest() throws Exception {
    assertEquals(zeroPoint.toString(), "[0, 0]");
  }

  @Test
  public void compareTo() throws Exception {
    assertEquals(zeroPoint.compareTo(zeroPoint), 0);
    assertEquals(zeroPoint.compareTo(first), -1);
    assertEquals(zeroPoint.compareTo(second), -1);
    assertEquals(zeroPoint.compareTo(third), -1);
    assertEquals(third2.compareTo(zeroPoint), 1);
  }

  @Test
  public void slopeTo() throws Exception {
    assertEquals(Double.NEGATIVE_INFINITY, zeroPoint.slopeTo(zeroPoint),  0.0d);
    assertEquals(Double.POSITIVE_INFINITY, zeroPoint.slopeTo(first),  0.0d);

    assertEquals(zeroPoint.slopeTo(second), +0.0d, .00001d);
    assertEquals(second.slopeTo(zeroPoint), +0.0d, .00001d);
    assertEquals(zeroPoint.slopeTo(third), 1.0d, .00001d);
    assertEquals(third2.slopeTo(zeroPoint), 1.0d, .00001d);
  }

  @Test
  public void slopeOrder() throws Exception {
  }

}