public abstract class Heap<T extends Comparable<T>> {

    public Comparable<T>[] data;
    public int size;
    public boolean max;

    public Heap() {
        this.data = (Comparable<T>[]) new Comparable[2];
        size = 0;
        max = true;
    }

    public Heap(T[] array)
    {
        int arrayLength = array.length;
        max = true;

        // Start from the last non-leaf node and go up to the root
        for (int parentNodeIndex = arrayLength / 2 - 1; parentNodeIndex >= 0; parentNodeIndex--) {
            heapifyUtil(array, arrayLength, parentNodeIndex);
        }

        this.data = (Comparable<T>[]) new Comparable[array.length];
        this.size = 0;


        for(int i = 0;i < array.length;i++)
        {
            data[i] = array[i];
            size++;
        }
    }

    public Heap(T[] array, boolean max)
    {
        this.max = false;
        int arrayLength = array.length;

        // Start from the last non-leaf node and go up to the root
        for (int parentNodeIndex = arrayLength / 2 - 1; parentNodeIndex >= 0; parentNodeIndex--) {
            heapifyUtil(array, arrayLength, parentNodeIndex);
        }

        this.data = (Comparable<T>[]) new Comparable[array.length];
        this.size = 0;
        max = true;

        for(int i = 0;i < array.length;i++)
        {
            data[i] = array[i];
            size++;
        }
    }

    protected void heapifyUtil(T[] array, int arrayLength, int parentIndex)
    {
        if(max)
        {

            int largest = parentIndex;
            int leftChild = getLeftChildIndex(parentIndex);
            int rightChild = getRightChildIndex(parentIndex);

            if(leftChild < arrayLength && array[leftChild].compareTo( array[largest])>0)
            {
                largest = leftChild;
            }

            if (rightChild < arrayLength && array[rightChild].compareTo(array[largest])>0) {
                largest = rightChild;
            }

            if (largest != parentIndex) {
                // Swap root with largest element
                T temp = array[parentIndex];
                array[parentIndex] = array[largest];
                array[largest] = temp;

                // Recursively heapify the affected subtree
                heapifyUtil(array, arrayLength, largest);
            }
        }
        else{
            int smallest = parentIndex;
            int leftChild = getLeftChildIndex(parentIndex);
            int rightChild = getRightChildIndex(parentIndex);

            if(leftChild < arrayLength && array[leftChild].compareTo( array[smallest])<0)
            {
                smallest = leftChild;
            }

            if (rightChild < arrayLength && array[rightChild].compareTo(array[smallest])<0) {
                smallest = rightChild;
            }

            if (smallest != parentIndex) {
                // Swap root with largest element
                T temp = array[parentIndex];
                array[parentIndex] = array[smallest];
                array[smallest] = temp;

                // Recursively heapify the affected subtree
                heapifyUtil(array, arrayLength, smallest);
            }
        }
    }

    protected abstract boolean compare(Comparable<T> child, Comparable<T> parent);

    public void push(T item)
    {
        if(size == data.length)
        {
            resize();
        }
        /*Insert the new element*/
        data[size] = item;
        size++;

        // Heapify the new node
        heapify(size-1);
    }

    public Comparable<T> pop()
    {
        if(size > 0)
        {
            T root = (T)data[0];
            int lastIndex = size-1;
            T lastElement = (T)data[lastIndex];

            //Step 1: remove the last element
            data[lastIndex] = null;
            size --;

            //Step 2: Set the new root
            data[0] = lastElement;

            heapifyDown(0);

            return root;
        }
        return null;
    }

    public Comparable<T> peek() {
        return data[0];
    }

    private void heapifyDown(int index)
    {
        if(!max)
        {
            int leftchild = getLeftChildIndex(index);
            int rightChild = getRightChildIndex(index);
            int smallestIndex = index;

            if (leftchild < size && data[leftchild].compareTo((T)data[smallestIndex]) < 0) {
                smallestIndex = leftchild;
            }

            if (rightChild < size && data[rightChild].compareTo((T)data[smallestIndex]) < 0) {
                smallestIndex = rightChild;
            }

            if (smallestIndex != index) {
                //Swap
                T temp = (T) data[index];
                data[index] = data[smallestIndex];
                data[smallestIndex] = temp;

                heapifyDown(smallestIndex);
            }
        }
        else{
            int leftchild = getLeftChildIndex(index);
            int rightChild = getRightChildIndex(index);
            int largestIndex = index;

            if (leftchild < size && data[leftchild].compareTo((T)data[largestIndex]) > 0) {
                largestIndex = leftchild;
            }

            if (rightChild < size && data[rightChild].compareTo((T)data[largestIndex]) > 0) {
                largestIndex = rightChild;
            }

            if (largestIndex != index) {
                //Swap
                T temp = (T) data[index];
                data[index] = data[largestIndex];
                data[largestIndex] = temp;

                heapifyDown(largestIndex);
            }
        }
    }

    private void heapify(int index)
    {
        int parent = getParent(index);

        if(parent >= 0)
        {
            if(max)
            {
                if(data[index].compareTo((T)data[parent])>0)
                {
                    T temp = (T) data[index];
                    data[index] = data[parent];
                    data[parent] = temp;

                    heapify(parent);
                }
            }
            else{
                if(data[index].compareTo((T)data[parent])<0)
                {
                    T temp = (T) data[index];
                    data[index] = data[parent];
                    data[parent] = temp;

                    heapify(parent);
                }
            }
        }
    }

    private void resize(){
        int length = data.length;
        int newLength = length * 2;

        Comparable<T>[] temp = (Comparable<T>[]) new Comparable[length];

        for(int i = 0; i < length;i++)
        {
            temp[i] = data[i];
        }

        data = (Comparable<T>[]) new Comparable[newLength];

        for(int i = 0; i < length;i++)
        {
            data[i] = temp[i];
        }
    }

    /*
     * 
     * Functions used for the toString
     * Do not change them but feel free to use them
     * 
     */

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParent(int currentIndex){return ((int) (currentIndex-1)/2);}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(0, "", true, sb); // Start from the root
        return sb.toString();
    }

    private void buildString(int index, String prefix, boolean isTail, StringBuilder sb) {
        if (index < size) {
            String linePrefix = isTail ? "└── " : "┌── ";
            if (getRightChildIndex(index) < size) { // Check if there is a right child
                buildString(getRightChildIndex(index), prefix + (isTail ? "|   " : "    "), false, sb);
            }
            sb.append(prefix).append(linePrefix).append(data[index]).append("\n");
            if (getLeftChildIndex(index) < size) { // Check if there is a left child
                buildString(getLeftChildIndex(index), prefix + (isTail ? "    " : "│   "), true, sb);
            }
        }
    }

}
