public class BinaryNode<T extends Comparable<T>> {

    T data;

    BinaryNode<T> left;
    BinaryNode<T> right;

    public BinaryNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        String leftString = (left == null) ? "N" : left.data.toString();
        String rightString = (right == null) ? "N" : right.data.toString();
        String out = "[" + leftString + "]<-[" + data.toString() + "]->[" + rightString + "]";

        return out;
    }

}