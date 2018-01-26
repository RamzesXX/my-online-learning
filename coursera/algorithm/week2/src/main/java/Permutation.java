import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("You should specify parameter k");
            return;
        }

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomizedQueue.enqueue(item);
        }

        if (k < 0 || k > randomizedQueue.size()) {
            System.err.println(String.format("Specified parameter k should be in [0; %d] range, but k=%d", randomizedQueue.size(), k));
            return;
        }

        Iterator<String> iterator = randomizedQueue.iterator();

        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }
}

