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

    public static void assertEquals(Integer[] actual, Integer[] expected)
    {
        TESTS_RUN++;
        boolean isEqual = false;
        if(actual.length == expected.length)
        {
            isEqual = true;
            for(int i = 0; i < actual.length; i++)
            {
                if(actual[i] != expected[i])
                {
                    isEqual = false;
                    break;
                }
            }
        }

        String actualArray = "[";
        for(Integer num:actual)
        {
            actualArray += num+",";
        }
        actualArray += "]";

        String expectedArray = "[";
        for(Integer num:expected)
        {
            expectedArray += num+",";
        }
        expectedArray += "]";

        if(isEqual){
            TESTS_PASSES++;
            System.out.println(ANSI_GREEN + "Test "+ TESTS_RUN +" Passed " + ANSI_RESET);
        }
        else{
            System.out.println(ANSI_RED + "Test "+ TESTS_RUN +" Failed: Expected " + expectedArray+ " but got "+ actualArray +  ANSI_RESET);
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
    public static void main(String[] args)
    {
        startSuite("Constructor Test");
        MaxSkewHeap heap = new MaxSkewHeap("{89{1{}{}}{72{66{}{}}{45{38{}{}}{}}}}");
        assertEquals(heap.toString(),"│       ┌── 45\n" +
                "│       │   └── 38\n" +
                "│   ┌── 72\n" +
                "│   │   └── 66\n" +
                "└── 89\n" +
                "    └── 1\n");

        heap = new MaxSkewHeap("{100{89{72{66{12{}{}}{}}{45{29{}{}}{38{}{}}}}{80{1{}{}}{}}}{67{33{}{}}{54{}{}}}}");
        assertEquals(heap.toString(),"│       ┌── 54\n" +
                "│   ┌── 67\n" +
                "│   │   └── 33\n" +
                "└── 100\n" +
                "    │   ┌── 80\n" +
                "    │   │   └── 1\n" +
                "    └── 89\n" +
                "        │       ┌── 38\n" +
                "        │   ┌── 45\n" +
                "        │   │   └── 29\n" +
                "        └── 72\n" +
                "            └── 66\n" +
                "                └── 12\n");

        heap = new MaxSkewHeap("{12{10{8{6{4{2{}{}}{}}{}}{}}{}}{9{7{5{3{1{}{}}{}}{}}{}}{}}}");
        assertEquals(heap.toString(),"│   ┌── 9\n" +
                "│   │   └── 7\n" +
                "│   │       └── 5\n" +
                "│   │           └── 3\n" +
                "│   │               └── 1\n" +
                "└── 12\n" +
                "    └── 10\n" +
                "        └── 8\n" +
                "            └── 6\n" +
                "                └── 4\n" +
                "                    └── 2\n");

        heap = new MaxSkewHeap("{999{785{678{265{}{}}{210{145{123{}{}}{}}{}}}{723{456{212{102{}{}}{}}{233{204{101{100{}{}}{}}{}}{}}}{}}}{778{475{}{}}{}}}");
        assertEquals(heap.toString(),"│   ┌── 778\n" +
                "│   │   └── 475\n" +
                "└── 999\n" +
                "    │   ┌── 723\n" +
                "    │   │   │   ┌── 233\n" +
                "    │   │   │   │   └── 204\n" +
                "    │   │   │   │       └── 101\n" +
                "    │   │   │   │           └── 100\n" +
                "    │   │   └── 456\n" +
                "    │   │       └── 212\n" +
                "    │   │           └── 102\n" +
                "    └── 785\n" +
                "        │   ┌── 210\n" +
                "        │   │   └── 145\n" +
                "        │   │       └── 123\n" +
                "        └── 678\n" +
                "            └── 265\n");

        endSuite("Constructor Test");
        endAll();
    }
}