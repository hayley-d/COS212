public class SplayTree {
    public Node root;
    /*
     * The functions below this line was given
     */

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
        return (root == null ? "Empty Tree" : "{" + toStringOneLine(root) + "}");
    }

    public String toStringOneLine(Node node) {
        return node.toString()
                + (node.left == null ? "{}" : "{" + toStringOneLine(node.left) + "}")
                + (node.right == null ? "{}" : "{" + toStringOneLine(node.right) + "}");
    }

    public SplayTree() {
        root = null;
    }

    /*
     * The functions above this line was given
     */

    public SplayTree(String input) {
        if(input.equals("Empty Tree")){
            this.root = null;
            return;
        }
        input = input.substring(1,input.length()-1);
        char[] myString = input.toCharArray();//for recursive purposes
        root = recursiveCopy(myString);
        recursiveAddParent(root);
    }

    public Node access(int studentNumber) {
        return access(studentNumber,null);
    }

    public Node access(int studentNumber, Integer mark) {
        Node target = recursiveContains(root,studentNumber);

        if(target==null)
        {
            if(root == null)
            {
                root = new Node(studentNumber,mark);
                return root;
            }

            Node newNode = new Node(studentNumber,mark);
             target = recursiveInsert(root,newNode);
             splayTarget(target);
            /*root = splay(root,studentNumber);*/

            return root;
        }
        else{
            /*root = splay(root,studentNumber);*/
            splayTarget(target);
            if(mark!=null)
            {
                root.mark = mark;
            }
            return root;
        }
    }

    public Node remove(int studentNumber) {
        if(root == null)
        {
            return null;
        }
        Node deleteNode = access(studentNumber);
        Node leftRoot = root.left;
        Node rightRoot = root.right;
        if(leftRoot == null && rightRoot !=null)
        {
            this.root = rightRoot;
            deleteNode.left = null;
            deleteNode.right = null;
            return deleteNode;
        }
        else if(leftRoot != null && rightRoot == null)
        {
            this.root = leftRoot;
            deleteNode.left = null;
            deleteNode.right = null;
            return deleteNode;
        }
        else if(leftRoot !=null && rightRoot !=null){
            this.root = leftRoot;
            Node maxLeft = maxLeft(leftRoot);
            if(maxLeft!=null)
            {
                access(maxLeft.studentNumber);
            }
            root.right = rightRoot;
        }
        else{
            root = null;
        }
        deleteNode.left = null;
        deleteNode.right = null;
        return deleteNode;
    }

    public String sortByStudentNumber() {
        if(root == null)
        {
            return "Empty Tree";
        }
        return sortStuNum(root);
    }

    public String sortByMark() {
        if(root == null)
        {
            return "Empty Tree";
        }
        int numNodes = numNodes(root);
        Node[] nodes = new Node[numNodes];
        sortMarks(root,nodes,0);

        selectionSort(nodes);

        String str ="";
        for(int i = 0; i < nodes.length;i++)
        {
            str+=nodes[i];
        }
        return str;
    }

    private Integer[] extractNodeInfo(char[] arr)
    {
        if(arr == null || arr.length<=0)
        {
            return null;
        }
        String nodeInfo = "";
        boolean copy = true;
        for(int i = 0; i < arr.length;i++)
        {
            if(copy)
            {
                nodeInfo+=arr[i];
            }
            if(arr[i] == ']')
            {
                copy = false;
                break;
            }
        }
        //remove node info from array
        removeNodeInfo(arr);
        Integer[] integerArr = new Integer[2];// 0=student null 1==mark
        //get the student number
        int start = nodeInfo.indexOf("u");

        int end = nodeInfo.indexOf(":");

        String studentNum = nodeInfo.substring(start+1,end);
        int stuNum = Integer.parseInt(studentNum);
        //get the mark
        start = nodeInfo.indexOf(":");
        end = nodeInfo.indexOf("%");
        String strMark = nodeInfo.substring(start+1,end);
        Integer mark;
        if(strMark.equals("null"))
        {
            mark = null;
        }
        else{
            mark = Integer.parseInt(strMark);
        }
        integerArr[0] = stuNum;
        integerArr[1] = mark;
        //add values to the array
        return integerArr;
    }

    private void removeNodeInfo(char[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        //assuming first char == [

        int end = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ']') {
                end = i;
                break;
            }
        }

        String newStr = null;
        if (end != -1) {
            int newIndex = 0;
            for (int i = end + 1; i < arr.length; i++) {
                arr[newIndex++] = arr[i];
            }
            // Fill the remaining elements with null characters
            for (int i = newIndex; i < arr.length; i++) {
                arr[i] = 'h';
            }
        }
    }

    private void removeBracket(char[] arr)
    {
        if (arr == null || arr.length == 0) {
            return;
        }

        //assuming first char == } or {
        String newStr = null;
        int newIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[newIndex++] = arr[i];
        }
        // Fill the remaining elements with null characters
        /*for (int i = newIndex+1; i < arr.length; i++) {
            arr[i] = 'h';
        }*/
    }

    private Node recursiveCopy(char[] arr)
    {
        if(arr == null || arr.length == 0)
        {
            return null;
        }

        //Step 1: extract node info
        Integer[] values = extractNodeInfo(arr);
        Node newNode = new Node(values[0],values[1]);

        //Step 2 check if left child is null
        if(arr[0] == '{' && arr[1] == '}')
        {
            //empty child
            newNode.left = null;
            //remove both brackets
            removeBracket(arr);
            removeBracket(arr);
        }
        else{
            //has info
            //remove open bracket
            removeBracket(arr);
            newNode.left = recursiveCopy(arr);
        }

        //check right child is null
        if(arr[0] == '{' && arr[1] == '}')
        {
            //empty child
            newNode.right = null;
            //remove both brackets
            removeBracket(arr);
            removeBracket(arr);
        }
        else{
            //has info
            //remove open bracket
            removeBracket(arr);
            newNode.right = recursiveCopy(arr);
        }

        //Remove outerBracket
        removeBracket(arr);
        //return
        return newNode;
    }

    private Node recursiveContains(Node current,int target)
    {
        if(current == null)
        {
            return null;
        }
        if(current.studentNumber == target)
        {
            return current;
        }
        else if(current.studentNumber > target)
        {
            //go left
            return recursiveContains(current.left,target);
        }
        else
        {
            //go left
            return recursiveContains(current.right,target);
        }
    }

    private Node recursiveInsert(Node current,Node newNode)
    {
        if(current==null)
        {
            return newNode;
        }
        if(current.studentNumber == newNode.studentNumber)
        {
            return current;
        }
        else if(current.studentNumber > newNode.studentNumber)
        {
            if(current.left == null)
            {
                current.left = newNode;
                newNode.parent = current;
                return newNode;
            }
            else{
               return recursiveInsert(current.left,newNode);
            }
        }
        else{
            if(current.right == null)
            {
                current.right = newNode;
                newNode.parent = current;
                return newNode;
            }
            else{
                return recursiveInsert(current.right,newNode);
            }
        }
    }

    private Node maxLeft(Node current)
    {
        if(current==null)
        {
            return null;
        }
        if(current.right == null)
        {
            return current;
        }
        return maxLeft(current.right);
    }

    private String sortStuNum(Node current){
        if(current == null)
        {
            return "";
        }
        return sortStuNum(current.left) + current.toString() + sortStuNum(current.right);
    }

    private int sortMarks(Node current,Node[] nodes,int index){
        if(current == null)
        {
            return index;
        }
        index = sortMarks(current.left, nodes, index);
        nodes[index++] = new Node(current.studentNumber,current.mark);
        index = sortMarks(current.right, nodes, index);

        return index;
    }

    private int numNodes(Node current){
        if(current == null)
        {
            return 0;
        }
        return numNodes(current.left) + 1 + numNodes(current.right);
    }

    private void selectionSort(Node[] arr){
        int n = arr.length;

        // Move null marks to the front
        int endOfNulls = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].mark == null) {
                Node temp = arr[endOfNulls];
                arr[endOfNulls] = arr[i];
                arr[i] = temp;
                endOfNulls++;
            }
        }

        // Sort null marks by student number
        for (int i = 0; i < endOfNulls - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < endOfNulls; j++) {
                if (arr[j].studentNumber < arr[minIndex].studentNumber) {
                    minIndex = j;
                }
            }
            Node temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        // Sort non-null marks by mark value
        for (int i = endOfNulls; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].mark != null && (arr[minIndex].mark == null || arr[j].mark < arr[minIndex].mark)) {
                    minIndex = j;
                }
                if(arr[j].mark == arr[minIndex].mark){
                    if(arr[j].studentNumber < arr[minIndex].studentNumber)
                    {
                        minIndex = j;
                    }
                }
            }
            Node temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

    }

    /*private void singleRotateRight(Node node)
    {
        Node t3 = node.right;
        Node a = node.left;
        Node t1 = a.left;
        Node t2 = a.right;

        if(t2!=null)
        {
            t2.parent = node;
        }

        node.left = t2;
        node.parent = a;
        a.right = node;

        if(node.parent == null)
        {
            this.root = a;
        }
        else if(node.isLeftChild())
        {
            node.parent.left = a;
        }
        else{
            node.parent.right = a;
        }

        a.parent = node.parent;
        a.left = t1;


    }*/


    private void splayTarget(Node node)
    {
        while(node.parent !=null)
        {

            if(node.parent.parent !=null)
            {
                //has grandparent
                if(node.studentNumber > node.parent.studentNumber && node.parent.studentNumber < node.parent.parent.studentNumber)
                {
                    rotateLeft(node.parent);
                    rotateRight(node.parent);



                }
                else if(node.studentNumber < node.parent.studentNumber && node.parent.studentNumber > node.parent.parent.studentNumber)
                {
                    rotateRight(node.parent);
                    rotateLeft(node.parent);

                }
                else if(node.studentNumber < node.parent.studentNumber && node.parent.studentNumber < node.parent.parent.studentNumber)
                {
                    rotateRight(node.parent.parent);
                    rotateRight(node.parent);

                }
                else if(node.studentNumber > node.parent.studentNumber && node.parent.studentNumber > node.parent.parent.studentNumber)
                {
                    rotateLeft(node.parent.parent);
                    rotateLeft(node.parent);
                }
            }
            else{
                //no grandparent
                if(node.parent.studentNumber > node.studentNumber)
                {
                    //right rotate
                    rotateRight(node.parent);
                }
                else{

                    rotateLeft(node.parent);
                }
            }

        }
        root = node;
    }

    private Node rotateLeft(Node parent)
    {

        Node child = parent.right;
        parent.right = child.left;
        if(child.left!=null)
        {
            child.left.parent = parent;
        }
        child.left = parent;
        child.parent = parent.parent;
        if(parent.parent!=null)
        {
            if(parent.studentNumber > parent.parent.studentNumber)
            {
                //right child
                parent.parent.right = child;
            }
            else{
                parent.parent.left = child;
            }
        }

        parent.parent = child;
        return child;
    }

    //right rotate
    private Node rotateRight(Node parent)
    {
        Node child = parent.left;
        parent.left = child.right;
        if(child.right!=null)
        {
            child.right.parent = parent;
        }
        child.right = parent;

        if(parent.parent!=null)
        {
            if(parent.studentNumber > parent.parent.studentNumber)
            {
                //right child
                parent.parent.right = child;
            }
            else{
                parent.parent.left = child;
            }
        }
        child.parent = parent.parent;
        parent.parent = child;

        return child;
    }

    private void recursiveAddParent(Node current)
    {
        if(current == null)
        {
            return;
        }

        if(current.left !=null)
        {
            current.left.parent = current;
            recursiveAddParent(current.left);
        }

        if(current.right!=null)
        {
            current.right.parent = current;
            recursiveAddParent(current.right);
        }

    }


}
