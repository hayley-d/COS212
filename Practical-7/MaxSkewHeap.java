public class MaxSkewHeap {
    Node root;

    @Override
    public String toString() {
        return (root == null ? "Empty Tree" : toString(root, "", true));
    }

    public String toString(Node node, String prefix, boolean end) {
        String res = "";
        if (node.right != null) {
            res += toString(node.right, prefix + (end ? "│   " : "    "), false);
        }
        res += prefix + (end ? "└── " : "┌── ") + node.toString() + "\n";
        if (node.left != null) {
            res += toString(node.left, prefix + (end ? "    " : "│   "), true);
        }
        return res;
    }

    public String toStringOneLine() {
        return (root == null ? "{}" : toStringOneLine(root));
    }

    public String toStringOneLine(Node ptr) {
        if (ptr == null) {
            return "{}";
        }
        return "{" + ptr.toString() + toStringOneLine(ptr.left) + toStringOneLine(ptr.right) + "}";
    }

    public MaxSkewHeap()
    {
        this.root = null;
    }

    public MaxSkewHeap(String input)
    {
        if(input.equals("{}"))
        {
            //Empty Heap
            this.root = null;
        }
        else{
            //get rid of the outer brackets
            String result = input.substring(1, input.length() - 1);
            int leftChildBracket = result.indexOf('{');
            char[] array = result.toCharArray();

            int rootNum = extractData(array);
            //update array length

            array = updateLength(array);

            this.root = new Node(rootNum);
            constructorHelper(array,root);


        }
    }

    public void insert(int data)
    {
        //check if duplicate
        if(search(data) == null)
        {
            root = merge(root, new Node(data));  // Merge the new node with the existing heap
        }
    }

    public void remove(int data)
    {
        if(root == null || search(data) == null)
        {
            return;
        }

        root = remove(root,data);
    }

    public Node search(int value) {
        if(root == null)
        {
            return null;
        }

        if(root.data == value)
        {
            return root;
        }

        return search(root,value);
    }

    public String searchPath(int value)
    {
        if(root == null)
        {
            return "";
        }

        String path = root.toString();

        if(root.data == value)
        {
            return "[" + root.toString() + "]";
        }
        return searchPath(root,value,path);
    }

    public boolean isLeftist() {
        if(root == null)
        {
            return true;
        }
        return isLeftistHeap(root);
    }

    public boolean isRightist() {
        if(root == null)
        {
            return true;
        }
        return isRightistHeap(root);
    }

    private boolean isRightistHeap(Node node) {
        if (node == null) {
            return true;
        }

        // Check if the right child is smaller or equal to the parent
        if (!(npl(node.left) <= npl(node.right))) {
            return false;
        }

        // Recursively check if both subtrees are rightist heaps
        return isRightistHeap(node.left) && isRightistHeap(node.right);
    }

    private boolean isLeftistHeap(Node node) {
        if (node == null) {
            return true;
        }


        if (!(npl(node.left) >= npl(node.right))) {
            return false;
        }

        // Recursively check if both subtrees are leftist heaps
        return isLeftistHeap(node.left) && isLeftistHeap(node.right);
    }

    private char[] constructorHelper(char [] input, Node parent)
    {


        //{38{40{}{}}{70{}{}}} {88{99{}{}}{100{}{}}}

        //38{40{}{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //{40{}{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //40{}{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //{}{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //}{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //{}}{70{}{}}} {88{99{}{}}{100{}{}}}
        //}{70{}{}}} {88{99{}{}}{100{}{}}}
        //{70{}{}}} {88{99{}{}}{100{}{}}}
        //70{}{}}} {88{99{}{}}{100{}{}}}
        // }
        if((input.length == 1 && input[0] == '}') || input.length == 0)
        {
            return null;
        }



        // Find the left child
        input = removeBracket(input);
        if(input[0] == '}')
        {
            //null child
            input = removeBracket(input);

        }
        else{
            int leftChild = extractData(input);
            input = updateLength(input);
            Node left = new Node(leftChild);
            parent.left = left;
            left.parent = parent;

            input = constructorHelper(input,left);
        }

        //move to right child
        input = removeBracket(input);

        if(input[0] == '}')
        {
            //null child
            input = removeBracket(input);
            input = removeBracket(input);

            return input;
        }
        else{
            int rightChild = extractData(input);
            input = updateLength(input);
            Node right = new Node(rightChild);
            parent.right = right;
            right.parent = parent;

            input = constructorHelper(input,right);
            input = removeBracket(input);
            return input;
        }
    }

    private int indexOf(char [] input,char ch)
    {
        for(int i = 0; i < input.length; i++){
            if(input[i] == ch)
            {
                return i;
            }
        }
        return -1;
    }

    private char[] removeBracket(char [] input)
    {
        if(input.length > 0)
        {
            char [] temp = new char[input.length-1];
            for(int i = 1; i < input.length;i++)
            {
                temp[i-1] = input[i];
            }

            input = new char[temp.length];

            for(int i = 0; i < temp.length;i++)
            {
                input[i] = temp[i];
            }
            return input;
        }
        return input;
    }

    private int extractData(char [] input)
    {
        if(input.length > 0)
        {
            char [] dataArray = new char[input.length];
            int data = 0;
            int index = 0;
            for(int i = 0; i < input.length;i++)
            {
                if(input[i] == '{')
                {
                    index = i;
                    break;
                }
                dataArray[i] = input[i];
            }
            String dataString = "";
            for(int i = 0; i < dataArray.length;i++)
            {
                if(dataArray[i] != '\u0000')
                {
                    dataString+=dataArray[i];
                }
            }
            data = Integer.parseInt(dataString);

            for (int i = 0; i < input.length-index; i++) {

                input[i] = input[i + index];

            }
            for(int i = input.length-index; i < input.length;i++)
            {
                input[i] = '\u0000';
            }

            return data;
        }
        return -1;
    }

    private int getLength(char[] array)
    {
        int length = 0;
        for(int i = 0; i < array.length;i++)
        {
            if(array[i] != '\u0000')
            {
                length++;
            }
        }
        return length;
    }

    private char[] updateLength(char[] array)
    {
        int length = getLength(array);
        char[] temp = new char[length];
        for(int i = 0; i < length; i++)
        {
            temp[i] = array[i];
        }

        array = new char[length];
        for(int i = 0; i < length; i++)
        {
            array[i] = temp[i];
        }
        return array;
    }

    private Node merge(Node h1, Node h2)
    {
        // If one of the heaps is empty
        if (h1 == null)
        {
            return h2;
        }
        if (h2 == null)
        {
            return h1;
        }

        // Make sure that h1 has smaller
        // key.
        if (h1.data < h2.data)
        {
            Node temp = h1;
            h1 = h2;
            h2 = temp;
        }

        // Swap h1.left and h1.right
        Node temp = h1.left;
        h1.left = h1.right;
        h1.right = temp;

        // Merge h2 and h1.left and make
        // merged tree as left of h1.
        h1.left = merge(h2, h1.left);

        return h1;
    }

    private Node search(Node current,int data)
    {
        //Base Case
        if(current == null || current.data < data)
        {
            return null;
        }

        if(current.data == data)
        {
            return current;
        }

        Node right = search(current.right,data);
        if(right == null)
        {
            return search(current.left,data);
        }
        else{
            return right;
        }
    }

    private String searchPath(Node current,int data,String path)
    {
        if(current != root && current != null && current.data != data)
        {
            path += "->" + current.toString();
        }

        //Base Case
        if(current == null || current.data < data)
        {
            return path;
        }

        if(current.data == data)
        {
            path += "->[" + current.toString() +"]";
            return path;
        }



        String right = searchPath(current.right,data,path);

        if(right.charAt(right.length()-1) != ']')
        {

            return searchPath(current.left,data,right);
        }
        else{
            return right;
        }
    }

    private Node remove(Node current, int data)
    {
        if(current == null)
        {
            return null;
        }

        if(current.data == data)
        {
            return merge(current.left,current.right);
        }

        current.left = remove(current.left,data);
        current.right = remove(current.right,data);

        return current;
    }

    private int npl(Node current)
    {
        if(current != null)
        {
            if(current.left == null || current.right == null)
            {
                return 0;
            }
            return 1 + npl(current.right) + npl(current.left);
        }
        return -1;
    }


}
