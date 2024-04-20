public class BTreeNode<T extends Comparable<T>> {
    public Comparable<T>[] nodeData;
    public BTreeNode<T>[] nodeChildren;
    public BTreeNode<T> parent;
    public int size;

    public int dataCount;

    public boolean isLeaf;

    @SuppressWarnings("unchecked")
    public BTreeNode(int size) {
        this.size = size;
        nodeData = (Comparable<T>[]) new Comparable[size];
        this.nodeChildren = new BTreeNode[size+1];
        this.parent = null;
        this.dataCount = 0;
        this.isLeaf = true;
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
            return nodeChildren[i-1];
        }
        return null;
    }

    public boolean insert(T data)
    {
        // Check if the node is full
        if (dataCount == size) {
            // Node is full
            return false;
        } else {
            // Find the correct position to insert the data
            int insertIndex = 0;
            while (insertIndex < dataCount && nodeData[insertIndex] != null && nodeData[insertIndex].compareTo(data) <= 0) {
                insertIndex++;
            }

            // Shift elements to make space for the new data
            for (int i = dataCount; i > insertIndex; i--) {
                nodeData[i] = nodeData[i - 1];
            }

            // Insert the new data
            nodeData[insertIndex] = data;
            dataCount++;
            return true;
        }
    }

    public boolean delete(T data)
    {
        int deleteIndex = -1;

        for (int i = 0; i < dataCount; i++) {
            if (nodeData[i] != null && nodeData[i].compareTo(data) == 0)
            {
                deleteIndex = i;
                break;
            }
        }

        // If data to delete is not found, return false
        if (deleteIndex == -1)
        {
            return false;
        }

        // Shift elements to remove the deleted data
        for (int i = deleteIndex; i < dataCount - 1; i++)
        {
            nodeData[i] = nodeData[i + 1];
        }

        // Clear the last element
        nodeData[dataCount - 1] = null;

        dataCount--;

        return true;
    }


    public BTreeNode<T> findChild(T data)
    {
        int insertIndex = 0;
        while (insertIndex < dataCount && nodeData[insertIndex] != null && nodeData[insertIndex].compareTo(data) <= 0) {
            insertIndex++;
        }
        return this.nodeChildren[insertIndex]; // Return child at insertIndex
    }

    public int findChildIndex(T data)
    {
        int insertIndex = 0;
        while (insertIndex < dataCount && nodeData[insertIndex] != null && nodeData[insertIndex].compareTo(data) <= 0) {
            insertIndex++;
        }
        return insertIndex; // Return child at insertIndex
    }

    public boolean isFull(){
        return dataCount == size-1;
    }

    public T getMedian() {
        int index = ((size-1) / 2)-1;
        return (T) this.nodeData[index];   // Calculate the median index of the data
    }

    public int medianIndex(){
        return (size - 1) / 2;
    }


    /* -------------------------------------------------------------------------- */
    /* Helpers */
    /* -------------------------------------------------------------------------- */
/*I changed the nodeData.length to nodeData.length-1*/
    public String toString() {
        String out = "|";
        for (int i = 0; i < nodeData.length-1; i++) {
            if (nodeData[i] == null) {
                out += "null|";
            } else {
                out += nodeData[i].toString() + "|";
            }

        }
        return out;
    }

}
