import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {
    private Deque<String> deque;
    private Deque<String> emptyDeque;
    Iterator<String> dequeIterator;
    Iterator<String> emptyDequeIterator;

    @Before
    public void setUp(){
        deque = new Deque<>();
        emptyDeque = new Deque<>();
        deque.addFirst("1");
        deque.addLast("2");
        dequeIterator = deque.iterator();
        emptyDequeIterator = emptyDeque.iterator();
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(emptyDeque.isEmpty());
        assertFalse(deque.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(emptyDeque.size(), 0);
        assertEquals(deque.size(), 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addFirst() throws Exception {
        emptyDeque.addFirst("2");
        assertEquals(1, emptyDeque.size());
        emptyDeque.addFirst("1");
        assertEquals(2, emptyDeque.size());
        deque.addFirst("0");
        assertEquals(3, deque.size());
        deque.addFirst(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addLast() throws Exception {
        emptyDeque.addLast("1");
        assertEquals(1, emptyDeque.size());
        emptyDeque.addLast("2");
        assertEquals(2, emptyDeque.size());
        deque.addLast("3");
        assertEquals(3, deque.size());
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirst() throws Exception {
        assertEquals("1", deque.removeFirst());
        assertEquals("2", deque.removeFirst());
        assertEquals(0, deque.size());
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLast() throws Exception {
        assertEquals("2", deque.removeLast());
        assertEquals("1", deque.removeLast());
        assertEquals(0, deque.size());
        deque.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemove() throws Exception {
        dequeIterator.remove();
    }


    @Test(expected = NoSuchElementException.class)
    public void iterator() throws Exception {
        assertTrue(dequeIterator.hasNext());
        assertEquals("1", dequeIterator.next());
        assertTrue(dequeIterator.hasNext());
        assertEquals("2", dequeIterator.next());
        assertFalse(dequeIterator.hasNext());
        dequeIterator.next();
    }

}