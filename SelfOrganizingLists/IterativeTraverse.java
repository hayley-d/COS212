public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T>{
    public IterativeTraverse(){
       this.list = null;
    };
    
    public IterativeTraverse(SelfOrderingList<T> list){
        this.list = list.getBlankList();
        Node<T> otherHead = list.head;
        this.list.insert(otherHead.data);

        Node<T> otherCurrent = otherHead;
        Node<T> current = this.list.head;
        while(otherCurrent!=null)
        {
            this.list.insert(otherCurrent.data);
            otherCurrent = otherCurrent.next;
        }
    }



    @Override
    public SelfOrderingList<T> reverseList() {
        SelfOrderingList<T> newList = list.getBlankList();
        if(!list.isEmpty())
        {
            newList.head = new Node<>(list.tail.data);

            //go through list in reverse
            Node<T> newCurrent = newList.head;
            Node<T> current = list.tail.prev;
            while(current!=null)
            {
                newList.insert(current.data);
                current = current.prev;
            }
            return newList;
        }
        return newList;
    }

    @Override
    public boolean contains(T data) {
        //TODO: Implement the function
    }

    @Override
    public String toString() {
        //TODO: Implement the function
    }

    @Override
    public Node<T> get(int pos) {
        //TODO: Implement the function
    }

    @Override
    public Node<T> find(T data) {
        //TODO: Implement the function
    }

    @Override
    public int size() {
        //TODO: Implement the function
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        //TODO: Implement the function
    }
}
