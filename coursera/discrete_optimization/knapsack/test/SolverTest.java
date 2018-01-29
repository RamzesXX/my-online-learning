import org.junit.Before;
import org.junit.Test;

public class SolverTest {
    private Solver solver;

    @Before
    public void setUp() throws Exception {
        solver = new Solver();
    }

    @Test
    public void loadTest() throws Exception {
        solver.loadInputData("data/ks_200_1");
        int[] taken = solver.fillKnapsackBranchAndBounds();
        solver.print(taken);
    }
}