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

    public static void assertEquals(RedBlackNode<Integer> actual)
    {
        TESTS_RUN++;
        if(actual == null){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(RedBlackNode<Integer> actual,RedBlackNode<Integer> expected )
    {
        TESTS_RUN++;
        if(actual.equals(expected)){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected null" + " but got "+ actual +  ANSI_RESET);
        }
    }

    public static void assertEquals(RedBlackNode<Integer> actual, Integer expected)
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
        rootTest();
        insertTest();
        validTest();
        deleteTest();

        endAll();
    }

    public static void constructorTest(){
        startSuite("Constructor Test");
        RedBlackTree<Integer> myTree = new RedBlackTree<>();
        assertEquals(myTree.SENTINEL.right);

        endSuite("Constructor Test");
    }

    public static void rootTest(){
        startSuite("Root Test");
        RedBlackTree<Integer> myTree = new RedBlackTree<>();
        assertEquals(myTree.getRoot(),myTree.NULL_NODE);
        myTree.bottomUpInsert(12);
        assertEquals(myTree.getRoot().data,12);
        endSuite("Root Test");
    }

    public static void insertTest(){
        startSuite("Insert Test");
        RedBlackTree<Integer> myTree = new RedBlackTree<>();
        //Insert root node
        myTree.bottomUpInsert(406);
        assertEquals(myTree.SENTINEL.right,406);
        assertEquals(myTree.toString(),"└── 406\n");
        assertEquals(myTree.SENTINEL.right.colour,0);

        //insert single node in tree
        myTree.bottomUpInsert(907);
        assertEquals(myTree.SENTINEL.right.right,907);
        assertEquals(myTree.toString(),"│   ┌── (907)\n" + "└── 406\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,1);

        myTree.bottomUpInsert(458);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│   ┌── (907)\n" + "└── 458\n" + "    └── (406)\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.left.colour,1);
        assertEquals(myTree.SENTINEL.right.left.data,406);

        myTree.bottomUpInsert(431);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│   ┌── 907\n" + "└── 458\n" + "    │   ┌── (431)\n" + "    └── 406\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);

        myTree.bottomUpInsert(980);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│       ┌── (980)\n" + "│   ┌── 907\n" + "└── 458\n" + "    │   ┌── (431)\n" + "    └── 406\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.data,980);
        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);

        myTree.bottomUpInsert(467);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│       ┌── (980)\n" + "│   ┌── 907\n" + "│   │   └── (467)\n" + "└── 458\n" + "    │   ┌── (431)\n" + "    └── 406\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.data,980);
        assertEquals(myTree.SENTINEL.right.right.left.colour,1);
        assertEquals(myTree.SENTINEL.right.right.left.data,467);

        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);

        myTree.bottomUpInsert(173);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│       ┌── (980)\n" + "│   ┌── 907\n" + "│   │   └── (467)\n" + "└── 458\n" + "    │   ┌── (431)\n" + "    └── 406\n" + "        └── (173)\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.data,980);
        assertEquals(myTree.SENTINEL.right.right.left.colour,1);
        assertEquals(myTree.SENTINEL.right.right.left.data,467);

        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.left.data,173);

        myTree.bottomUpInsert(994);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│           ┌── (994)\n" + "│       ┌── 980\n" + "│   ┌── (907)\n" +
                "│   │   └── 467\n" + "└── 458\n" + "    │   ┌── (431)\n" + "    └── 406\n" + "        └── (173)\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.right.data,980);
        assertEquals(myTree.SENTINEL.right.right.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.right.data,994);
        assertEquals(myTree.SENTINEL.right.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.right.left.data,467);

        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.left.data,173);

        myTree.bottomUpInsert(1000);
        assertEquals(myTree.SENTINEL.right,458);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── (907)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── (431)\n" +
                "    └── 406\n" +
                "        └── (173)\n");
        assertEquals(myTree.SENTINEL.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.data,907);
        assertEquals(myTree.SENTINEL.right.right.right.colour,0);
        assertEquals(myTree.SENTINEL.right.right.right.data,994);
        assertEquals(myTree.SENTINEL.right.right.right.left.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.left.data,980);
        assertEquals(myTree.SENTINEL.right.right.right.right.colour,1);
        assertEquals(myTree.SENTINEL.right.right.right.right.data,1000);
        assertEquals(myTree.SENTINEL.right.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.right.left.data,467);

        assertEquals(myTree.SENTINEL.right.left.colour,0);
        assertEquals(myTree.SENTINEL.right.left.data,406);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.right.data,431);
        assertEquals(myTree.SENTINEL.right.left.right.colour,1);
        assertEquals(myTree.SENTINEL.right.left.left.data,173);

        myTree.bottomUpInsert(1);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── (907)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── 431\n" +
                "    └── (406)\n" +
                "        └── 173\n" +
                "            └── (1)\n");
        myTree.bottomUpInsert(500);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── (907)\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── 431\n" +
                "    └── (406)\n" +
                "        └── 173\n" +
                "            └── (1)\n");
        myTree.bottomUpInsert(3);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── (907)\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── 431\n" +
                "    └── (406)\n" +
                "        │   ┌── (173)\n" +
                "        └── 3\n" +
                "            └── (1)\n");
        myTree.bottomUpInsert(44);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── 431\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            └── 1\n");
        myTree.bottomUpInsert(2);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │   ┌── 431\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");

        myTree.bottomUpInsert(433);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │       ┌── (433)\n" +
                "    │   ┌── 431\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");

        myTree.bottomUpInsert(430);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │       ┌── (433)\n" +
                "    │   ┌── 431\n" +
                "    │   │   └── (430)\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");

        myTree.bottomUpInsert(420);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │       ┌── 433\n" +
                "    │   ┌── (431)\n" +
                "    │   │   └── 430\n" +
                "    │   │       └── (420)\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");
        myTree.bottomUpInsert(420);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │       ┌── 433\n" +
                "    │   ┌── (431)\n" +
                "    │   │   └── 430\n" +
                "    │   │       └── (420)\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");
        myTree.bottomUpInsert(420);
        assertEquals(myTree.toString(),"│           ┌── (1000)\n" +
                "│       ┌── 994\n" +
                "│       │   └── (980)\n" +
                "│   ┌── 907\n" +
                "│   │   │   ┌── (500)\n" +
                "│   │   └── 467\n" +
                "└── 458\n" +
                "    │       ┌── 433\n" +
                "    │   ┌── (431)\n" +
                "    │   │   └── 430\n" +
                "    │   │       └── (420)\n" +
                "    └── 406\n" +
                "        │   ┌── 173\n" +
                "        │   │   └── (44)\n" +
                "        └── (3)\n" +
                "            │   ┌── (2)\n" +
                "            └── 1\n");
        endSuite("Insert Test");
    }

    public static void validTest(){
        startSuite("Validation Test");
        RedBlackTree<Integer> myTree = new RedBlackTree<>();
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(12);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(600);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(456);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(387);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(223);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(943);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(805);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(640);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(723);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(401);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(938);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(203);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(918);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(86);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(77);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(2);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(10);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(3);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(23);
        assertEquals(myTree.isValidRedBlackTree(),true);

        endSuite("Validation Test");
    }

    public static void deleteTest(){
        startSuite("Delete Test");
        RedBlackTree<Integer> myTree = new RedBlackTree<>();

        myTree.bottomUpInsert(782);
        myTree.topDownDelete(782);
        assertEquals(myTree.toString(),"Empty tree");

        //{782{}{(950){}{}}}
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.topDownDelete(782);
        assertEquals(myTree.getRoot().data,950);
        //assertEquals(myTree.toString(),"└── 950\n");
        assertEquals(myTree.getRoot().colour,0);

        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.topDownDelete(950);
        assertEquals(myTree.toString(),"└── 782\n");


        endSuite("Delete Test");
    }
}
