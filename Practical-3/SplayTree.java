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
    }

    public Node access(int studentNumber) {
        return null;
    }

    public Node access(int studentNumber, Integer mark) {
        return null;
    }

    public Node remove(int studentNumber) {
        return null;
    }

    public String sortByStudentNumber() {
        return "";
    }

    public String sortByMark() {
        return "";
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
}
