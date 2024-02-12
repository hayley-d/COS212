public class Node<T extends Comparable<T>>{
    public Node<T> next;
    public Node<T> prev;
    public int accessCount; //keeps track of the amount of access counts only used in couting list
    public T data; //data stored by the node

    public Node(T data){
       this.data = data;
       this.accessCount = 0;
       this.prev = null;
       this.next = null;
    }

    @Override
    public String toString() {
        return "(" + data.toString() + "[" + accessCount + "]" + ")";
    }
}
