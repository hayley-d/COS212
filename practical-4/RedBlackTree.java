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
            newNode.left = newNode.right = NULL_NODE;
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
            newNode.left = newNode.right = NULL_NODE;
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

                    recolour(grandparent.parent);

                    if(data.compareTo(parent.data)>0)
                    {
                        parent.right =newNode;
                    }
                    else{
                        parent.left =newNode;
                    }
                    newNode.parent = parent;
                    newNode.left = newNode.right = NULL_NODE;
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
                            parent.left =newNode;
                            newNode.parent = parent;
                            newNode.left = newNode.right = NULL_NODE;

                            rotateRight(grandparent);
                            parent.colour = BLACK;
                            grandparent.colour = RED;
                        }
                        else{
                            //go right -> zig zag
                            parent.right =newNode;
                            newNode.parent = parent;
                            newNode.left = newNode.right = NULL_NODE;
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
                            parent.right =newNode;
                            newNode.parent = parent;
                            newNode.left = newNode.right = NULL_NODE;

                            rotateLeft(grandparent);
                            parent.colour = BLACK;
                            grandparent.colour = RED;
                        }
                        else{
                            //go left -> zig zag
                            parent.left =newNode;
                            newNode.parent = parent;
                            newNode.left = newNode.right = NULL_NODE;
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
                        parent.left =newNode;
                        newNode.parent = parent;
                        newNode.left = newNode.right = NULL_NODE;

                        rotateRight(grandparent);
                        parent.colour = BLACK;
                        grandparent.colour = RED;
                    }
                    else{
                        //go right -> zig zag
                        parent.right =newNode;
                        newNode.parent = parent;
                        newNode.left = newNode.right = NULL_NODE;
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
                        parent.right =newNode;
                        newNode.parent = parent;
                        newNode.left = newNode.right = NULL_NODE;

                        rotateLeft(grandparent);
                        parent.colour = BLACK;
                        grandparent.colour = RED;
                    }
                    else{
                        //go left -> zig zag
                        parent.left =newNode;
                        newNode.parent = parent;
                        newNode.left = newNode.right = NULL_NODE;
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
        if(getRoot().equals(NULL_NODE))
        {
            //empty tree
            return true;
        }
        if(!isRootBlack())
        {
            return false;
        }

        if(!blackPath(SENTINEL.right))
        {
            return false;
        }

        if(!inorderValidChildren(getRoot()))
        {
            return false;
        }
        return true;
    }

    public void topDownDelete(T data)
    {
        if(SENTINEL.right == null || SENTINEL.right.equals(NULL_NODE))
        {
            return;
        }

        if(contains(data,SENTINEL.right))
        {
            //Examine the root node
            RedBlackNode<T> root = SENTINEL.right;
            RedBlackNode<T> target = SENTINEL.right;

            //Two black children
            if((root.left !=null || !root.left.equals(NULL_NODE))&&(root.right !=null || !root.right.equals(NULL_NODE)))
            {
                //Two children

                //both are black
                if(root.left.colour == BLACK && root.right.colour == BLACK)
                {
                    root.colour = RED;
                    target = (data.compareTo(root.data)>0)? root.right : (data.compareTo(root.data)==0)? root:root.left;
                }
            }
            else if(root.left == null || root.left.equals(NULL_NODE) && (root.right != null && !root.right.equals(NULL_NODE)))
            {
                //only right child

                if(root.right.colour == BLACK)
                {
                    root.colour = RED;
                    target = root.right;
                }
            }
            else if(root.right == null || root.right.equals(NULL_NODE) && (root.left != null && !root.left.equals(NULL_NODE)))
            {
                //only left child
                if(root.left.colour == BLACK)
                {
                    root.colour = RED;
                    target = root.left;
                }
            }


            recursiveDelete(target);


            RedBlackNode<T> node = null;
            //Delete found node
            if((node.left != null && node.right!=null) || (!node.left.equals(NULL_NODE) && !node.right.equals(NULL_NODE)))
            {
                //has both children
                RedBlackNode<T> replacement = smallestNode(node.right);
            }
            else if(node.left == null || node.left.equals(NULL_NODE))
            {
                //one right child
                RedBlackNode<T> replacement = smallestNode(node.right);
            }
            else if(node.right == null || node.right.equals(NULL_NODE))
            {
                //one left child
                RedBlackNode<T> replacement = LargestNode(node.left);
            }
        }

    }

    private RedBlackNode<T> recursiveDelete(RedBlackNode<T> x)
    {
        if(x == null || x.equals(NULL_NODE))
        {
            return null;
        }

        RedBlackNode<T> p = x.parent;
        RedBlackNode<T> t = null;
        if(p.left != null && !p.left.equals(NULL_NODE))
        {
            t = (p.left.equals(x))? p.right:p.left;
        }
        else{
            t = p.left;
        }

        if((x.left != null && !x.left.equals(NULL_NODE)) && (x.right != null && !x.right.equals(NULL_NODE)))
        {
            //Two children
            //both children are black
            if(x.left.colour == BLACK && x.right.colour ==BLACK)
            {
                //check T's children
                if(t!=null && !t.equals(NULL_NODE))
                {
                    if((t.right != null && !t.right.equals(NULL_NODE)) && (t.left != null && !t.left.equals(NULL_NODE)))
                    {
                        //both children
                        if(t.right.colour == BLACK && t.left.colour == BLACK)
                        {
                            //both are black
                            
                        }
                    }
                }
            }
            else{

            }

        }
        else if((x.left == null || x.left.equals(NULL_NODE)) && (x.right != null && !x.right.equals(NULL_NODE)))
        {
            //only right child

            if(x.right.colour == BLACK)
            {

            }
            else{

            }
        }
        else if(x.right == null || x.right.equals(NULL_NODE) && (x.left != null && !x.left.equals(NULL_NODE)))
        {
            //only left child
            if(x.left.colour == BLACK)
            {

            }
            else{

            }
        }
        else{
            //both are null
        }

    }

    private RedBlackNode<T> LargestNode(RedBlackNode<T> current)
    {
        if(current == null || current.equals(NULL_NODE))
        {
            return null;
        }

        if(current.right == null || current.right.equals(NULL_NODE))
        {
            return current;
        }

        return smallestNode(current.right);
    }

    private RedBlackNode<T> smallestNode(RedBlackNode<T> current)
    {
        if(current == null || current.equals(NULL_NODE))
        {
            return null;
        }

        if(current.left == null || current.left.equals(NULL_NODE))
        {
            return current;
        }

        return smallestNode(current.left);
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
            if(node.left != null && !node.equals(NULL_NODE) && node.left.colour == RED)
            {
                return false;
            }
            if(node.right != null && !node.equals(NULL_NODE) && node.right.colour == RED)
            {
                return false;
            }
        }
        return true;
    }

    private boolean inorderValidChildren(RedBlackNode<T> current)
    {
        if(current == null || current.equals(NULL_NODE))
        {
            return true;
        }
        inorderValidChildren(current.left);
        if(!validChildren(current) || !validColours(current)){
            return false;
        }
        inorderValidChildren(current.right);
        return true;
    }

    private boolean blackPath(RedBlackNode<T> node){
        if(node == null || node.equals(NULL_NODE))
        {
            return true;
        }

        if(blackHeight(node.left) != blackHeight(node.right))
        {
            return false;
        }
        else{
            return blackPath(node.left) && blackPath(node.right);
        }
    }

    private int blackHeight(RedBlackNode<T> current)
    {
        if(current == null || current.equals(NULL_NODE))
        {
            return 1;
        }

        int left = blackHeight(current.left);
        int right = blackHeight(current.right);

        return (current.colour == BLACK ? left + 1 : left) == right ? right : -1;
    }


    private RedBlackNode<T> findParent(T data, RedBlackNode<T> current)
    {
        if(current.equals(NULL_NODE)){
            return null;
        }

        if(current.data.compareTo(data)>0)
        {
            //go left
            if(current.left.equals(NULL_NODE))
            {
                return current;
            }
            else{
                return findParent(data,current.left);
            }
        }
        else if(current.data.compareTo(data)<0){
            //go right
            if(current.right.equals(NULL_NODE))
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

    private RedBlackNode<T> recolour(RedBlackNode<T> current)
    {
        if(current == null || current.equals(NULL_NODE))
        {
            return null;
        }

        RedBlackNode<T> parent = current.parent;



        if(parent != null && !parent.equals(SENTINEL) )
        {
            parent.left.colour= BLACK;
            parent.right.colour = BLACK;
        }

        return recolour(parent);
    }

    private boolean contains(T data, RedBlackNode<T> node)
    {
        if(node == null || node.equals(NULL_NODE))
        {
            return false;
        }

        if(node.data == data)
        {
            return true;
        }

        if(node.data.compareTo(data)>0)
        {
            return contains(data,node.left);
        }
        else{
            return contains(data,node.right);
        }
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
