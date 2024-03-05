
public class Main {
    //colours
    public static String ANSI_RED = "\u001b[31;1m";

    public static String ANSI_GREEN = "\u001b[32;1m";

    public static String ANSI_RESET = "\u001b[0m";

    public static int SUITES_RUN = 0;
    public static int SUITES_PASSED = 0;
    public static int TESTS_RUN = 0;
    public static int TESTS_PASSES = 0;

    public static void startSuite(String name)
    {
        SUITES_RUN++;
        System.out.println("======================\n" + "Starting: " + name + "\n======================");
    }

    public static void endSuite(String name)
    {
        if(TESTS_RUN == TESTS_PASSES)
        {
            SUITES_PASSED++;
            System.out.println(ANSI_GREEN + "======================\n" + "Tests Passed " + "\n======================\n" + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "======================\n" + "Tests Failed: " + (TESTS_RUN-TESTS_PASSES) + "\n======================\n"+ ANSI_RESET);
        }
        TESTS_RUN = 0;
        TESTS_PASSES = 0;
    }

    public static void assertEquals(int actual, int expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(boolean actual, boolean expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(double actual, double expected)
    {
        TESTS_RUN++;
        if(actual == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(String actual, String expected)
    {
        TESTS_RUN++;
        if(actual.equals(expected)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(BinaryNode<Integer> actual, int expected)
    {
        TESTS_RUN++;
        if(actual.data == expected){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(BinaryNode<String> actual, String expected)
    {
        TESTS_RUN++;
        if(actual.data.equals(expected)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(BinaryNode<Integer> actual, BinaryNode<Integer> expected)
    {
        TESTS_RUN++;
        if(actual == null && expected == null)
        {
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
            return;
        }
        if(actual != null && expected == null)
        {
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null but got "+ actual.data +  ANSI_RESET);
            return;
        }
        if(actual.data.equals(expected.data)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expected+ " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void endAll(){
        if(SUITES_PASSED == SUITES_RUN)
        {
            System.out.println(ANSI_GREEN + "======================\n" + "Congratulations All Suites Passed: " + SUITES_PASSED +"/" + SUITES_RUN + "\n======================\n" + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "======================\n" + "Some Suites Failed: " + SUITES_PASSED +"/" + SUITES_RUN + "\n======================\n" + ANSI_RESET);
        }
    }


    public static void main(String[] args){
        startSuite("Constructor Test");
        //Empty Tree
        BST<Integer> myBST = new BST<>();
        assertEquals(myBST.root,null);
        endSuite("Constructor Test");

        startSuite("Insert Test");
        //Empty insert
        myBST.insert(1);
        assertEquals(myBST.root,1);
        assertEquals(myBST.root.left,null);
        assertEquals(myBST.root.right,null);

        //Single element insert
        myBST.insert(2);
        assertEquals(myBST.root.right,2);
        assertEquals(myBST.root.left,null);

        //Smaller insert
        myBST.insert(0);
        assertEquals(myBST.root.right,2);
        assertEquals(myBST.root.left,0);

        //multiple insert
        myBST.insert(-1);
        assertEquals(myBST.root.left.left,-1);
        myBST.insert(10);
        assertEquals(myBST.root.right.right,10);
        myBST.insert(23);
        assertEquals(myBST.root.right.right.right,23);
        myBST.insert(20);
        assertEquals(myBST.root.right.right.right.left,20);

        //String
        BST<String> stringTree = new BST<>();
        stringTree.insert("h");
        assertEquals(stringTree.root,"h");
        stringTree.insert("a");
        assertEquals(stringTree.root.left,"a");
        stringTree.insert("i");
        assertEquals(stringTree.root.right,"i");
        endSuite("Insert Test");

        startSuite("Delete Test");
        //Reset tree
        myBST = new BST<Integer>();
        //Empty delete
        myBST.delete(3);
        assertEquals(myBST.root,null);//passed

        //Single element
        myBST.insert(2);
        //not found
        myBST.delete(3);
        assertEquals(myBST.root,2);//passed
        //Delete root
        myBST.delete(2);
        assertEquals(myBST.root,null);//passed

        //Two elements
        myBST = new BST<Integer>();
        myBST.insert(5);
        myBST.insert(2);

        //Delete not found
        myBST.delete(3);
        assertEquals(myBST.root,5);//4
        assertEquals(myBST.root.left,2);//5

        //Delete root
        myBST.delete(5);
        assertEquals(myBST.root,2);//6
        assertEquals(myBST.root.left,null);//7
        assertEquals(myBST.root.right,null);//8

        myBST = new BST<Integer>();
        myBST.insert(5);
        myBST.insert(2);

        //Delete left
        myBST.delete(2);
        assertEquals(myBST.root,5);//9
        assertEquals(myBST.root.left,null);//10
        assertEquals(myBST.root.right,null);//11

        myBST.insert(7);
        //Delete nothing
        myBST.delete(10);
        assertEquals(myBST.root,5);//12
        assertEquals(myBST.root.left,null);//13
        assertEquals(myBST.root.right,7);//14

        //Delete root
        myBST.delete(5);
        assertEquals(myBST.root,7);//15
        assertEquals(myBST.root.left,null);//16
        assertEquals(myBST.root.right,null);//17

        myBST = new BST<Integer>();
        myBST.insert(5);
        myBST.insert(7);
        //delete right
        myBST.delete(7);
        assertEquals(myBST.root,5);//18
        assertEquals(myBST.root.left,null);//19
        assertEquals(myBST.root.right,null);//20

        //Big delete
        myBST = new BST<Integer>();
        myBST.insert(10);
        myBST.insert(8);
        myBST.insert(9);
        myBST.insert(6);
        myBST.insert(4);
        myBST.insert(7);
        myBST.insert(15);
        myBST.insert(20);
        myBST.insert(12);
        myBST.insert(11);
        myBST.insert(13);
        myBST.insert(14);
        myBST.insert(16);
        myBST.insert(22);

        String myTree = myBST.toString();
        myBST.delete(765);
        assertEquals(myBST.toString(),myTree);//21
        myBST.delete(-2);
        assertEquals(myBST.toString(),myTree);//22
        assertEquals(myBST.contains(22),true);//23
        myBST.delete(22);
        assertEquals(myBST.contains(22),false);//24
        assertEquals(myBST.contains(16),true);
        myBST.delete(16);
        assertEquals(myBST.contains(16),false);
        assertEquals(myBST.contains(14),true);
        myBST.delete(14);
        assertEquals(myBST.contains(14),false);
        assertEquals(myBST.contains(13),true);
        myBST.delete(13);
        assertEquals(myBST.contains(13),false);
        assertEquals(myBST.contains(11),true);
        myBST.delete(11);
        assertEquals(myBST.contains(11),false);
        assertEquals(myBST.contains(12),true);
        myBST.delete(12);
        assertEquals(myBST.contains(12),false);
        assertEquals(myBST.contains(20),true);
        myBST.delete(20);
        assertEquals(myBST.contains(20),false);
        assertEquals(myBST.contains(15),true);
        myBST.delete(15);
        assertEquals(myBST.contains(15),false);
        assertEquals(myBST.contains(7),true);
        myBST.delete(7);
        assertEquals(myBST.contains(7),false);
        assertEquals(myBST.contains(4),true);
        myBST.delete(4);
        assertEquals(myBST.contains(4),false);
        assertEquals(myBST.contains(6),true);
        myBST.delete(6);
        assertEquals(myBST.contains(6),false);
        assertEquals(myBST.contains(9),true);
        myBST.delete(9);
        assertEquals(myBST.contains(9),false);
        assertEquals(myBST.contains(8),true);
        myBST.delete(8);
        assertEquals(myBST.contains(8),false);
        assertEquals(myBST.contains(10),true);
        myBST.delete(10);
        assertEquals(myBST.contains(10),false);
        assertEquals(myBST.root,null);


        endSuite("Delete Test");

        startSuite("Contains Test");
        //Empty Tree
        myBST = new BST<>();
        //No nodes test
        assertEquals(myBST.contains(4),false);//Test 1
        assertEquals(myBST.contains(8),false);//Test 2

        //Single element test
        myBST.insert(5);
        assertEquals(myBST.contains(4),false);//Test 3
        assertEquals(myBST.contains(5),true);//Test 4
        assertEquals(myBST.contains(6),false);//Test 5

        //Two elements test
        myBST.insert(2);
        assertEquals(myBST.contains(4),false);//Test 6
        assertEquals(myBST.contains(5),true);//Test 7
        assertEquals(myBST.contains(6),false);//Test 8
        assertEquals(myBST.contains(2),true);//Test 9

        //Three element test
        myBST.insert(20);
        assertEquals(myBST.contains(4),false);//Test 10
        assertEquals(myBST.contains(5),true);//Test 11
        assertEquals(myBST.contains(6),false);//Test 12
        assertEquals(myBST.contains(2),true);//Test 13
        assertEquals(myBST.contains(20),true);//Test 14
        
        //Big tree
        myBST = new BST<Integer>();
        myBST.insert(10);
        myBST.insert(8);
        myBST.insert(9);
        myBST.insert(6);
        myBST.insert(4);
        myBST.insert(7);
        myBST.insert(15);
        myBST.insert(20);
        myBST.insert(12);
        myBST.insert(11);
        myBST.insert(13);
        myBST.insert(14);
        myBST.insert(16);
        myBST.insert(22);
        assertEquals(myBST.contains(22),true);//Test 15
        assertEquals(myBST.contains(16),true);//Test 16
        assertEquals(myBST.contains(14),true);//Test 17
        assertEquals(myBST.contains(13),true);//Test 18
        assertEquals(myBST.contains(11),true);//Test 19
        assertEquals(myBST.contains(12),true);//Test 20
        assertEquals(myBST.contains(20),true);//Test 21
        assertEquals(myBST.contains(15),true);//Test 22
        assertEquals(myBST.contains(7),true);//Test 23
        assertEquals(myBST.contains(4),true);//Test 24
        assertEquals(myBST.contains(6),true);//Test 25
        assertEquals(myBST.contains(9),true);//Test 26
        assertEquals(myBST.contains(8),true);//Test 27
        assertEquals(myBST.contains(10),true);//Test 28
        assertEquals(myBST.contains(25),false);//Test 29
        assertEquals(myBST.contains(90),false);//Test 30
        assertEquals(myBST.contains(0),false);//Test 31
        endSuite("Contains Test");

        startSuite("Find Max Test");
        //Empty Tree
        myBST = new BST<>();
        assertEquals(myBST.findMax(),null);

        //Test Single element
        myBST.insert(10);
        assertEquals(myBST.findMax(),10);

        //Two elements
        myBST.insert(20);
        assertEquals(myBST.findMax(),20);

        //Three elements
        myBST.insert(1);
        assertEquals(myBST.findMax(),20);

        //Many elemments
        myBST = new BST<Integer>();
        myBST.insert(10);
        myBST.insert(8);
        myBST.insert(9);
        myBST.insert(6);
        myBST.insert(4);
        myBST.insert(7);
        myBST.insert(15);
        myBST.insert(20);
        myBST.insert(12);
        myBST.insert(11);
        myBST.insert(13);
        myBST.insert(14);
        myBST.insert(16);
        myBST.insert(22);
        assertEquals(myBST.findMax(),22);
        endSuite("Find Max Test");

        startSuite("Find Min Test");
        //Empty Tree
        myBST = new BST<>();
        assertEquals(myBST.findMin(),null);

        //Test Single element
        myBST.insert(10);
        assertEquals(myBST.findMin(),10);

        //Two elements
        myBST.insert(20);
        assertEquals(myBST.findMin(),10);

        //Three elements
        myBST.insert(1);
        assertEquals(myBST.findMin(),1);

        //Many elemments
        myBST = new BST<Integer>();
        myBST.insert(10);
        myBST.insert(8);
        myBST.insert(9);
        myBST.insert(6);
        myBST.insert(4);
        myBST.insert(7);
        myBST.insert(15);
        myBST.insert(20);
        myBST.insert(12);
        myBST.insert(11);
        myBST.insert(13);
        myBST.insert(14);
        myBST.insert(16);
        myBST.insert(22);
        assertEquals(myBST.findMin(),4);
        endSuite("Find Min Test");

        startSuite("Find Node Test");
        //Empty Tree
        myBST = new BST<>();
        assertEquals(myBST.getNode(5),null);

        //Single element
        myBST.insert(22);
        assertEquals(myBST.getNode(5),null);
        assertEquals(myBST.getNode(22),22);

        //Two elements
        myBST.insert(2);
        assertEquals(myBST.getNode(5),null);
        assertEquals(myBST.getNode(22),22);
        assertEquals(myBST.getNode(2),2);

        //Three elements
        myBST.insert(25);
        assertEquals(myBST.getNode(5),null);
        assertEquals(myBST.getNode(22),22);
        assertEquals(myBST.getNode(2),2);
        assertEquals(myBST.getNode(25),25);

        //Many elements
        //Many elemments
        myBST = new BST<Integer>();
        myBST.insert(10);
        myBST.insert(8);
        myBST.insert(9);
        myBST.insert(6);
        myBST.insert(4);
        myBST.insert(7);
        myBST.insert(15);
        myBST.insert(20);
        myBST.insert(12);
        myBST.insert(11);
        myBST.insert(13);
        myBST.insert(14);
        myBST.insert(16);
        myBST.insert(22);
        assertEquals(myBST.getNode(5),null);
        assertEquals(myBST.getNode(96),null);
        assertEquals(myBST.getNode(-1),null);
        assertEquals(myBST.getNode(23),null);
        assertEquals(myBST.getNode(22),22);
        assertEquals(myBST.getNode(16),16);
        assertEquals(myBST.getNode(14),14);
        assertEquals(myBST.getNode(13),13);
        assertEquals(myBST.getNode(11),11);
        assertEquals(myBST.getNode(12),12);
        assertEquals(myBST.getNode(20),20);
        assertEquals(myBST.getNode(15),15);
        assertEquals(myBST.getNode(7),7);
        assertEquals(myBST.getNode(4),4);
        assertEquals(myBST.getNode(6),6);
        assertEquals(myBST.getNode(9),9);
        assertEquals(myBST.getNode(8),8);
        assertEquals(myBST.getNode(10),10);
        endSuite("Find Node Test");

        startSuite("Count Leaves Test");
        //Empty Tree
        myBST = new BST<>();
        assertEquals(myBST.getNumLeaves(),0);

        //Single node
        myBST.insert(10);
        assertEquals(myBST.getNumLeaves(),1);

        //left child of root
        myBST.insert(8);
        assertEquals(myBST.getNumLeaves(),1);

        //child of a child
        myBST.insert(9);
        assertEquals(myBST.getNumLeaves(),1);

        myBST.insert(6);
        assertEquals(myBST.getNumLeaves(),2);

        myBST.insert(4);
        assertEquals(myBST.getNumLeaves(),2);

        myBST.insert(7);
        assertEquals(myBST.getNumLeaves(),3);

        myBST.insert(15);
        assertEquals(myBST.getNumLeaves(),4);

        myBST.insert(20);
        assertEquals(myBST.getNumLeaves(),4);

        myBST.insert(12);
        assertEquals(myBST.getNumLeaves(),5);

        myBST.insert(11);
        assertEquals(myBST.getNumLeaves(),5);

        myBST.insert(13);
        assertEquals(myBST.getNumLeaves(),6);

        myBST.insert(14);
        assertEquals(myBST.getNumLeaves(),6);

        myBST.insert(16);
        assertEquals(myBST.getNumLeaves(),6);

        myBST.insert(22);
        assertEquals(myBST.getNumLeaves(),7);

        endSuite("Count Leaves Test");

        startSuite("Height Test");
        //Empty Tree
        myBST = new BST<>();
        assertEquals(myBST.getHeight(),0);

        //Single node
        myBST.insert(10);
        assertEquals(myBST.getHeight(),1);

        //left child of root
        myBST.insert(8);
        assertEquals(myBST.getHeight(),2);

        //child of a child
        myBST.insert(9);
        assertEquals(myBST.getHeight(),3);

        myBST.insert(6);
        assertEquals(myBST.getHeight(),3);

        myBST.insert(4);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(7);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(15);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(20);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(12);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(11);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(13);
        assertEquals(myBST.getHeight(),4);

        myBST.insert(14);
        assertEquals(myBST.getHeight(),5);

        myBST.insert(16);
        assertEquals(myBST.getHeight(),5);

        myBST.insert(22);
        assertEquals(myBST.getHeight(),5);
        endSuite("Height Test");

        startSuite("Path Test");
        //Empty tree
        myBST = new BST<Integer>();
        assertEquals(myBST.printSearchPath(5),"Null");

        //Single node
        myBST.insert(10);
        assertEquals(myBST.printSearchPath(5),"10 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");

        //Two nodes
        myBST.insert(8);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");

        //Three Nodes
        myBST.insert(9);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");

        myBST.insert(6);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");

        myBST.insert(4);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");

        myBST.insert(7);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");

        myBST.insert(15);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");

        myBST.insert(20);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");

        myBST.insert(12);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");

        myBST.insert(11);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");
        assertEquals(myBST.printSearchPath(11),"10 -> 15 -> 12 -> 11");

        myBST.insert(13);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");
        assertEquals(myBST.printSearchPath(11),"10 -> 15 -> 12 -> 11");
        assertEquals(myBST.printSearchPath(13),"10 -> 15 -> 12 -> 13");

        myBST.insert(14);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");
        assertEquals(myBST.printSearchPath(11),"10 -> 15 -> 12 -> 11");
        assertEquals(myBST.printSearchPath(13),"10 -> 15 -> 12 -> 13");
        assertEquals(myBST.printSearchPath(14),"10 -> 15 -> 12 -> 13 -> 14");

        myBST.insert(16);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");
        assertEquals(myBST.printSearchPath(11),"10 -> 15 -> 12 -> 11");
        assertEquals(myBST.printSearchPath(13),"10 -> 15 -> 12 -> 13");
        assertEquals(myBST.printSearchPath(14),"10 -> 15 -> 12 -> 13 -> 14");
        assertEquals(myBST.printSearchPath(16),"10 -> 15 -> 20 -> 16");

        myBST.insert(22);
        assertEquals(myBST.printSearchPath(5),"10 -> 8 -> 6 -> 4 -> Null");
        assertEquals(myBST.printSearchPath(10),"10");
        assertEquals(myBST.printSearchPath(8),"10 -> 8");
        assertEquals(myBST.printSearchPath(9),"10 -> 8 -> 9");
        assertEquals(myBST.printSearchPath(6),"10 -> 8 -> 6");
        assertEquals(myBST.printSearchPath(4),"10 -> 8 -> 6 -> 4");
        assertEquals(myBST.printSearchPath(7),"10 -> 8 -> 6 -> 7");
        assertEquals(myBST.printSearchPath(15),"10 -> 15");
        assertEquals(myBST.printSearchPath(20),"10 -> 15 -> 20");
        assertEquals(myBST.printSearchPath(12),"10 -> 15 -> 12");
        assertEquals(myBST.printSearchPath(11),"10 -> 15 -> 12 -> 11");
        assertEquals(myBST.printSearchPath(13),"10 -> 15 -> 12 -> 13");
        assertEquals(myBST.printSearchPath(14),"10 -> 15 -> 12 -> 13 -> 14");
        assertEquals(myBST.printSearchPath(16),"10 -> 15 -> 20 -> 16");
        assertEquals(myBST.printSearchPath(22),"10 -> 15 -> 20 -> 22");

        endSuite("Path Test");

        endAll();
    }
}
