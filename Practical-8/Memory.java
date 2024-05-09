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
