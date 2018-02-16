import java.util.HashMap;
import java.util.Map;

public class MOUCache<K, V> {

    private final int size;

    private Node<K, V> first;
    private Node<K, V> last;
    private Map<K, Node<K, V>> storage;

    private static class Node<K, V> {

        private V data;
        private K key;
        private Node<K, V> next;
        private Node<K, V> prev;
    }

    public MOUCache(int size) {
        this.size = size;
        this.first = null;
        this.last = null;
        this.storage = new HashMap<>();
    }

    public void put(K key, V value) {
        Node<K, V> tmp, node;

        node = new Node<>();
        node.data = value;
        node.key = key;

        if (storage.size() == 0) {
            last = node;
            first = node;
            storage.put(key, node);
            return;
        }

        if (storage.containsKey(key)) {
            get(key);
            return;
        }

        if (storage.size() >= size) {
            tmp = first;
            first = first.next;
            first.prev = null;
            tmp.next = null;
            storage.remove(tmp.key);
        }

        last.next = node;
        node.prev = last;
        last = node;
        storage.put(key, node);
    }


    public V get(K key) {
        Node<K, V> node, prev, next;

        if (!storage.containsKey(key)) {
            return null;
        }

        node = storage.get(key);

        if (first == last || node.next == null) {
            return node.data;
        }

        if (node.prev == null) {
            first = node.next;
        }

        prev = node.prev;
        next = node.next;

        next.prev = prev;

        if (prev != null) {
            prev.next = next;
        }

        last.next = node;
        node.prev = last;
        node.next = null;
        last = node;

        return node.data;
    }
}
