public class BTreeNode<T extends Comparable<T>> {
    public Comparable<T>[] nodeData;
    public BTreeNode<T>[] nodeChildren;
    public BTreeNode<T> parent;
    public int size;

    @SuppressWarnings("unchecked")
    public BTreeNode(int size) {
        this.size = size;

    }

    public Comparable<T> getIndex(int i) {
        return null;
    }

    public BTreeNode<T> ascend() {
        return null;
    }

    public BTreeNode<T> descend(int i) {
        return null;
    }

    /* -------------------------------------------------------------------------- */
    /* Helpers */
    /* -------------------------------------------------------------------------- */

    public String toString() {
        String out = "|";
        for (int i = 0; i < nodeData.length; i++) {
            if (nodeData[i] == null) {
                out += "null|";
            } else {
                out += nodeData[i].toString() + "|";
            }

        }
        return out;
    }

}
