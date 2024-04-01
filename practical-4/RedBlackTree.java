public class RedBlackTree<T extends Comparable<T>> {

    /*
     * Sentinel is not the root. Go check the text book if this doesn't make sense
     */
    public RedBlackNode<T> SENTINEL;
    public RedBlackNode<T> NULL_NODE;

    public static final int RED = 1;
    public static final int BLACK = 0;

    public RedBlackTree() {
        SENTINEL = new RedBlackNode<>(null);
        SENTINEL.right = null;
        SENTINEL.left = null;

        NULL_NODE = new RedBlackNode<>(null);
        NULL_NODE.left = null;
        NULL_NODE.right = null;

    }

    public RedBlackNode<T> getRoot() {
        return (SENTINEL.right != null) ? SENTINEL.right:NULL_NODE;
    }

    public void bottomUpInsert(T data) {
        RedBlackNode<T> newNode = new RedBlackNode<>(data);
        newNode.colour = RED;
        //check if root is null
        if(getRoot() == NULL_NODE)
        {
            newNode.colour = BLACK;
            this.SENTINEL.right = newNode;
            return;
        }

        RedBlackNode<T> parent = findParent(data,getRoot());

        if(parent == null)
        {
            return;
        }

        if(parent.colour == BLACK)
        {
            //Insert then finish
            if(data.compareTo(parent.data)>0)
            {
                parent.right =newNode;
            }
            else{
                parent.left =newNode;
            }
            newNode.parent = parent;
            return;
        }

        if(parent.parent!=null)
        {
            //has grandparent
            RedBlackNode<T> grandparent = parent.parent;
            RedBlackNode<T> uncle = (grandparent.left != null && grandparent.left.equals(parent))? grandparent.right:grandparent.left;

            if(uncle!=null)
            {
                if(uncle.colour == RED)
                {
                    //uncle is red
                    //recolour
                    grandparent.colour = RED;
                    parent.colour = BLACK;
                    uncle.colour = BLACK;

                    if(data.compareTo(parent.data)>0)
                    {
                        parent.right =newNode;
                    }
                    else{
                        parent.left =newNode;
                    }
                    newNode.parent = parent;
                    getRoot().colour = BLACK;
                    return;
                }
                else{
                    //uncle is black
                    if(grandparent.left.equals(parent))
                    {
                        if(parent.data.compareTo(data)>0)
                        {
                            //go left -> zig zig
                            rotateRight(grandparent);
                            parent.colour = BLACK;
                            grandparent.colour = RED;
                        }
                        else{
                            //go right -> zig zag
                            parent.right =newNode;
                            newNode.parent = parent;
                            //right rotate new Node
                            rotateLeft(parent);
                            rotateRight(grandparent);
                            newNode.colour = BLACK;
                            grandparent.colour = RED;
                        }
                    }
                    else{
                        //grandparent.right == parent
                        if(parent.data.compareTo(data)<0)
                        {
                            //go right -> zig zig
                            rotateLeft(grandparent);
                            parent.colour = BLACK;
                            grandparent.colour = RED;
                        }
                        else{
                            //go left -> zig zag
                            parent.left =newNode;
                            newNode.parent = parent;
                            //Left rotate new node
                            rotateRight(parent);
                            rotateLeft(grandparent);
                            newNode.colour = BLACK;
                            grandparent.colour = RED;
                        }
                    }
                    getRoot().colour = BLACK;
                    return;
                }
            }
            else{
                //uncle is black
                if(grandparent.left.equals(parent))
                {
                    if(parent.data.compareTo(data)>0)
                    {
                        //go left -> zig zig
                        rotateRight(grandparent);
                        parent.colour = BLACK;
                        grandparent.colour = RED;
                    }
                    else{
                        //go right -> zig zag
                        parent.right =newNode;
                        newNode.parent = parent;
                        //right rotate new Node
                        rotateLeft(parent);
                        rotateRight(grandparent);
                        newNode.colour = BLACK;
                        grandparent.colour = RED;
                    }
                }
                else{
                    //grandparent.right == parent
                    if(parent.data.compareTo(data)<0)
                    {
                        //go right -> zig zig
                        rotateLeft(grandparent);
                        parent.colour = BLACK;
                        grandparent.colour = RED;
                    }
                    else{
                        //go left -> zig zag
                        parent.left =newNode;
                        newNode.parent = parent;
                        //Left rotate new node
                        rotateRight(parent);
                        rotateLeft(grandparent);
                        newNode.colour = BLACK;
                        grandparent.colour = RED;
                    }
                }
                getRoot().colour = BLACK;
                return;
            }
        }

    }

    public boolean isValidRedBlackTree() {
        return false;
    }

    public void topDownDelete(T data) {

    }

    private boolean isRootBlack(){
        return SENTINEL.right.colour == BLACK;
    }

    private boolean validColours(RedBlackNode<T> node){
        return node.colour == BLACK || node.colour == RED;
    }

    private boolean validChildren(RedBlackNode<T> node)
    {
        if(node.colour == RED)
        {
            //if any child is red then invalid
            if(node.left != null && node.left.colour == RED)
            {
                return false;
            }
            if(node.right != null && node.right.colour == RED)
            {
                return false;
            }
        }
        return true;
    }

    private boolean blackPath(RedBlackNode<T> node){
        if(node.equals(NULL_NODE))
        {
            return true;
        }

        if(blackHeight(node.left) != blackHeight(node.right))
        {
            return false;
        }
        else{
            blackPath(node.left);
            blackPath(node.right);
        }
        return true;
    }

    private int blackHeight(RedBlackNode<T> current)
    {
        if(current.equals(NULL_NODE))
        {
            return 1;
        }

        if(current.colour == BLACK)
        {
            return blackHeight(current.left) + blackHeight(current.right) + 1;
        }
        else{
            return blackHeight(current.left) + blackHeight(current.right);
        }
    }

    private RedBlackNode<T> findParent(T data, RedBlackNode<T> current)
    {
        if(current == null){
            return null;
        }

        if(current.data.compareTo(data)>0)
        {
            //go left
            if(current.left == null)
            {
                return current;
            }
            else{
                return findParent(data,current.left);
            }
        }
        else if(current.data.compareTo(data)<0){
            //go right
            if(current.right == null)
            {
                return current;
            }
            else{
                return findParent(data,current.right);
            }
        }
        else{
            return null;
        }
    }

    private RedBlackNode<T> rotateRight(RedBlackNode<T> parent)
    {
        RedBlackNode<T> node = parent.left;
        parent.left = node.right;

        if(node.right !=null)
        {
            node.right.parent = parent;
        }
        node.parent = parent.parent;

        if(parent.parent != null)
        {
            if(parent.parent.left == parent)
            {
                parent.parent.left = node;
            }
            else{
                parent.parent.right = node;
            }
        }
        else{
            SENTINEL.right = node;
        }

        node.right = parent;
        parent.parent = node;
        return node;
    }

    private RedBlackNode<T> rotateLeft(RedBlackNode<T> parent) {
        RedBlackNode<T> node = parent.right;
        parent.right = node.left;

        if(node.left !=null)
        {
            node.left.parent = parent;
        }
        node.parent = parent.parent;

        if(parent.parent != null)
        {
            if(parent.parent.left == parent)
            {
                parent.parent.left = node;
            }
            else{
                parent.parent.right = node;
            }
        }
        else{
            SENTINEL.right = node;
        }
        node.left = parent;
        parent.parent = node;
        return node;
    }

    /* -------------------------------------------------------------------------- */
    /* Private methods, which shouldn't be called from outside the class */
    /* -------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------- */
    /* Please don't change this toString, I worked really hard to make it pretty. */
    /* Also, it matches the website. -------------------------------------------- */
    /* Also, also, we might test against it ;) ---------------------------------- */
    /* -------------------------------------------------------------------------- */
    private StringBuilder toString(RedBlackNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != NULL_NODE) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
        if (node.left != NULL_NODE) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return SENTINEL.right == NULL_NODE ? "Empty tree"
                : toString(SENTINEL.right, new StringBuilder(), true, new StringBuilder()).toString();
    }

    public String toVis() {
        return toVisHelper(getRoot());
    }

    private String toVisHelper(RedBlackNode<T> node) {
        if (node == NULL_NODE) {
            return "{}";
        }
        String leftStr = toVisHelper(node.left);
        String rightStr = toVisHelper(node.right);
        return "{" + node.toString() + leftStr + rightStr + "}";
    }

}
