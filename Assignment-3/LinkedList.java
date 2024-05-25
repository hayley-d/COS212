public class LinkedList<T extends Comparable<T>> {
    public Node<T> head;
    public Node<T> tail;

    public int size;

    public LinkedList()
    {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /**
     * Appends a new node with the given data to the end of the list.
     * @param data The data to be stored in the new node.
     */
    public void append(T data) {
        if(contains(data))
        {
            //no duplicates
            return;
        }
        Node<T> newNode = new Node<>(data);
        if (tail == null)
        {
            // The list is empty
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Prepends a new node with the given data to the start of the list.
     * @param data The data to be stored in the new node.
     */
    public void prepend(T data) {
        if(contains(data))
        {
            //no duplicates
            return;
        }
        Node<T> newNode = new Node<>(data);
        if (head == null)
        {
            // The list is empty
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }



    // Print the list from head to tail
    public String printForward() {
        String list = "";
        Node<T> current = head;
        if(this.head == null)
        {
           return "Empty List";
        }
        while (current != null)
        {
            if(current.next != null)
            {
                list += current.data + "->";
            }
            else{
                list += current.data;
            }
            current = current.next;
        }
        return list;
    }

    // Print the list from tail to head
    public void printBackward() {
        Node<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    /**
     * Checks if the list is empty
     */
    public boolean isEmpty()
    {
        return head == null;
    }

    /**
     * Finds a node with the given value
     * @param data The data in the node to find
     */
    public Node<T> find(T data)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.data.equals(data))
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes a node with the data from the list
     * @param data The data in the node to be removed
     */
    public void remove(T data)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.data.equals(data))
            {
                if (current.prev != null)
                {
                    current.prev.next = current.next;
                }
                else {
                    head = current.next;
                }
                if (current.next != null)
                {
                    current.next.prev = current.prev;
                }
                else {
                    tail = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    /**
     * Finds a node with the given value
     * @param data The data in the node to find
     */
    public boolean contains(T data)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.data.equals(data))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Insertion sort the doubly linked list.
     */
    public void insertionSort()
    {
        if (head == null) {
            return; // The list is empty
        }

        Node<T> sorted = null;
        Node<T> current = head;

        while (current != null)
        {
            Node<T> next = current.next;

            if (sorted == null || sorted.data.compareTo(current.data) >= 0)
            {
                current.next = sorted;
                if (sorted != null) {
                    sorted.prev = current;
                }
                sorted = current;
                sorted.prev = null;
            } else {
                Node<T> temp = sorted;
                while (temp.next != null && temp.next.data.compareTo(current.data) < 0) {
                    temp = temp.next;
                }
                current.next = temp.next;
                if (temp.next != null) {
                    temp.next.prev = current;
                }
                temp.next = current;
                current.prev = temp;
            }
            current = next;
        }

        head = sorted;

        // Update the tail
        tail = head;
        if (tail != null) {
            while (tail.next != null) {
                tail = tail.next;
            }
        }
    }
}
