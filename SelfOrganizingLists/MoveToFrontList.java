public class MoveToFrontList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        return new MoveToFrontList<>();
    }

    @Override
    public void access(T data) {
       // check is the list is empty
        if(isEmpty())
        {
            return;
        }
        Node<T> currentNode = this.head;

        while(currentNode!=null)
        {
            if(currentNode.data.equals(data))
            {
                //node found!
                break;
            }
            currentNode = currentNode.next;
        }
        if(currentNode!=null)
        {
            //if the node was found
            if(head == currentNode)
            {
                return;
            }
            //If not the head node
            Node<T> prev = currentNode.prev;
            Node<T> next = currentNode.next;
            if(prev!=null)
            {
                prev.next = next;
            }
            if(next!=null)
            {
                next.prev = prev;
            }
            currentNode.prev = null;
            currentNode.next = head;
            head.prev = currentNode;
            this.head = currentNode;
        }
    }
}
