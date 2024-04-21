public class BTree<T extends Comparable<T>> {
    public BTreeNode<T> root;
    public int m;

    public BTree(int m) {
        this.m = m;
        this.root = null;
    }

    public void insert(T data)
    {
        if(this.root == null)
        {
            this.root = new BTreeNode<>(m);
            root.insert(data);
        }
        else{
            if (root.isFull()) {
                BTreeNode<T> child = root.findChild(data);
                if(child == null)
                {
                    // Allocate memory for new root
                    BTreeNode<T> newRoot = new BTreeNode(m);
                    newRoot.isLeaf = false;

                    // Make old root as child of new root
                    newRoot.insertChild(root);

                    // Split the old root and move 1 key to the new root
                    root.insert(data);
                    root.parent = newRoot;
                    root.splitNode(this);

                    // Change root
                    this.root = newRoot;
                    return;
                }
                else {
                    root.nonFullInsert(data,this);
                }

            } else // If root is not full, call insertNonFull for root

                root.nonFullInsert(data,this);
        }

        /*if(this.root == null)
        {
            this.root = new BTreeNode<>(m);
            root.insert(data);
        }
        else{
            //Root exists
            if(root.dataCount == m-1)
            {
                //Root is full
                BTreeNode<T> child = root.findChild(data);
                if(child == null)
                {
                    //root needs to split
                    BTreeNode<T> newRoot = new BTreeNode<>(m);
                    newRoot.isLeaf = false;
                    newRoot.nodeChildren[0] = root;
                    root.parent = newRoot;
                    root.insert(data);
                    splitChild(newRoot,0);
                    this.root = newRoot;
                    return;
                }
                else if(child.isFull())
                {
                    //root needs to split
                    // Child is full, split the child first, then consider splitting root
                    //child.insert(data);
                    insertAndSplit(child, data);

                    return;
                }
                else{
                    insertNonFull(child,data);

                    return;
                }

            }
            insertNonFull(root,data);
        }*/
    }

    private void insertNonFull(BTreeNode<T> node,T data)
    {
        int i = node.dataCount - 1;
        if(node.isLeaf)
        {
            //Insert into the leaf node
            node.insert(data);
        }
        else{
            //Find child to insert into
            BTreeNode<T> child = node.findChild(data);
            child.parent = node;
            //child.insert(data);
            if(child.isFull())
            {
                i = node.findChildIndex(data);
                child.insert(data);
                splitChild(node,i);
                // After splitting, decide which child to descend into
                child = node.findChild(data);
                return;
            }
            insertNonFull(child, data);
        }
    }

    private void splitChild(BTreeNode<T> parent,int childIndex)
    {
        BTreeNode<T> child = parent.nodeChildren[childIndex];
        BTreeNode<T> newChild = new BTreeNode<T>(m);
        newChild.isLeaf = child.isLeaf;

        // Calculate the index of the median key

        int medianIndex = ((m-1)/2);

        // Move median key from child to parent

        T median = (T) child.nodeData[medianIndex];
        child.delete(median);
        // Insert median key into parent
        parent.insert(median);

        // Move keys and children from child to newChild

        for (int i = medianIndex; i < child.size-1; i++)
        {
            if( child.nodeData[medianIndex]!=null)
            {
                newChild.insert((T) child.nodeData[medianIndex]);
                child.delete((T) child.nodeData[medianIndex]);
            }
        }

       /* System.out.println(medianIndex-1);
        System.out.println(parent.nodeChildren[1]);
        System.out.println(parent.nodeChildren[0]);
        System.out.println(parent.descend(2));
        System.out.println(newChild);*/
        if (!child.isLeaf)
        {
            for (int i = medianIndex-1; i <= child.size; i++) {
                newChild.nodeChildren[i - (medianIndex-1)] = child.nodeChildren[i]; // Move children to newChild
                child.nodeChildren[i] = null; // Clear children from original child
            }
        }

        //T median = child.getMedian();



        // Insert newChild into parent's children array
        /*parent.nodeChildren[childIndex] = child;
        child.parent = parent;
        parent.nodeChildren[childIndex + 1] = newChild;
        newChild.parent = parent;*/
        parent.insertChild(newChild);
    }

    private void insertAndSplit(BTreeNode<T> node, T data)
    {
        if (node.isFull())
        {
            BTreeNode<T> child = node.findChild(data);
            if (child == null)
            {
                node.insert(data);
                splitChild(node.parent, node.parent.findChildIndex(data));
            } else {
                insertAndSplit(child, data);
            }
        } else {
            insertNonFull(node, data);
        }
    }

    public String printPath(T key)
    {
        if(root == null)
        {
            return "Null";
        }
        BTreeNode<T> current = root;
        String path = "";

        while(current != null && !current.contains(key))
        {
            //Step 1: Find the correct value
            T val = null;

                /*for(int i = 0; i < m; i++)
                {
                    if(current.nodeData[i] != null && current.nodeData[i].compareTo(key) <= 0)
                    {
                        if(i + 1 < m &&  current.nodeData[i+1].compareTo(key) >0)
                        {
                            val = (T) current.nodeData[i];
                        }
                    }
                }*/


            path += current.printUntil(key);

            current = current.findChild(key);

        }
        if(current == null)
        {
            path += "Null";
        }
        else{
            path +=  current.printUntilDone(key);
        }
        return path;
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
