import com.khanchych.coursera.discreate.optimisation.coloring.Coloring;
import com.khanchych.coursera.discreate.optimisation.coloring.ColoringGraph;
import com.khanchych.coursera.discreate.optimisation.coloring.ProblemInput;
import com.khanchych.coursera.discreate.optimisation.coloring.Util;

import java.io.IOException;
import java.util.List;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {

    /**
     * The main class
     */
    public static void main(String[] args) {
        try {
            solve(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the instance, solve it, and print the solution in the standard output
     */
    public static void solve(String[] args) throws IOException {
        String fileName = null;
        ProblemInput problemInput;
        Coloring coloring;
        List<Integer> solution;

        // get the temp file name
        for (String arg : args) {
            if (arg.startsWith("-file=")) {
                fileName = arg.substring(6);
            }
        }
        if (fileName == null) {
            return;
        }

        problemInput = Util.getProblemFromFile(fileName);
        coloring = new ColoringGraph();
        solution = coloring.solve(problemInput);
        System.out.println(Util.getAmountOfColors(solution) + " 1");
        System.out.println(Util.getSolutionString(solution));
    }
}