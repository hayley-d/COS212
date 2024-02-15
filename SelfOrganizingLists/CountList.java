public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() {
        return new CountList<T>();
    }

    @Override
    public void access(T data) {
        //check if the list is empty
        if(isEmpty())
        {
            return;
        }
        //create a curret node variable
        Node<T> currentNode = this.head;

        //while the current node is not null
        while(currentNode != null)
        {
            //check if the current node data matches target
            if(currentNode.data.equals(data))
            {
                currentNode.accessCount += 1;
                break;
            }
            //if not go to the next node
            currentNode = currentNode.next;
        }
        //if the current node is null or the head do nothing
        if(head == currentNode || currentNode == null)
        {
            return;
        }
        //ensure order is correct
        //from list to current
        Node<T> prev = currentNode.prev;
        while(prev!=null)
        {
            //if the next node has less or equal accesses
            if(prev.accessCount <= currentNode.accessCount)
            {
                //swap the nodes
                swap(prev,currentNode);
                prev = currentNode.prev;
                continue;
            }
            prev = prev.prev;
        }
    }

    @Override
    protected void swap(Node<T> node1, Node<T> node2) {
        //check if node is the head
        if(node1 == head)
        {
            Node<T> prev = null;
            Node<T> next = node2.next;

            node2.prev = null;
            node2.next = node1;

            node1.prev = node2;
            node1.next = next;

            if(next != null)
            {
                next.prev = node1;
            }

            this.head = node2;
            return;
        }
        else if(node2 == tail)
        {
            Node<T> prev = node1.prev;
            Node<T> next = null;

            node2.prev = prev;
            if(prev !=null)
            {
                prev.next = node2;
            }

            node2.next = node1;
            node1.prev = node2;

            node1.next = null;
            this.tail = node1;
            return;
        }
        else{
            Node<T> prev = node1.prev;
            Node<T> next = node2.next;

            node2.prev = prev;
            if(prev !=null)
            {
                prev.next = node2;
            }

            node2.next = node1;
            node1.prev = node2;

            node1.next = next;
            if(next != null)
            {
                next.prev = node1;
            }
            return;
        }
    }
}
