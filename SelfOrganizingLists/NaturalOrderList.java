public class NaturalOrderList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        return new NaturalOrderList<T>();
    }

    @Override
    public void access(T data) {
        
    }

    @Override
    public void insert(T data) {
        //create new node
        Node<T> newNode = new Node<>(data);
        //check if list is empty
        if(isEmpty())
        {
            this.head = newNode;
            return;
        }

        Node<T> current = this.head;
        if(head.data.compareTo(data)<0)
        {
            //Make new head
            newNode.next = head;
            this.head = newNode;
            return;
        }

        while(current.next!=null)
        {
            if(current.next.data.compareTo(data)<0)
            {
                //if the current data is smaller
                break;
            }
            current = current.next;
        }
        if(current.next!=null)
        {
            //current.next is smaller than new node
            Node<T> temp = current.next;
            newNode.next = temp;
            temp.prev = newNode;
            newNode.prev = current;
            current.next = newNode;
        }
        else{
            //current.next==null
            current.next = newNode;
            newNode.prev = current;
            this.tail = newNode;
        }
    }
}
