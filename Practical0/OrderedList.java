public class OrderedList<T extends Comparable<T>> {

    public Node<T> head;
    private Node<T> tail;
    private int numberOfNodes;

    public OrderedList() {
    this.head = null;
    this.tail = null;
    this.numberOfNodes = 0;
    }

    public void insert(T data) {
        Node<T> newNode = new Node<T>(data);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
            numberOfNodes++;
            return;
        }
        Node<T> current = head;
        if(current.data.compareTo(data)<0)
        {
            newNode.next = current;
            head = newNode;
            numberOfNodes++;
            return;
        }
        while(current.next !=null)
        {
            if(current.next.data.compareTo(data)<0)
            {
                Node<T> temp = current.next;
                current.next = newNode;
                newNode.next = temp;
                numberOfNodes++;
                return;
            }
            current = current.next;
        }
        current.next = newNode;
        tail = newNode;
        numberOfNodes++;
    }

    public String toString() {
        String list = "Head->";
        Node<T> current = head;
        if(!isEmpty())
        {
            while(current!=null)
            {
                list += "[" + current.data+"]->";
                current = current.next;
            }
        }
        list += "NULL";
        return list;
    }

    private boolean isEmpty()
    {
        return head == null;
    }
}
