public class CountList<T extends Comparable<T>> extends SelfOrderingList<T>{
    @Override
    public SelfOrderingList<T> getBlankList() {
        CountList<T> countList = new CountList<T>();
        return countList;
    }

    @Override
    public void access(T data) {
        if(isEmpty())
        {
            return;
        }
        Node<T> currentNode = this.head;
        while(currentNode != null)
        {
            if(currentNode.data.equals(data))
            {
                currentNode.accessCount += 1;
                break;
            }
        }
        if(head == currentNode)
        {
            return;
        }
        //ensure order is correct
        //from list to current
        Node<T> prev = currentNode.prev;
        while(prev!=null)
        {
            if(prev.accessCount <= currentNode.accessCount)
            {
                Node<T> temp = prev.prev;
                Node<T> next = currentNode.next;
                if(temp !=null)
                {
                    temp.next = currentNode;
                }
                currentNode.prev = temp;

                prev.next = next;
                if(next!=null)
                {
                    next.prev = prev;
                }

                currentNode.next = prev;
                prev.prev = currentNode;
                prev = currentNode.prev;
                continue;
            }
            prev = prev.prev;
        }
    }
}
