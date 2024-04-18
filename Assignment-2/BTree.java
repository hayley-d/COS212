public class BTree<T extends Comparable<T>> {
    public BTreeNode<T> root;
    public int m;

    public BTree(int m) {
        this.m = m;
        this.root = null;
    }

    public void insert(T data) {

    }

    public String printPath(T key) {
        return "";
    }

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I tried to make it pretty. */
    /* -------------------------------------------------------------------------- */
    /* -------------------------------------------------------------------------- */
    /* Also we may test against it */
    /* -------------------------------------------------------------------------- */

    @Override
    public String toString() {
        if (root == null) {
            return "The B-Tree is empty";
        }
        StringBuilder builder = new StringBuilder();
        buildString(root, builder, "", true);
        return builder.toString();
    }

    private void buildString(BTreeNode<T> node, StringBuilder builder, String prefix, boolean isTail) {
        if (node == null)
            return;

        builder.append(prefix).append(isTail ? "└── " : "├── ");
        for (int i = 0; i < node.nodeData.length; i++) {
            if (node.nodeData[i] != null) {
                builder.append(node.nodeData[i]);
                if (i < node.nodeData.length - 1 && node.nodeData[i + 1] != null) {
                    builder.append(", ");
                }
            }

        }
        if (node.parent != null)
            builder.append("\t(p:" + node.parent.nodeData[0].toString() + ")");
        builder.append("\n");

        int numberOfChildren = m;
        for (int i = 0; i < numberOfChildren; i++) {

            BTreeNode<T> child = node.descend(i);
            buildString(child, builder, prefix + (isTail ? "    " : "│   "), i == numberOfChildren - 1);
        }
    }
}
