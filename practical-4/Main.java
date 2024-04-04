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

        validTest();
        deleteTest();
        insertTest();
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

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(34);
        assertEquals(myTree.getRoot().data,34);


        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(502);
        assertEquals(myTree.getRoot().data,502);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(775);
        assertEquals(myTree.getRoot().data,775);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(90);
        assertEquals(myTree.getRoot().data,90);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(-1);
        assertEquals(myTree.getRoot().data,-1);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(0);
        assertEquals(myTree.getRoot().data,0);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(55);
        assertEquals(myTree.getRoot().data,55);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(734);
        assertEquals(myTree.getRoot().data,734);

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(596);
        assertEquals(myTree.getRoot().data,596);
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

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(248);
        myTree.bottomUpInsert(944);
        myTree.bottomUpInsert(618);
        myTree.bottomUpInsert(138);
        myTree.bottomUpInsert(242);
        myTree.bottomUpInsert(606);
        myTree.bottomUpInsert(283);
        myTree.bottomUpInsert(19);
        assertEquals(myTree.toString(),"│   ┌── 944\n" +
                "└── 618\n" +
                "    │       ┌── (606)\n" +
                "    │   ┌── 283\n" +
                "    │   │   └── (248)\n" +
                "    └── (242)\n" +
                "        └── 138\n" +
                "            └── (19)\n");
        myTree.bottomUpInsert(605);
        assertEquals(myTree.toString(),"│       ┌── 944\n" +
                "│   ┌── (618)\n" +
                "│   │   └── 606\n" +
                "│   │       └── (605)\n" +
                "└── 283\n" +
                "    │   ┌── 248\n" +
                "    └── (242)\n" +
                "        └── 138\n" +
                "            └── (19)\n");
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

        myTree.bottomUpInsert(475);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(789);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(143);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(596);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(597);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(447);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(33);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.bottomUpInsert(68);
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
        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.topDownDelete(782);
        assertEquals(myTree.toString(),"└── 950\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.topDownDelete(950);
        assertEquals(myTree.toString(),"└── 782\n");

        //{913{(782){}{}}{(950){}{}}}
        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.topDownDelete(913);
        assertEquals(myTree.toString(),"└── 950\n" + "    └── (782)\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.topDownDelete(950);
        assertEquals(myTree.toString(),"└── 913\n" + "    └── (782)\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.topDownDelete(782);
        assertEquals(myTree.toString(),"│   ┌── (950)\n" + "└── 913\n");

        //{913{529{(410){}{}}{(782){}{}}}{950{}{}}}
        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(529);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "└── 913\n" + "    └── 782\n" + "        └── (410)\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(410);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "└── 913\n" + "    │   ┌── (782)\n" + "    └── 529\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(913);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "│   │   └── (782)\n" + "└── 529\n" + "    └── 410\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(529);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "└── 913\n" + "    └── 782\n" + "        └── (410)\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(410);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "└── 913\n" + "    │   ┌── (782)\n" + "    └── 529\n");

        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(782);
        myTree.bottomUpInsert(950);
        myTree.bottomUpInsert(913);
        myTree.bottomUpInsert(529);
        myTree.bottomUpInsert(410);
        myTree.topDownDelete(913);
        assertEquals(myTree.toString(),"│   ┌── 950\n" + "│   │   └── (782)\n" + "└── 529\n" + "    └── 410\n");


        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(498);
        myTree.bottomUpInsert(40);
        myTree.bottomUpInsert(320);
        myTree.topDownDelete(320);
        assertEquals(myTree.toString(),"└── 498\n" + "    └── (40)\n");

        myTree.bottomUpInsert(189);
        myTree.bottomUpInsert(31);
        myTree.bottomUpInsert(544);
        myTree.bottomUpInsert(154);
        myTree.bottomUpInsert(175);
        myTree.bottomUpInsert(704);

        myTree.topDownDelete(189);
        assertEquals(myTree.toString(),"│           ┌── (704)\n" + "│       ┌── 544\n" + "│   ┌── (498)\n" + "│   │   │   ┌── (175)\n" + "│   │   └── 154\n" + "└── 40\n" + "    └── 31\n");
        myTree.topDownDelete(31);
        assertEquals(myTree.toString(),"│       ┌── (704)\n" +
                "│   ┌── 544\n" +
                "└── 498\n" +
                "    │   ┌── 175\n" +
                "    └── (154)\n" +
                "        └── 40\n");
        myTree.topDownDelete(544);
        assertEquals(myTree.toString(),"│       ┌── 704\n" +
                "│   ┌── (498)\n" +
                "│   │   └── 175\n" +
                "└── 154\n" +
                "    └── 40\n");
        myTree.topDownDelete(154);
        assertEquals(myTree.toString(),"│       ┌── (704)\n" +
                "│   ┌── 498\n" +
                "└── 175\n" +
                "    └── 40\n");
        myTree.topDownDelete(175);
        assertEquals(myTree.toString(),"│   ┌── 704\n" +
                "└── 498\n" +
                "    └── 40\n");
        myTree.topDownDelete(704);
        assertEquals(myTree.toString(),"└── 498\n" + "    └── (40)\n");
        myTree.topDownDelete(498);
        assertEquals(myTree.toString(),"└── 40\n");
        myTree.topDownDelete(40);
        assertEquals(myTree.toString(),"Empty tree");


        myTree = new RedBlackTree<>();
        myTree.bottomUpInsert(394);
        myTree.bottomUpInsert(613);
        myTree.bottomUpInsert(581);
        myTree.bottomUpInsert(696);
        myTree.bottomUpInsert(833);
        myTree.bottomUpInsert(348);

        myTree.bottomUpInsert(444);
        myTree.bottomUpInsert(60);
        myTree.bottomUpInsert(279);
        myTree.bottomUpInsert(638);
        myTree.bottomUpInsert(249);
        myTree.bottomUpInsert(334);

        myTree.bottomUpInsert(820);
        myTree.bottomUpInsert(831);
        myTree.bottomUpInsert(374);
        myTree.bottomUpInsert(816);
        myTree.bottomUpInsert(254);
        myTree.bottomUpInsert(188);

        myTree.topDownDelete(394);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(613);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(581);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(696);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(833);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(348);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.topDownDelete(444);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(60);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(279);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(638);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(249);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(334);
        assertEquals(myTree.isValidRedBlackTree(),true);

        myTree.topDownDelete(820);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(831);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(374);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(816);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(254);
        assertEquals(myTree.isValidRedBlackTree(),true);
        myTree.topDownDelete(188);
        assertEquals(myTree.isValidRedBlackTree(),true);


        endSuite("Delete Test");
    }
}
