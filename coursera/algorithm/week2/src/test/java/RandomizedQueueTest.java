import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    private RandomizedQueue<String> randomQueue;
    private RandomizedQueue<String> emptyRandomQueue;
    private Iterator<String> randomQueueIterator;

    @Before
    public void setUp(){
        emptyRandomQueue = new RandomizedQueue<>();
        randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue("1");
        randomQueue.enqueue("2");
        randomQueue.enqueue("3");
        randomQueueIterator = randomQueue.iterator();
    }

    private RandomizedQueue<String> getQueueFromFile(String fileName) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        try (InputStream inputStream = PermutationTest.class.getClassLoader().getResourceAsStream("queues/tale.txt")) {
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()) {
                String item = scanner.next();
                randomizedQueue.enqueue(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return randomizedQueue;
    }

    @Test
    public void bigFile(){
        RandomizedQueue<String> randomizedQueue = getQueueFromFile("queues/tale.txt");

        System.out.println(randomizedQueue.size());
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(emptyRandomQueue.isEmpty());
        assertFalse(randomQueue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, emptyRandomQueue.size());
        assertEquals(3, randomQueue.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void enqueue() throws Exception {
        randomQueue.enqueue("4");
        assertEquals(4, randomQueue.size());
        randomQueue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeue() throws Exception {
        assertNotNull(randomQueue.dequeue());
        assertNotNull(randomQueue.dequeue());
        assertNotNull(randomQueue.dequeue());
        assertEquals(0, randomQueue.size());
        assertTrue(randomQueue.isEmpty());
        randomQueue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void sample() throws Exception {
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        System.out.println(randomQueue.sample());
        assertEquals(3, randomQueue.size());
        emptyRandomQueue.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemove() throws Exception {
        randomQueueIterator.remove();
    }


    @Test(expected = NoSuchElementException.class)
    public void iterator() throws Exception {
        assertTrue(randomQueueIterator.hasNext());
        assertNotNull(randomQueueIterator.next());
        assertTrue(randomQueueIterator.hasNext());
        assertNotNull(randomQueueIterator.next());
        assertTrue(randomQueueIterator.hasNext());
        assertNotNull(randomQueueIterator.next());
        assertFalse(randomQueueIterator.hasNext());
        randomQueueIterator.next();
    }


}