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
        //If no nodes in the tree
        if(SENTINEL.right == null || SENTINEL.right.equals(NULL_NODE))
        {
            return;
        }

        //if the node is in the tree
        RedBlackNode<T> root = SENTINEL.right;
        RedBlackNode<T> nodeToDelete = contains(data,root);
        RedBlackNode<T> x = null;
        if(nodeToDelete != null)
        {
            if((nodeToDelete.left == null  || nodeToDelete.left.equals(NULL_NODE)) && (nodeToDelete.right == null  || nodeToDelete.right.equals(NULL_NODE)))
            {
                //two NIL Children
                RedBlackNode<T> parent = nodeToDelete.parent;
                if(parent == null || parent.equals(NULL_NODE))
                {
                    //node is the root
                    SENTINEL.right = NULL_NODE;
                    nodeToDelete.right = null;
                    nodeToDelete.left = null;
                    nodeToDelete.parent = null;
                    return;
                }
                else
                {
                    //node is not the root
                    if(nodeToDelete.colour == BLACK)
                    {
                        x = NULL_NODE;
                        RedBlackNode<T> parentX = x.parent;
                        RedBlackNode<T> w = null;
                        String side = "";
                        //Node is BLACK
                        if(parent.left!= null && parent.left.equals(nodeToDelete))
                        {
                            //node is the left child
                            parent.left = NULL_NODE;
                            w = parentX.right;
                            side = "left";
                        }
                        else{
                            //node is the right child
                            parent.right = NULL_NODE;
                            w = parentX.left;
                            side = "right";
                        }
                        //x is black
                        if(w != null && w.colour == RED)
                        {
                            case1(x,parentX,w);
                        }
                        else if(w != null && w.colour == BLACK)
                        {
                            if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK)&&(w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                            {
                                case2(x,parentX,w);
                            }
                            else if((w.left ==null || w.left.equals(NULL_NODE))&&(w.right ==null && w.right.equals(NULL_NODE)))
                            {
                                case2(x,parentX,w);
                            }
                            else if(side.equals("left") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                            {
                                if((w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                                {
                                    case3(x,parentX,w);
                                }
                                else if((w.right ==null || !w.right.equals(NULL_NODE)))
                                {
                                    case3(x,parentX,w);
                                }
                            }
                            else if(side.equals("right") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                            {
                                if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK))
                                {
                                    case3(x,parentX,w);
                                }
                                else if((w.left ==null || !w.left.equals(NULL_NODE)))
                                {
                                    case3(x,parentX,w);
                                }
                            }
                            else if(side.equals("left") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                            {
                                case4(x,parentX,w);
                            }
                            else if(side.equals("right") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                            {
                                case4(x,parentX,w);
                            }

                        }
                    }
                    else{
                        //node is red
                        if(parent.left!=null && parent.left.equals(nodeToDelete))
                        {
                            parent.left = NULL_NODE;
                            return;
                        }
                        else{
                            parent.right = NULL_NODE;
                            return;
                        }
                    }
                }
            }
            else  if((nodeToDelete.left == null  || nodeToDelete.left.equals(NULL_NODE)) && (nodeToDelete.right != null  || !nodeToDelete.right.equals(NULL_NODE)))
            {
                //only right child
                RedBlackNode<T> parent = nodeToDelete.parent;
                RedBlackNode<T> replacement = smallestNode(nodeToDelete.right);

                RedBlackNode<T> child = replacement.right;
                replacement.parent.left = child;
                if(child!=null && !child.equals(NULL_NODE))
                {
                    child.parent =  replacement.parent;
                }
                replacement.right = nodeToDelete.right;
                if(nodeToDelete.right != null && !nodeToDelete.right.equals(NULL_NODE))
                {
                    nodeToDelete.right.parent = replacement;
                }

                nodeToDelete.left = null;
                nodeToDelete.right = null;
                nodeToDelete.parent = null;

                //root
                if(parent == null)
                {
                    SENTINEL.right = replacement;
                    replacement.parent = null;
                    replacement.colour = BLACK;
                    return;
                }
                else if(!parent.equals(NULL_NODE))
                {
                    //not root
                    if(nodeToDelete.colour ==BLACK)
                    {
                        //node is black
                        if(parent.left !=null && parent.left.equals(nodeToDelete))
                        {
                            parent.left = replacement;
                            replacement.parent = parent;
                        }
                        else{
                            parent.right = replacement;
                            replacement.parent = parent;
                        }

                        if(replacement.colour == RED)
                        {
                            replacement.colour = BLACK;
                        }
                        else{
                            x = replacement;
                            RedBlackNode<T> parentX = x.parent;
                            RedBlackNode<T> w = null;
                            String side = "";

                            //Node is BLACK
                            if(parent.left!= null && parent.left.equals(replacement))
                            {
                                //node is the left child
                                w = parentX.right;
                                side = "left";
                            }
                            else{
                                //node is the right child
                                w = parentX.left;
                                side = "right";
                            }

                            if(w != null && w.colour == RED)
                            {
                                case1(x,parentX,w);
                            }
                            else if(w != null && w.colour == BLACK)
                            {
                                if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK)&&(w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                                {
                                    case2(x,parentX,w);
                                }
                                else if((w.left ==null || w.left.equals(NULL_NODE))&&(w.right ==null && w.right.equals(NULL_NODE)))
                                {
                                    case2(x,parentX,w);
                                }
                                else if(side.equals("left") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                                {
                                    if((w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                                    {
                                        case3(x,parentX,w);
                                    }
                                    else if((w.right ==null || !w.right.equals(NULL_NODE)))
                                    {
                                        case3(x,parentX,w);
                                    }
                                }
                                else if(side.equals("right") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                                {
                                    if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK))
                                    {
                                        case3(x,parentX,w);
                                    }
                                    else if((w.left ==null || !w.left.equals(NULL_NODE)))
                                    {
                                        case3(x,parentX,w);
                                    }
                                }
                                else if(side.equals("left") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                                {
                                    case4(x,parentX,w);
                                }
                                else if(side.equals("right") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                                {
                                    case4(x,parentX,w);
                                }
                            }
                        }
                    }
                    else{
                        //node is red
                    }

                }
            }
            else  if((nodeToDelete.left != null  || !nodeToDelete.left.equals(NULL_NODE)) && (nodeToDelete.right == null  || nodeToDelete.right.equals(NULL_NODE)))
            {
                //only left child
                RedBlackNode<T> parent = nodeToDelete.parent;
                RedBlackNode<T> replacement = LargestNode(nodeToDelete.left);

                RedBlackNode<T> child = replacement.left;
                replacement.parent.right = child;
                if(child!=null && !child.equals(NULL_NODE))
                {
                    child.parent =  replacement.parent;
                }
                replacement.left = nodeToDelete.left;
                if(nodeToDelete.left != null && !nodeToDelete.left.equals(NULL_NODE))
                {
                    nodeToDelete.left.parent = replacement;
                }


                if(parent == null)
                {
                    SENTINEL.right = replacement;
                    replacement.parent = null;
                    return;
                }
                else if(parent != null && !parent.equals(NULL_NODE))
                {
                    if(parent.left!=null && parent.left.equals(nodeToDelete))
                    {
                        parent.left = replacement;
                        replacement.parent = parent;
                        return;
                    }
                    else{
                        parent.right = replacement;
                        replacement.parent = parent;
                        return;
                    }
                }
            }
            else  if((nodeToDelete.left != null  || !nodeToDelete.left.equals(NULL_NODE)) && (nodeToDelete.right != null  || !nodeToDelete.right.equals(NULL_NODE)))
            {
                //both children
                RedBlackNode<T> parent = nodeToDelete.parent;
                RedBlackNode<T> replacement = smallestNode(nodeToDelete.right);

                RedBlackNode<T> child = replacement.right;
                replacement.parent.left = child;
                if(child!=null && !child.equals(NULL_NODE))
                {
                    child.parent =  replacement.parent;
                }
                replacement.right = nodeToDelete.right;
                if(nodeToDelete.right != null && !nodeToDelete.right.equals(NULL_NODE))
                {
                    nodeToDelete.right.parent = replacement;
                }
                replacement.left = nodeToDelete.left;
                if(nodeToDelete.left != null && !nodeToDelete.left.equals(NULL_NODE))
                {
                    nodeToDelete.left.parent = replacement;
                }


                if(parent == null)
                {
                    SENTINEL.right = replacement;
                    replacement.parent = null;
                }
                else if(parent != null && !parent.equals(NULL_NODE))
                {
                    if(parent.left!=null && parent.left.equals(nodeToDelete))
                    {
                        parent.left = replacement;
                        replacement.parent = parent;
                    }
                    else{
                        parent.right = replacement;
                        replacement.parent = parent;
                    }
                }

                if(replacement.colour == RED && nodeToDelete.colour == RED)
                {
                    return;
                }
                else if(replacement.colour == RED && nodeToDelete.colour == BLACK)
                {
                    replacement.colour = BLACK;
                    return;
                }
            }


        }
        else{
            //node not in the tree
            return;
        }
    }


    private void case1(RedBlackNode<T> x,RedBlackNode<T> parent,RedBlackNode<T> w)
    {
        //X is Black/NULL and sibling is red
        if(x == null)
        {
            return;
        }

        w.colour = BLACK;
        parent.colour = RED;
        String side = "";
        if(parent.left.equals(x))
        {
            rotateLeft(parent);
            w = parent.right;
            side = "left";
        }
        else{
            rotateRight(parent);
            w = parent.left;
            side = "right";
        }

        //Case check 2,3 or 4
        if(w != null && w.colour == BLACK)
        {
            if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK)&&(w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
            {
                case2(x,parent,w);
            }
            else if((w.left ==null || w.left.equals(NULL_NODE))&&(w.right ==null && w.right.equals(NULL_NODE)))
            {
                case2(x,parent,w);
            }
            else if(side.equals("left") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
            {
                if((w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                {
                    case3(x,parent,w);
                }
                else if((w.right ==null || !w.right.equals(NULL_NODE)))
                {
                    case3(x,parent,w);
                }
            }
            else if(side.equals("right") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
            {
                if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK))
                {
                    case3(x,parent,w);
                }
                else if((w.left ==null || !w.left.equals(NULL_NODE)))
                {
                    case3(x,parent,w);
                }
            }
            else if(side.equals("left") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
            {
                case4(x,parent,w);
            }
            else if(side.equals("right") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
            {
                case4(x,parent,w);
            }
        }
    }

    private void case2(RedBlackNode<T> x,RedBlackNode<T> parent,RedBlackNode<T> w)
    {
        if(w!=null || !w.equals(NULL_NODE))
        {
            w.colour = RED;
        }

        x = parent;
        String side = "";
        if(x != null && !x.equals(NULL_NODE))
        {
            parent = x.parent;
        }
        if(parent!=null && !parent.equals(NULL_NODE))
        {
            if(parent.left.equals(x))
            {
                w = parent.right;
                side = "left";
            }
            else{
                w = parent.left;
                side = "right";
            }
        }


        if(x != null && !x.equals(NULL_NODE) && x.colour == RED)
        {
            case0(x,parent,w);
        }
        else if(parent == null)
        {
            //root node
            return;
        }
        else{
            //cases 1, 2, 3 or 4
            if(w != null && w.colour == BLACK)
            {
                if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK)&&(w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                {
                    case2(x,parent,w);
                }
                else if((w.left ==null || w.left.equals(NULL_NODE))&&(w.right ==null && w.right.equals(NULL_NODE)))
                {
                    case2(x,parent,w);
                }
                else if(side.equals("left") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                {
                    if((w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == BLACK))
                    {
                        case3(x,parent,w);
                    }
                    else if((w.right == null || w.right.equals(NULL_NODE)))
                    {
                        case3(x,parent,w);
                    }
                }
                else if(side.equals("right") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                {
                    if((w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == BLACK))
                    {
                        case3(x,parent,w);
                    }
                    else if((w.left == null || w.left.equals(NULL_NODE)))
                    {
                        case3(x,parent,w);
                    }
                }
                else if(side.equals("left") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
                {
                    case4(x,parent,w);
                }
                else if(side.equals("right") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
                {
                    case4(x,parent,w);
                }
            }
            else{
                //w is red
                case1(x,parent,w);
            }
        }
    }

    private void case3(RedBlackNode<T> x,RedBlackNode<T> parent,RedBlackNode<T> w)
    {
        String side = "";
        if(parent.left !=null && parent.left.equals(x))
        {
            side = "left";
        }
        else{
            side = "right";
        }

        if(w != null && !w.equals(NULL_NODE))
        {
            if(side.equals("left") && (w.left !=null && !w.left.equals(NULL_NODE) && w.left.colour == RED))
            {
                w.left.colour = BLACK;
                w.colour = RED;
                rotateRight(w);
                w = parent.right;
            }
            else if(side.equals("right") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
            {
                w.right.colour = BLACK;
                w.colour = RED;
                rotateLeft(w);
                w = parent.left;
            }
        }
        case4(x,parent,w);
    }

    private void case4(RedBlackNode<T> x,RedBlackNode<T> parent,RedBlackNode<T> w)
    {
        String side = "";
        if(parent.left !=null && parent.left.equals(x))
        {
            side = "left";
        }
        else{
            side = "right";
        }

        if(w!=null && !w.equals(NULL_NODE))
        {

            if(side.equals("left") && (w.right !=null && !w.right.equals(NULL_NODE) && w.right.colour == RED))
            {
                w.colour = parent.colour;

                if(parent != null)
                {
                    parent.colour = BLACK;
                }
                w.right.colour = BLACK;
                rotateLeft(parent);
            }
            else{
                w.colour = parent.colour;
                if(parent != null)
                {
                    parent.colour = BLACK;
                }
                w.left.colour = BLACK;
                rotateRight(parent);
            }
        }
    }

    private void case0(RedBlackNode<T> x,RedBlackNode<T> parent,RedBlackNode<T> w)
    {
        x.colour = BLACK;
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

    private RedBlackNode<T>  contains(T data, RedBlackNode<T> node)
    {
        if(node == null || node.equals(NULL_NODE))
        {
            return null;
        }

        if(node.data.equals(data))
        {
            return node;
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
