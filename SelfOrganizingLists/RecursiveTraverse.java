public class RecursiveTraverse<T extends Comparable<T>> extends Traverser<T>{
    public RecursiveTraverse(){
        this.list = null;
    }
    
    public RecursiveTraverse(SelfOrderingList<T> list){
        this.list = list.getBlankList();
        Node<T> otherHead = list.head;
        if(otherHead != null)
        {
            this.list.head = new Node<>(otherHead.data);
        }
        else{
            this.list.head = null;
            return;
        }

        Node<T> otherCurrent = otherHead.next;
        Node<T> current = this.list.head;
        if(current != null)
        {
            deepCopy(otherCurrent);
        }
    }

    private Node<T> deepCopy(Node<T> copyNode)
    {
        if(copyNode == null)
        {
            return null;
        }
        //not null
        this.list.insert(copyNode.data);
        //move to the next node
        copyNode = copyNode.next;
        return deepCopy(copyNode);
    }

    @Override
    public SelfOrderingList<T> reverseList() {
        //create new list
        SelfOrderingList<T> newList = list.getBlankList();
        //create new head
        if(!list.isEmpty())
        {
            newList.head = new Node<>(list.head.data);
            Node<T> newHead = newList.head;
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
