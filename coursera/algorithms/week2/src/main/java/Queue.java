public class Queue<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public Queue() {
        first = null;
        last = null;
    }

    public void enqueue(T element) {
        Node<T> node = new Node<>(element, null);

        if (last == null) {
            first = node;
            last = node;
        }
        else {
            last.setNext(node);
            last = node;
        }

        size++;
    }

    public T dequeue() {
        Node<T> node = first;

        if (first == null) {
            last = null;
            return null;
        }
        else {
            first = first.getNext();
        }

        size--;

        return node.getValue();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
