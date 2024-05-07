class Memory {
    private Integer[] data;
    private int size;

    public int currentSize;

    public Memory(int size) {
        this.data = new Integer[size];
        this.size = size;
        this.currentSize = 0;
    }

    public Integer get(int index) {
        return data[index];
    }

    public void set(int index, int value) {

        data[index] = value;
    }

    public int getSize() {
        return size;
    }

    public Integer[] getData() {
        return data;
    }

    public void add(int num)
    {
        if(currentSize < size)
        {
            //Can add the data
            data[currentSize] = num;
            currentSize ++;
        }
    }

    public int remove(int index)
    {
        int temp = -1;
        if(currentSize > 0)
        {
            temp = data[index];
            data[index] = 0;
            currentSize--;
        }
        return temp;
    }

    /**
     * Recursive helper method to perform Quick Sort.
     * @param low The index of the lowest element of the partition.
     * @param high The index of the highest element of the partition.
     */
    private void quickSort(int low, int high)
    {
        if(low < high)
        {
            // Partition the array and get the pivot index
            int pivotIndex = partition(low,high);

            // Recursivly sort the left and right partitions
            quickSort(low,pivotIndex-1);
            quickSort(pivotIndex+1,high);
        }
    }

    /**
     * Partitions the array and returns the index of the pivot element.
     * @param low The index of the lowest element of the partition.
     * @param high The index of the highest element of the partition.
     * @return The index of the pivot element.
     */
    private int partition(int low,int high)
    {
        int pivot = data[high];
        int i = low-1;

        for(int j = low;j< high;j++){
            // If the current element is smaller/equal to
            if(data[j] <= pivot)
            {
                i++;

                //swap the elemenets
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        int temp = data[i+1];
        data[i+1] = data[high];
        data[high] = temp;

        return i+1;
    }

    /**
     * Sorts an array using the Quick Sort algorithm.
     * @param arr The array to be sorted.
     */
    public void quickSort()
    {
        quickSort(0, size - 1);
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }



    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        currentSize = 0;
    }
}
