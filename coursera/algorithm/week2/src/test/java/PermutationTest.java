import org.junit.Test;

import java.util.Iterator;
import java.util.Scanner;

public class PermutationTest {
    @Test
    public void main() throws Exception {
        int k = 15;
        Scanner scanner = new Scanner(PermutationTest.class.getClassLoader().getResourceAsStream("queues/mediumTale.txt"));
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (scanner.hasNext()) {
            String item = scanner.next();
            randomizedQueue.enqueue(item);
        }

        Iterator<String> iterator = randomizedQueue.iterator();

        for (int i = 0; i < k; i++) {
            System.out.println(iterator.next());
        }
    }

}