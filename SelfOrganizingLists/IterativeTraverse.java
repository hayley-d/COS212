public class IterativeTraverse<T extends Comparable<T>> extends Traverser<T>{
    public IterativeTraverse(){
       this.list = null;
    };
    
    public IterativeTraverse(SelfOrderingList<T> list){
        this.list = list.getBlankList();

        Node<T> otherCurrent = list.head;

        while(otherCurrent!=null)
        {
            this.list.insert(otherCurrent.data);
            this.list.setCount(otherCurrent.data,otherCurrent.accessCount);
            otherCurrent = otherCurrent.next;
        }
    }



    @Override
    public SelfOrderingList<T> reverseList() {
        SelfOrderingList<T> newList = list.getBlankList();
        if(!list.isEmpty())
        {
            newList.head = new Node<>(list.tail.data);

            //go through list in reverse
            Node<T> newCurrent = newList.head;
            Node<T> current = list.tail.prev;
            while(current!=null)
            {
                newList.insert(current.data);
                newList.setCount(current.data,current.accessCount);
                current = current.prev;
            }
            return newList;
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
        Node<T> current = this.list.head;
        while(current!=null)
        {
            if(current.data.equals(data))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        if(this.list == null || this.list.isEmpty())
        {
            return "";
        }
        String myList = "";
        Node<T> current = this.list.head;
        while(current!=null)
        {
            myList += "->(" + current.data+"["+current.accessCount+"])";
            current = current.next;
        }
        return myList;
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
        for(int i = 0; i < pos; i++)
        {
            current = current.next;
        }
        return current;
    }

    @Override
    public Node<T> find(T data) {
        //Check if the list is empty
        if(list.isEmpty())
        {
            return null;
        }
        Node<T> current = this.list.head;
        while(current!=null)
        {
            if(current.data.equals(data))
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        int numberOfNodes = 0;
        if(list.isEmpty())
        {
            return 0;
        }
        Node<T> current = this.list.head;
        while(current!=null)
        {
            numberOfNodes++;
            current = current.next;
        }
        return numberOfNodes;
    }

    @Override
    public SelfOrderingList<T> clone(SelfOrderingList<T> otherList) {
        if(otherList == null || otherList.isEmpty())
        {
            return null;
        }
        Node<T> current = otherList.head;
        SelfOrderingList<T> newList = otherList.getBlankList();

        while(current!=null)
        {
            newList.insert(current.data);
            current = current.next;
        }

        return newList;
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
