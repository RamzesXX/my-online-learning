import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static class Node<Item> {
        private final Item value;
        private Node<Item> next;
        private Node<Item> prev;

        public Node(Item value) {
            this.value = value;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> nextElement = first;

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }

        @Override
        public Item next() {
            Node<Item> currentElement;

            if (nextElement == null) {
                throw new NoSuchElementException("There is no more element");
            }

            currentElement = nextElement;
            nextElement = nextElement.next;

            return currentElement.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;

    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }

        Node<Item> node = new Node<>(item);

        if (first == null) {
            last = node;
        }
        else {
            first.prev = node;
            node.next = first;
        }

        size++;
        first = node;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }

        Node<Item> node = new Node<>(item);

        if (last == null) {
            first = node;
        }
        else {
            last.next = node;
            node.prev = last;
        }
        size++;
        last = node;
    }

    public Item removeFirst() {
        Node<Item> node = first;

        if (isEmpty()) {
            throw new NoSuchElementException("There is no more element");
        }

        first = node.next;
        if (first == null) {
            last = null;
        }
        else {
            first.prev = null;
        }
        size--;

        return node.value;
    }

    public Item removeLast() {
        Node<Item> node = last;

        if (isEmpty()) {
            throw new NoSuchElementException("There is no more element");
        }

        last = node.prev;
        if (last == null) {
            first = null;
        }
        else {
            last.next = null;
        }
        size--;

        return node.value;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        System.out.println("Deque");
    }
}
