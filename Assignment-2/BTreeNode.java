public class BTreeNode<T extends Comparable<T>> {
    public Comparable<T>[] nodeData;
    public BTreeNode<T>[] nodeChildren;
    public BTreeNode<T> parent;
    public int size;

    @SuppressWarnings("unchecked")
    public BTreeNode(int size) {
        this.size = size;
        nodeData = (Comparable<T>[]) new Comparable[size];
        this.nodeChildren = new BTreeNode[size+1];
        this.parent = null;
    }

    public Comparable<T> getIndex(int i) {
        if(i < size-1 && i > 0)
        {
            return nodeData[i];
        }
        return null;
    }

    public BTreeNode<T> ascend() {
        return parent;
    }

    public BTreeNode<T> descend(int i) {
        if(i > 0 && i < size)
        {
            return nodeChildren[i];
        }
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
