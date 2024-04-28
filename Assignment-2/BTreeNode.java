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

        if(i >= 0 && i <= size)
        {
            return nodeChildren[i];
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
        while (insertIndex < dataCount && nodeData[insertIndex] != null && nodeData[insertIndex].compareTo(data) < 0) {
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

    public void insertChild(BTreeNode<T> child)
    {

        if (child == null) {
            return; // No child to insert
        }

        // Find the correct position to insert the child
        int insertIndex = 0;
        while (insertIndex < dataCount && nodeData[insertIndex] != null && nodeData[insertIndex].compareTo((T)child.nodeData[0]) <= 0) {
            insertIndex++;
        }



        // Shift elements to make space for the new child
        for (int i = size-1; i > insertIndex; i--) {
            nodeChildren[i] = nodeChildren[i - 1];
        }

        // Insert the new child
        nodeChildren[insertIndex] = child;
        child.parent = this;

    }

    public void nonFullInsert(T data, BTree<T> tree)
    {
        if(isLeaf)
        {
            //insert the key in this node
            this.insert(data);
            if(this.dataCount == size)
            {
                splitNode(tree);
            }
        }
        else{
            //find the child to insert into
            int index = 0;
            for(int i = 0; i < this.size;i++)
            {
               if( nodeChildren[i] != null)
               {
                   nodeChildren[i].parent = this;
               }
                if(nodeData[i] == null || nodeData[i].compareTo(data) >= 0)
                {

                    index = i;
                    break;
                }
            }
            BTreeNode<T> child = nodeChildren[index];

            /*if(child == null)
            {
                nodeChildren[index] = new BTreeNode<>(size);
                child = nodeChildren[index];
            }*/

            if(child!=null)
            {
                child.nonFullInsert(data, tree);
            }
            else {
                nodeChildren[index] = new BTreeNode<>(size);
                child = nodeChildren[index];
                child.nonFullInsert(data, tree);
            }
        }
    }

    public void splitNode(BTree<T> tree)
    {

        BTreeNode<T> newNode = new BTreeNode<>(size);
        newNode.isLeaf = this.isLeaf;

        int medianIndex = ((size-1)/2);

        T median = (T) this.nodeData[medianIndex];

        this.delete(median);

        // Insert median key into parent


        for (int i = medianIndex; i < size-1; i++)
        {
            if( this.nodeData[medianIndex]!=null)
            {
                newNode.insert((T) this.nodeData[medianIndex]);
                this.delete((T) this.nodeData[medianIndex]);
            }
        }


        if (!this.isLeaf)
        {
            for (int i = medianIndex; i <= size; i++) {

                if(i+1 < size)
                {
                    if(nodeChildren[i] != null && nodeChildren[i+1] != null)
                    {
                        if(this.nodeChildren[i].nodeData[0].compareTo((T)this.nodeData[medianIndex-1]) >=0 && (this.nodeChildren[i+1].nodeData[0].compareTo((T)newNode.nodeData[0]) < 0))
                        {
                            continue;
                        }
                    }
                }
                newNode.insertChild(this.nodeChildren[i]); // Move children to newChild nodeChildren[i - (medianIndex-1)]
                if(this.nodeChildren[i] != null)
                {
                    this.nodeChildren[i].parent = newNode;
                }
                this.nodeChildren[i] = null; // Clear children from original child
            }
        }



        if(parent != null)
        {
            parent.insert(median);
            if(parent.dataCount == size)
            {

                parent.splitNode(tree);
            }
        }
        else{
            //need to create a new root node
            BTreeNode<T> newRoot = new BTreeNode(size);
            newRoot.isLeaf = false;

            // Make old root as child of new root
            newRoot.insertChild(this);
            this.parent = newRoot;
            parent.insert(median);
            tree.root = newRoot;
        }



        if(parent != null)
        {
            parent.insertChild(newNode);
            newNode.parent = parent;
        }

    }

    public boolean contains(T data)
    {
        for(int i = 0; i < size; i++)
        {
            if(this.nodeData[i] != null)
            {
                if((this.nodeData[i].compareTo(data)) == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String printUntil(T data)
    {
        String path = "";
        int i = 0;
        while((i < size) && (nodeData[i] != null) && (this.nodeData[i].compareTo(data) <= 0))
        {
            path += this.nodeData[i] + " -> ";
            i++;
        }
        return path;
    }

    public String printUntilDone(T data)
    {
        String path = "";
        int i = 0;

        while((i < size) && (nodeData[i] != null) && (this.nodeData[i].compareTo(data) <= 0))
        {
            path += this.nodeData[i];
            if(i+1 < size && (nodeData[i+1] != null) && this.nodeData[i+1].compareTo(data) <= 0)
            {
                path += " -> ";
            }
            i++;
        }
        return path;
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
