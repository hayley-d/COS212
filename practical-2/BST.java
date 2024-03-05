public class BST<T extends Comparable<T>> {

    BinaryNode<T> root;

    public BST() {
        this.root = null;
    }

    public void delete(T data) {
        if(root == null)
        {
            return;
        }

        if(root.data.equals(data))
        {
            BinaryNode<T> temp = root.right;
            root = root.left;

            if(temp!=null)
            {
                if(root==null)
                {
                    root = temp;
                    return;
                }

                BinaryNode<T> maxRightChild = maxRight(root);
                maxRightChild.right = temp;
                return;
            }
            return;
        }

        if(root.data.compareTo(data)<0)
        {
            recursiveRightDelete(data,root.right,root);
        }
        else{
            recursiveLeftDelete(data,root.left,root);
        }
    }

    public boolean contains(T data) {
        return recursiveFind(root,data)!=null;
    }

    public void insert(T data) {
        if(root == null)
        {
            this.root = new BinaryNode<T>(data);
            return;
        }
        BinaryNode<T> newNode = new BinaryNode<>(data);
        recursiveInsert(newNode,root);
    }

    public int getHeight() {
        return recursiveHeight(root);
    }

    public String printSearchPath(T data) {
        if(root == null)
        {
            return "Null";
        }

        if(root.data.equals(data))
        {
            return root.data+"";
        }
        else{
            String path = root.data+"";
            if(root.data.compareTo(data)<0)
            {
                return recursivePath(data,path,root.right);
            }
            else{
                return recursivePath(data,path,root.left);
            }
        }
    }

    public int getNumLeaves() {
        return recursiveCountLeaves(root);
    }

    public BST<T> extractBiggestSuperBalancedSubTree() {
        return null;
    }

    public BinaryNode<T> getNode(T data) {
        return recursiveFind(root,data);
    }

    public boolean isSuperBalanced() {
        return false;
    }

    public BinaryNode<T> findMax() {
        return recursiveFindMax(root);
    }

    public BinaryNode<T> findMin() {
        return recursiveFindMin(root);
    }

    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }

    //HELPER FUNCTIONS

    private void recursiveInsert(BinaryNode<T> node,BinaryNode<T> current)
    {
        if(current == null)
        {
            return;
        }

        if(node.data.equals(current.data))
        {
            return;
        }

        if(node.data.compareTo(current.data)>0)
        {
            if(current.right == null)
            {
                current.right = node;
                return;
            }
            else{
                recursiveInsert(node,current.right);
            }
        }else{
            if(current.left == null)
            {
                current.left = node;
                return;
            }
            else{
                recursiveInsert(node,current.left);
            }
        }
    }

    private BinaryNode<T> maxRight(BinaryNode<T> node)
    {
        if(node == null)
        {
            return null;
        }

        if(node.right == null)
        {
            return node;
        }

        return maxRight(node.right);
    }

    private void recursiveRightDelete(T data, BinaryNode<T> node, BinaryNode<T> parent)
    {
        if(node == null)
        {
            return;
        }

        if(node.data.equals(data))
        {
            if(node.left == null && node.right == null)
            {
                //leaf
                parent.right = null;
            }
            else if(node.right == null)
            {
                parent.right = node.left;
                return;
            }
            else if(node.left == null)
            {
                parent.right = node.right;
                return;
            }
            else{
                BinaryNode<T> temp = node.right;
                BinaryNode<T> maxRight = maxRight(node.left);

                parent.right = node.left;
                maxRight.right = temp;
                return;
            }

        }

        if(node.data.compareTo(data)<0)
        {
            recursiveRightDelete(data,node.right,node);
        }
        else{
            recursiveLeftDelete(data,node.left,node);
        }
    }

    private void recursiveLeftDelete(T data, BinaryNode<T> node, BinaryNode<T> parent)
    {
        if(node == null)
        {
            return;
        }

        if(node.data.equals(data))
        {
            if(node.left == null && node.right == null)
            {
                parent.left = null;
                return;
            }
            else if(node.left == null)
            {
                parent.left = node.right;
                return;
            }
            else if(node.right == null)
            {
                parent.left = node.left;
                return;
            }
            else{
                BinaryNode<T> temp = node.right;
                BinaryNode<T> maxRight = maxRight(node.left);

                parent.left = node.left;

                maxRight.right = temp;
                return;
            }
        }

        if(node.data.compareTo(data)<0)
        {
            recursiveRightDelete(data,node.right,node);
        }
        else{
            recursiveLeftDelete(data,node.left,node);
        }
    }

    private BinaryNode<T> recursiveFindMax(BinaryNode<T> current)
    {
        if(current == null)
        {
            return null;
        }

        if(current.right == null)
        {
            return current;
        }

        return recursiveFindMax(current.right);
    }

    private BinaryNode<T> recursiveFindMin(BinaryNode<T> current)
    {
        if(current == null)
        {
            return null;
        }

        if(current.left == null)
        {
            return current;
        }

        return recursiveFindMin(current.left);
    }

    private BinaryNode<T> recursiveFind(BinaryNode<T> current,T data)
    {
        if(current == null)
        {
            return null;
        }

        if(current.data.equals(data))
        {
            return current;
        }

        if(current.data.compareTo(data)<0)
        {
            //go right
            return recursiveFind(current.right,data);
        }
        else{
            return recursiveFind(current.left,data);
        }
    }

    private int recursiveCountLeaves(BinaryNode<T> current)
    {
        if(current == null)
        {
            return 0;
        }

        if(current.left == null && current.right == null)
        {
            return 1;
        }

        return recursiveCountLeaves(current.left) + recursiveCountLeaves(current.right);
    }

    private int recursiveHeight(BinaryNode<T> current)
    {
        if(current == null)
        {
            return 0;
        }

        int tallestChild = max(recursiveHeight(current.right),recursiveHeight(current.left));
        return 1+tallestChild;
    }

    private int max(int num1, int num2)
    {
        if(num1 > num2)
        {
            return num1;
        }else{
            return num2;
        }
    }

    private String recursivePath(T data,String path,BinaryNode<T> current)
    {
        if(current == null)
        {
            path += " -> Null";
            return path;
        }

        path += " -> " +current.data;

        if(current.data.equals(data))
        {
            return path;
        }
        else if(current.data.compareTo(data)<0)
        {
            //go right
            return recursivePath(data,path,current.right);
        }
        else{
            return recursivePath(data,path,current.left);
        }
    }
}
