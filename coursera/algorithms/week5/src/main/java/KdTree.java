import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private final List<Node> nodes;

    private class Node {

        private Node left;
        private Node right;
        private final Point2D value;
        private final RectHV border;
        private final boolean isVerticalOrientation;

        public Node(Point2D value, RectHV border, boolean isVerticalOrientation) {
            this.value = value;
            this.border = border;
            this.isVerticalOrientation = isVerticalOrientation;
        }

    }

    public KdTree() {
        nodes = new LinkedList<>();
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public int size() {
        return nodes.size();
    }

    private Node getRoot() {
        if (nodes.isEmpty()) {
            return null;
        }

        return nodes.get(0);
    }

    public void insert(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            Node node = new Node(point, new RectHV(0.0, 0.0, 1.0, 1.0), true);
            nodes.add(node);
        }
        else {
            insert(getRoot(), point);
        }
    }

    private void insert(Node currentNode, Point2D point) {
        Node node;

        if (currentNode.value.equals(point)) {
            return;
        }

        if (currentNode.isVerticalOrientation) {
            if (currentNode.value.x() <= point.x()) {
                if (currentNode.right == null) {
                    node = new Node(point,
                            new RectHV(currentNode.value.x(), currentNode.border.ymin(),
                                    currentNode.border.xmax(), currentNode.border.ymax()), !currentNode.isVerticalOrientation);
                    nodes.add(node);
                    currentNode.right = node;
                }
                else {
                    insert(currentNode.right, point);
                }
            }
            else {
                if (currentNode.left == null) {
                    node = new Node(point,
                            new RectHV(currentNode.border.xmin(), currentNode.border.ymin(),
                                    currentNode.value.x(), currentNode.border.ymax()), !currentNode.isVerticalOrientation);
                    nodes.add(node);
                    currentNode.left = node;
                }
                else {
                    insert(currentNode.left, point);
                }
            }
        }
        else {
            if (currentNode.value.y() <= point.y()) {
                if (currentNode.right == null) {
                    node = new Node(point,
                            new RectHV(currentNode.border.xmin(), currentNode.value.y(),
                                    currentNode.border.xmax(), currentNode.border.ymax()), !currentNode.isVerticalOrientation);
                    nodes.add(node);
                    currentNode.right = node;
                }
                else {
                    insert(currentNode.right, point);
                }
            }
            else {
                if (currentNode.left == null) {
                    node = new Node(point,
                            new RectHV(currentNode.border.xmin(), currentNode.border.ymin(),
                                    currentNode.border.xmax(), currentNode.value.y()), !currentNode.isVerticalOrientation);
                    nodes.add(node);
                    currentNode.left = node;
                }
                else {
                    insert(currentNode.left, point);
                }
            }
        }
    }

    public boolean contains(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        Node node = getRoot();

        while (node != null && !point.equals(node.value)) {
            if (node.isVerticalOrientation) {
                if (node.value.x() <= point.x()) {
                    node = node.right;
                }
                else {
                    node = node.left;
                }
            }
            else {
                if (node.value.y() <= point.y()) {
                    node = node.right;
                }
                else {
                    node = node.left;
                }
            }
        }

        return (node != null && point.equals(node.value));
    }

    public void draw() {
        draw(getRoot());
        StdDraw.show();
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }

        StdDraw.setPenRadius(0.02D);
        StdDraw.setPenColor(StdDraw.BLACK);
        node.value.draw();

        StdDraw.setPenRadius(0.003D);
        if (node.isVerticalOrientation) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.value.x(), node.border.ymin(), node.value.x(), node.border.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.border.xmin(), node.value.y(), node.border.xmax(), node.value.y());
        }

        draw(node.left);
        draw(node.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        List<Point2D> list = new LinkedList<>();

        return range(rect, getRoot(), list);
    }

    private List<Point2D> range(RectHV rect, Node node, List<Point2D> list) {
        if (node != null) {
            if (rect.contains(node.value)) {
                list.add(node.value);
            }

            if (node.left != null && rect.intersects(node.left.border)) {
                list = range(rect, node.left, list);
            }

            if (node.right != null && rect.intersects(node.right.border)) {
                list = range(rect, node.right, list);
            }
        }

        return list;
    }

    public Point2D nearest(Point2D point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }

        if (getRoot() == null) {
            return null;
        }

        return nearest(point, getRoot().value, getRoot());
    }

    private Point2D nearest(final Point2D point, Point2D nearestPoint, Node node) {
        double distance = nearestPoint.distanceSquaredTo(point);

        if (node.value.distanceSquaredTo(point) < distance) {
            nearestPoint = node.value;
            distance = nearestPoint.distanceSquaredTo(point);
        }

        if (node.left != null && distance > node.left.border.distanceSquaredTo(point)) {
            nearestPoint = nearest(point, nearestPoint, node.left);
            distance = nearestPoint.distanceSquaredTo(point);
        }

        if (node.right != null && distance >= node.right.border.distanceSquaredTo(point)) {
            nearestPoint = nearest(point, nearestPoint, node.right);
        }

        return nearestPoint;
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        In in = new In("kdtree/horizontal8.txt");

        while (!in.isEmpty()) {
            Point2D p = new Point2D(in.readDouble(), in.readDouble());
            tree.insert(p);
        }

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0.0D, 1.0D);
        StdDraw.setYscale(0.0D, 1.0D);
        StdDraw.setPenRadius(0.005D);
        StdDraw.enableDoubleBuffering();

        tree.draw();
        StdDraw.show();
    }
}