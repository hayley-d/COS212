
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

        myBST.delete(765);
        //come back when toString is implemented


        endSuite("Delete Test");

        endAll();
    }
}
