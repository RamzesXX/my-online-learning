import com.khanchych.coursera.discreate.optimisation.coloring.Coloring;
import com.khanchych.coursera.discreate.optimisation.coloring.ColoringGraph;
import com.khanchych.coursera.discreate.optimisation.coloring.ProblemInput;
import com.khanchych.coursera.discreate.optimisation.coloring.Util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> outputSolution;
        int value;

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
        value = Collections.max(solution);
        outputSolution = solution.stream().map(String::valueOf).collect(Collectors.toList());

        // prepare the solution in the specified output format
        System.out.println(value + " 1");
        System.out.println(String.join(" ", outputSolution));
        System.out.println();
    }
}