import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private Node<T> top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element should not be null");
        }

        top = new Node<>(element, top);
        size++;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        Node<T> element = top;

        top = top.getNext();
        size--;

        return element.getValue();
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public int size() {
        return this.size;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();


        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }

    private class StackIterator implements Iterator<T> {
        private Node<T> currentNode = top;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            Node<T> node = currentNode;
            currentNode = currentNode.getNext();

            return node.getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }
}
