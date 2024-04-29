public class Node {
    public int data;
    public Node left;
    public Node right;

    public Node parent;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
        parent = null;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
