// This is used to remove the warnings which occur when using generics
@SuppressWarnings("unchecked")
public class SkipListNode<T extends Comparable<T>> {
    public T key;
    public SkipListNode<T>[] next;

    public int level;

    public SkipListNode(T key, int levels) {
        this.key = key;
        this.level = levels;
        this.next = new SkipListNode[levels];
        for (SkipListNode<T> node: next)
        {
            node = null;
        }
    }

    @Override
    public String toString() {
        return "["+key+"]";
    }

    public String emptyString() {
        int length = toString().length();
        String emptyString = "";
        for(int i = 0; i < length;i++)
        {
            emptyString+="-";
        }
        return emptyString;
    }
}