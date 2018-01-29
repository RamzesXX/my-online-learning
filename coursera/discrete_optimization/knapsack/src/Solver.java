import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {

    private static class Item {

        private int value;
        private int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    private static class Node<T> {
        int level;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        T value;
    }

    private static class BBNode {

        int potential;
        int capacity;
        int value;

        public BBNode(int potential, int capacity, int value) {
            this.potential = potential;
            this.capacity = capacity;
            this.value = value;
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
    void solve(String[] args) throws IOException {
        for (String arg : args) {
            if (arg.startsWith("-file=")) {
                loadInputData(arg.substring(6));
                int[] solution =
                    capacity < 100_001 ? fillKnapsackDynamicProgramingAlgorithm() : fillKnapsackBranchAndBounds();
                print(solution);

                return;
            }
        }
    }

    void loadInputData(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            Scanner scanner = new Scanner(input);

            n = scanner.nextInt();
            capacity = scanner.nextInt();

            items = new Item[n];
            for (int i = 0; i < n; i++) {
                items[i] = new Item(scanner.nextInt(), scanner.nextInt());
            }
            Arrays.sort(items, Comparator.comparingDouble(item -> (-1.0d * item.value / item.weight)));
        }
    }

    // a trivial greedy algorithm for filling the knapsack
    // it takes n in-order until the knapsack is full
    int[] fillKnapsackGreedyAlgorithm() {
        int weight = 0;
        int[] taken = new int[n];

        for (int i = 0; i < n; i++) {
            taken[i] = (weight + items[i].weight <= capacity) ? 1 : 0;
            weight += items[i].weight * taken[i];
        }

        return taken;
    }

    int[] fillKnapsackBranchAndBounds() {
        int[] taken = new int[n];
        Node<BBNode> root = new Node<>();
        Node<BBNode> current, best;
        int optimisticValue = Arrays.stream(items).mapToInt(item -> item.value).sum();

        root.value = new BBNode(optimisticValue, capacity, 0);
        root.level = -1;
        best = current = root;



        if (best.value.value < current.value.potential) {
            Node<BBNode> newNode = new Node<>();
            newNode.parent = current;
            newNode.level = current.level + 1;
            if (current.value.capacity - items[newNode.level].weight >= 0) {
                newNode.value = new BBNode(current.value.potential,
                    current.value.capacity - items[newNode.level].weight,
                    current.value.value + items[newNode.level].value);
                current.left = newNode;
                current = newNode;
            } else {
                newNode.value = new BBNode(current.value.potential - items[newNode.level].value, current.value.capacity,
                    current.value.value);
                current.right = newNode;
                current = newNode;

                newNode = new Node();
                newNode.parent = current.parent;
                newNode.level = current.level;
                newNode.value = new BBNode(current.value.potential,
                    current.value.capacity - items[newNode.level].weight,
                    current.value.value + items[newNode.level].value);
                current.parent.left = newNode;
            }

            if (current.value.potential == current.value.value) {
                if (best.value.value < current.value.value) {
                    best = current;
                }
            }
        }

        return taken;
    }

    Node<BBNode> getBest(Node<BBNode> root, Node<BBNode> best) {
        return best;
    }

    // a trivial greedy algorithm for filling the knapsack
    // it takes n in-order until the knapsack is full
    int[] fillKnapsackDynamicProgramingAlgorithm() {
        Map<Integer, List<Integer>> optimalSolutions = new HashMap<>();
        int[] taken = new int[n];

        optimalSolutions.put(0, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            Map<Integer, List<Integer>> partialSolutions = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> entry : optimalSolutions.entrySet()) {
                int newWeight = entry.getKey() + items[i].weight;
                int newValue = calcValue(entry.getValue()) + items[i].value;
                int currentValue = calcValue(optimalSolutions.getOrDefault(newWeight, new ArrayList<>()));

                if ((newWeight <= capacity) && (!optimalSolutions.containsKey(newWeight) || (newValue
                    > currentValue))) {
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

    int calcValue(List<Integer> items) {
        return items.stream().mapToInt(item -> this.items[item].value).sum();
    }

    void print(int[] taken) {
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