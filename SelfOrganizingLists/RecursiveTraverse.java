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
        //Check if the list is empty
        if(list.isEmpty())
        {
            return false;
        }
        Node<T> result = loopThrough(this.list.head,data);
        if(result !=null)
        {
            return true;
        }
        return false;
    }

    private Node<T> loopThrough(Node<T> current,T target)
    {
        if(current == null)
        {
            return null;
        }
        if(current.data.equals(target))
        {
            return current;
        }
        return loopThrough(current.next,target);
    }

    @Override
    public String toString() {
        if(this.list == null || this.list.isEmpty())
        {
            return "";
        }
        String myList = "";
        Node<T> current = this.list.head;
        return printList(current,myList);
    }

    private String printList(Node<T> current, String myList)
    {
        if(current == null)
        {
            return myList;
        }
        myList += "->(" + current.data+"["+current.accessCount+"])";
        return printList(current.next,myList);
    }

    @Override
    public Node<T> get(int pos) {
        //check if the pos > size
        if(pos > this.size() || this.list.isEmpty())
        {
            return null;
        }
        int position = 0;
        Node<T> current = this.list.head;

        return findPos(current,pos,position);
    }

    private Node<T> findPos(Node<T> current, int targetPos,int currentPos)
    {
        if(current == null || currentPos == targetPos)
        {
            return current;
        }
        current = current.next;
        currentPos += 1;
        return findPos(current,targetPos,currentPos);
    }

    @Override
    public Node<T> find(T data) {
        //Check if the list is empty
        if(list.isEmpty())
        {
            return null;
        }
        return loopThrough(this.list.head,data);
    }

    @Override
    public int size() {
        int numberOfNodes = 0;
        if(list.isEmpty())
        {
            return 0;
        }
        Node<T> current = this.list.head;
        return countThrough(current,numberOfNodes);
    }

    private int countThrough(Node<T> current,int count)
    {
        if(current == null)
        {
            return count;
        }
        count += 1;
        return countThrough(current.next,count);
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        if(otherList == null || otherList.isEmpty())
        {
            return null;
        }
        Node<T> current = otherList.head;
        SelfOrderingList<T> newList = otherList.getBlankList();
        return cloneList(newList,current);
    }

    private SelfOrderingList<T> cloneList(SelfOrderingList<T> newList,Node<T> current)
    {
        if(current == null)
        {
            return newList;
        }
        newList.insert(current.data);
        current = current.next;
        return cloneList(newList, current);
    }

    @Override
    public void setList(SelfOrderingList<T> otherList) {
        if(otherList == null || otherList.isEmpty())
        {
            return;
        }
        this.list.head = otherList.head;
    }
}
