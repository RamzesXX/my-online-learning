import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 */
public class Solver {

    static class Item {
        private int value;
        private int weight;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    static class Node<T> {
        int level;
        T value;
        int[] taken;

        Node(int level, T value) {
            this.level = level;
            this.value = value;
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
                        (long) n * capacity < 100_000_000L ? fillKnapsackDynamicProgramingAlgorithm() : fillKnapsackBranchAndBounds();
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
        int maxValue = Arrays.stream(items).mapToInt(item -> item.value).sum();
        BBNode start = new BBNode(maxValue, capacity, 0);
        Node<BBNode> startNode = new Node<>(-1, start);
        Node<BBNode> bestNode = new Node<>(-1, start);
        Node<BBNode>[] nodes = (Node<BBNode>[]) new Node[capacity];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node<>(i, new BBNode(0, 0, 0));
        }

        return getBest(startNode, bestNode, new int[n], nodes).taken;
    }

    Node<BBNode> getBest(Node<BBNode> current, Node<BBNode> best, int[] currentVariant, Node<BBNode>[] nodes) {
        Node<BBNode> newNode;
        int newLevel = current.level + 1;

        if (current.value.capacity < 0 || best.value.value > current.value.potential) {
            return best;
        }

        if (current.value.potential == current.value.value) {
            if (best.value.value < current.value.value) {
                best.level = current.level;

                best.value.capacity = current.value.capacity;
                best.value.value = current.value.value;
                best.value.potential = current.value.potential;

                best.taken = Arrays.copyOf(currentVariant, capacity);
            }
            return best;
        }

        if (current.value.capacity - items[newLevel].weight >= 0) {
            newNode = nodes[newLevel];

            newNode.value.potential = current.value.potential;
            newNode.value.capacity = current.value.capacity - items[newLevel].weight;
            newNode.value.value = current.value.value + items[newLevel].value;

            currentVariant[newLevel] = 1;
            best = getBest(newNode, best, currentVariant, nodes);
        }

        if (current.value.potential - items[newLevel].value > best.value.value) {

            newNode = nodes[newLevel];

            newNode.value.potential = current.value.potential - items[newLevel].value;
            newNode.value.capacity = current.value.capacity;
            newNode.value.value = current.value.value;

            currentVariant[newNode.level] = 0;
            best = getBest(newNode, best, currentVariant, nodes);
        }

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