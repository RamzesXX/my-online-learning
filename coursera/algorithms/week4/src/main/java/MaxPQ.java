public class MaxPQ<Key extends Comparable<Key>> {
    private int n;
    private Key[] pq;

    public MaxPQ(int size) {
        pq = (Key[]) new Comparable[size + 1];
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void exch(int k, int i) {
        Key tmp = pq[k];

        pq[k] = pq[i];
        pq[i] = tmp;
    }

    private boolean less(int i, int k) {
        return pq[i].compareTo(pq[k]) < 0;
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return max;
    }

    public void insert(Key x) {
        pq[++n] = x;
        swim(n);
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}
