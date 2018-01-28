import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QueueTest {
    private Queue<String> queue;

    @Before
    public void setUp(){
        queue = new Queue<>();
        queue.enqueue("1");
        queue.enqueue("2");
    }
    @Test
    public void enqueue() {
        queue.enqueue("3");
        assertEquals(queue.size(), 3);
    }

    @Test
    public void dequeue() throws Exception {
        assertEquals(queue.size(), 2);
        assertEquals("1", queue.dequeue());
        assertEquals("2", queue.dequeue());
        assertEquals(null, queue.dequeue());
        assertEquals(null, queue.dequeue());
    }

    @Test
    public void isEmpty() throws Exception {
        assertFalse(queue.isEmpty());
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(queue.size(), 2);
        queue.dequeue();
        assertEquals(queue.size(), 1);
        queue.dequeue();
        assertEquals(queue.size(), 0);
        queue.dequeue();
        queue.dequeue();
        assertEquals(queue.size(), 0);
    }

}