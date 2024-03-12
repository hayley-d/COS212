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

    public static void assertEquals(Node actual, Integer expectedMark,int expectedNum)
    {
        TESTS_RUN++;
        if(actual==null)
        {
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expectedMark+" and "+ expectedNum + " but got null" +  ANSI_RESET);
            return;
        }
        else if(actual.mark == expectedMark && actual.studentNumber==expectedNum){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expectedMark+" and "+ expectedNum + " but got " + actual +  ANSI_RESET);
            return;
        }
    }

    public static void assertEquals(Node actual)
    {
        TESTS_RUN++;
        if(actual==null)
        {
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null but got " + actual +  ANSI_RESET);
            return;
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
        constructorTest();



        endAll();
    }

    public static void constructorTest(){
        startSuite("Constructor Test");
        //Empty Tree
        SplayTree tree = new SplayTree("Empty Tree");
        assertEquals(tree.root);
        //Single root
        String input = "{[u10:50%]{}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        input = "{[u10:50%]{[u5:40%]{}{}}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);

        input = "{[u10:50%]{[u5:40%]{}{}}{[u15:60%]{}{}}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);
        assertEquals(tree.root.right,60,15);
        endSuite("Constructor Test");
    }

    public static void accessTest(){
        startSuite("Access Test");
        //Empty Tree
        SplayTree tree = new SplayTree("Empty Tree");
        assertEquals(tree.root);
        //Single root
        String input = "{[u10:50%]{}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        input = "{[u10:50%]{[u5:40%]{}{}}{}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);

        input = "{[u10:50%]{[u5:40%]{}{}}{[u15:60%]{}{}}}";
        tree = new SplayTree(input);
        assertEquals(tree.root,50,10);
        assertEquals(tree.root.left,40,5);
        assertEquals(tree.root.right,60,15);
        endSuite("Access Test");
    }
}
