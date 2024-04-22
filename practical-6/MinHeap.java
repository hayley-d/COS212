class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap() {
        super();
        super.max = false;
    }

    public MinHeap(T[] array) {
        super(array,false);
        super.max = false;
    }

    @Override
    protected boolean compare(Comparable<T> child, Comparable<T> parent)
    {
        if(child != null && parent != null)
        {
            if(child.compareTo((T)parent)<0)
            {
                return true;
            }
        }
        return false;
    }
}