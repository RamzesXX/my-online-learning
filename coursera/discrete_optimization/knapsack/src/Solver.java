import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {
    private int items;
    private int capacity;
    private int[] values;
    private int[] weights;

    public static void main(String[] args) {
        try {
            Solver solver = new Solver();
            solver.solve(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read the instance, solve it, and print the solution in the standard output
     */
    private void solve(String[] args) throws IOException {
        for (String arg : args) {
            if (arg.startsWith("-file=")) {
                loadInputData(arg.substring(6));
                int[] solution = fillKnapsackDynamicProgramingAlgorithm();
                print(solution);

                return;
            }
        }
    }

    private void loadInputData(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            Scanner scanner = new Scanner(input);

            items = scanner.nextInt();
            capacity = scanner.nextInt();

            values = new int[items];
            weights = new int[items];
            for (int i = 0; i < items; i++) {
                values[i] = scanner.nextInt();
                weights[i] = scanner.nextInt();
            }
        }
    }

    // a trivial greedy algorithm for filling the knapsack
    // it takes items in-order until the knapsack is full
    private int[] fillKnapsackGreedyAlgorithm() {
        int weight = 0;
        int[] taken = new int[items];

        for (int i = 0; i < items; i++) {
            taken[i] = (weight + weights[i] <= capacity) ? 1 : 0;
            weight += weights[i] * taken[i];
        }

        return taken;
    }

    // a trivial greedy algorithm for filling the knapsack
    // it takes items in-order until the knapsack is full
    private int[] fillKnapsackDynamicProgramingAlgorithm() {
        Map<Integer, List<Integer>> optimalSolutions = new HashMap<>();
        int[] taken = new int[items];

        optimalSolutions.put(0, new ArrayList<>());
        for (int i = 0; i < items; i++) {
            Map<Integer, List<Integer>> partialSolutions = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> entry : optimalSolutions.entrySet()) {
                int newWeight = entry.getKey() + weights[i];
                int newValue = calcValue(entry.getValue()) + values[i];
                int currentValue = calcValue(optimalSolutions.getOrDefault(newWeight, new ArrayList<>()));

                if ((newWeight <= capacity) && (!optimalSolutions.containsKey(newWeight) || (newValue > currentValue))){
                    List<Integer> list = new ArrayList<>(entry.getValue());
                    list.add(i);
                    partialSolutions.put(newWeight, list);
                }
            }

            optimalSolutions.putAll(partialSolutions);
        }

        optimalSolutions.values().stream()
                .max(Comparator.comparingInt(this::calcValue)).orElse(new ArrayList<>())
                .forEach(i -> taken[i] = 1);
        return taken;
    }

    private int calcValue(List<Integer> items) {
        return items.stream().mapToInt(item -> values[item]).sum();
    }

    private void print(int[] taken) {
        int value = 0;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < items; i++) {
            value += taken[i] * values[i];
            builder.append(taken[i]).append(" ");
        }

        System.out.println(value + " 0");
        System.out.println(builder.toString());
        System.out.println("");
    }
}