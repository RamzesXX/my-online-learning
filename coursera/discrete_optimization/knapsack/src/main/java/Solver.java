import com.khanchych.coursera.discreate.optimisation.knapsack.Item;
import com.khanchych.coursera.discreate.optimisation.knapsack.Knapsack;
import com.khanchych.coursera.discreate.optimisation.knapsack.ProblemInput;
import com.khanchych.coursera.discreate.optimisation.knapsack.bb.KnapsackBB;
import com.khanchych.coursera.discreate.optimisation.knapsack.dp.KnapsackDP_1;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 *
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
        Knapsack knapsack;
        List<Item> solution;
        List<String> outputSolution;
        int value;
        
        // get the temp file name
        for(String arg : args){
            if(arg.startsWith("-file=")){
                fileName = arg.substring(6);
            } 
        }
        if(fileName == null)
            return;

        problemInput = getProblemFromFile(fileName);
        if (problemInput.getCapacity() > 10_000) {
            knapsack = new KnapsackBB();
        }
        else {
            knapsack = new KnapsackDP_1();
        }

        solution = knapsack.solve(problemInput);
        value = 0;
        outputSolution = Stream.iterate("0", (element) -> "0")
                .limit(problemInput.getItems().size())
                .collect(Collectors.toList());

        for (Item item:solution) {
            value += item.getValue();
            outputSolution.set(item.getIndex(), "1");
        }

        // prepare the solution in the specified output format
        System.out.println(value+" 1");
        System.out.println( String.join(" ", outputSolution));
        System.out.println();
    }

    private static ProblemInput getProblemFromFile(String fileName) throws IOException {
        int amount;
        int capacity;
        int index = 0;
        List<Item> items = new ArrayList<>();

        try (
                BufferedReader bufferedReader =  new BufferedReader(new FileReader(fileName));
                Scanner scanner = new Scanner(bufferedReader)
        ){
            amount = scanner.nextInt();
            capacity = scanner.nextInt();

            while (scanner.hasNext()) {
                int value = scanner.nextInt();
                int weight = scanner.nextInt();

                items.add(new Item(index++, value, weight));
            }
        }

        return new ProblemInput(items, capacity);
    }
}