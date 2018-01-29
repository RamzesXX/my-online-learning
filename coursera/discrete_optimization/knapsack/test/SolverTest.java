import org.junit.Test;
import org.junit.Before;

public class SolverTest {
  private Solver solver;

  @Before
  public void setUp() throws Exception {
    solver = new Solver();
  }

  @Test
  public void loadTest() throws Exception {
    solver.loadInputData("data/ks_4_0");
    solver.fillKnapsackBranchAndBounds();
  }
}