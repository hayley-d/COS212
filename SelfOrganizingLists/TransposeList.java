public class TransposeList<T extends Comparable<T>> extends SelfOrderingList<T> {
    @Override
    public SelfOrderingList<T> getBlankList() {
        return new TransposeList<T>();
    }

    @Override
    public void access(T data) {
        if(isEmpty())
        {
            return;
        }
        //not empty
        Node<T> current = this.head;
        while(current.next!=null)
        {
            if(current.next.data.equals(data))
            {
                break;
            }
            current = current.next;
        }
        if(current.next!=null)
        {
            Node<T> temp = current.next;
            Node<T> next = temp.next;
            Node<T> prev = current.prev;
            if(current == head)
            {
                head.prev = temp;
                head.next = next;
                if(next!=null)
                {
                    next.prev = current;
                }
                temp.next = head;
                temp.prev = null;
                this.head = temp;
                return;
            }

            if(prev!=null)
            {
                prev.next = temp;
            }
            temp.prev = prev;
            if(next!=null)
            {
                next.prev = current;
            }
            current.next = next;

            current.prev = temp;
            temp.next = current;
        }

    }
}
