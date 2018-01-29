import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {
    static class Item {
        private int value;
        private int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    static class BBNode {
        int potential;
        int capacity;
        int value;

        public BBNode(int potential, int capacity, int value) {
            this.potential = potential;
            this.capacity = capacity;
            this.value = value;
        }

        public int getPotential() {
            return potential;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getValue() {
            return value;
        }
    }

    private int n;
    private int capacity;
    private Item[] items;

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
                int[] solution = capacity < 100_001 ? fillKnapsackDynamicProgramingAlgorithm() : fillKnapsackBranchAndBounds();
                print(solution);

                return;
            }
        }
    }

    private void loadInputData(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            Scanner scanner = new Scanner(input);

            n = scanner.nextInt();
            capacity = scanner.nextInt();

            items = new Item[n];
            for (int i = 0; i < n; i++) {
                items[i] = new Item(scanner.nextInt(), scanner.nextInt());
            }
            Arrays.sort(items, Comparator.comparingDouble(item -> ( -1.0d * item.value / item.weight)));
        }
    }
    // a trivial greedy algorithm for filling the knapsack
    // it takes n in-order until the knapsack is full
    private int[] fillKnapsackGreedyAlgorithm() {
        int weight = 0;
        int[] taken = new int[n];

        for (int i = 0; i < n; i++) {
            taken[i] = (weight + items[i].weight <= capacity) ? 1 : 0;
            weight += items[i].weight * taken[i];
        }

        return taken;
    }

    private int[] fillKnapsackBranchAndBounds() {
        int[] taken = new int[n];



        return taken;
    }

    // a trivial greedy algorithm for filling the knapsack
    // it takes n in-order until the knapsack is full
    private int[] fillKnapsackDynamicProgramingAlgorithm() {
        Map<Integer, List<Integer>> optimalSolutions = new HashMap<>();
        int[] taken = new int[n];

        optimalSolutions.put(0, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            Map<Integer, List<Integer>> partialSolutions = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> entry : optimalSolutions.entrySet()) {
                int newWeight = entry.getKey() + items[i].weight;
                int newValue = calcValue(entry.getValue()) + items[i].value;
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
        return items.stream().mapToInt(item -> this.items[item].value).sum();
    }

    private void print(int[] taken) {
        int value = 0;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            value += taken[i] * items[i].value;
            builder.append(taken[i]).append(" ");
        }

        System.out.println(value + " 1");
        System.out.println(builder.toString());
        System.out.println("");
    }
}