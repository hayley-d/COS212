public class MaxSkewHeap {
    Node root;

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "{}" : toStringOneLine(root));
    }

    public String toStringOneLine(Node ptr) {
        if (ptr == null) {
            return "{}";
        }
        return "{" + ptr.toString() + toStringOneLine(ptr.left) + toStringOneLine(ptr.right) + "}";
    }

    public MaxSkewHeap() {
    }

    public MaxSkewHeap(String input) {
    }

    public void insert(int data) {
    }

    public void remove(int data) {
    }

    public Node search(int value) {
    }

    public String searchPath(int value) {
    }

    public boolean isLeftist() {
    }

    public boolean isRightist() {
    }
}
