import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 2;

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int notIteratedElements;

        private int[] itemsForIteration = new int[size];

        public RandomizedQueueIterator() {
            notIteratedElements = size;
            for (int i = 0; i < notIteratedElements; i++) {
                itemsForIteration[i] = i;
            }
        }

        @Override
        public boolean hasNext() {
            return notIteratedElements > 0;
        }

        @Override
        public Item next() {
            if (notIteratedElements == 0) {
                throw new NoSuchElementException("There is no more element");
            }

            int position = StdRandom.uniform(notIteratedElements);
            int queuePosition = itemsForIteration[position];

            itemsForIteration[position] = itemsForIteration[notIteratedElements - 1];
            notIteratedElements--;

            return randomizedQueue[queuePosition];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

    }
    private int size;

    private Item[]  randomizedQueue;

    public RandomizedQueue() {
        size = 0;
        randomizedQueue = (Item[]) new Object[INIT_SIZE];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= size;

        Item[] temp = randomizedQueue;
        randomizedQueue = (Item[]) new Object[capacity];
        System.arraycopy(temp, 0, randomizedQueue, 0, size);
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }

        randomizedQueue[size++] = item;

        if (size == randomizedQueue.length) {
            resize(size * 2);
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no more element");
        }

        int position = StdRandom.uniform(size);
        Item item = randomizedQueue[position];
        randomizedQueue[position] = randomizedQueue[size - 1];
        randomizedQueue[size - 1] = null;
        size--;
        if (size > 0 && size == randomizedQueue.length / 4) {
            resize(randomizedQueue.length / 2);
        }

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("There is no more element");
        }

        return randomizedQueue[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
    }
}
