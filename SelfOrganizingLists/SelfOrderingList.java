abstract class SelfOrderingList<T extends Comparable<T>> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public void insert(T data){
        //Step 1: Create new node
        Node<T> newNode = new Node<>(data);
        //check if the list iss empty
        if(isEmpty())
        {

            head = newNode;
            tail = newNode;
            return;
        }

        Node<T> currentNode = head;

        while(currentNode.next !=null)
        {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
        newNode.prev = currentNode;
        this.tail = newNode;
        return;
    }

    public void remove(T data){
       //check if list is empty
        if(isEmpty())
        {
            return;
        }

        Node<T> current = head;
        while(current!=null)
        {
            if(current.data.equals(data))
            {
                Node<T> prev = current.prev;
                Node<T> next = current.next;
                prev.next = next;
                next.prev = prev;
                current.prev = null;
                current.next = null;
                return;
            }
            current = current.next;
        }
    }

    public abstract void access(T data);

    public abstract SelfOrderingList<T> getBlankList();

    public boolean isEmpty(){
        return head == null;
    }

    protected void swap(Node<T> node1, Node<T> node2)
    {
        if(node1 != null && node2 != null)
        {
            Node<T> prev1 = node1.prev;
            Node<T> prev2 = node2.prev;

            Node<T> next1 = node1.next;
            Node<T> next2 = node2.next;

            if(prev1!=null)
            {
                prev1.next = node2;
            }
            if(next1!=null)
            {
                next1.prev = node2;
            }

            if(prev2!=null)
            {
                prev2.next = node1;
            }
            if(next2!=null)
            {
                next2.prev = node1;
            }
        }
    }

    public Node<T> setCount(T data,int count)
    {
        Node<T> current = head;
        while(current != null)
        {
            if(current.data.equals(data))
            {
                current.accessCount = count;
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void printMe()
    {
        Node<T> current = this.head;
        while(current!=null)
        {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("\n");
    }
}
