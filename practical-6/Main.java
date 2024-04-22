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
        Integer [] array = new Integer[]{85, 34, 99, 45, 72, 59, 92, 24, 18, 63, 77, 40, 56, 81, 30};
        MaxHeap<Integer> heap = new MaxHeap(array);
        assertEquals(heap.toString(),"|           ┌── 30\n" +
                "|       ┌── 85\n" +
                "|       │   └── 81\n" +
                "|   ┌── 92\n" +
                "|   │   |   ┌── 56\n" +
                "|   │   └── 59\n" +
                "|   │       └── 40\n" +
                "└── 99\n" +
                "    |       ┌── 34\n" +
                "    |   ┌── 72\n" +
                "    |   │   └── 63\n" +
                "    └── 77\n" +
                "        |   ┌── 18\n" +
                "        └── 45\n" +
                "            └── 24\n");

        array = new Integer[]{85};
        heap = new MaxHeap(array);
        assertEquals(heap.toString(),"└── 85\n");

        array = new Integer[]{17, 94, 22, 56, 83, 10, 42, 71, 29, 64, 5, 78, 36, 90, 13, 51, 25, 68, 8, 47, 19, 73, 31, 62, 2, 88, 40, 76, 15, 59};
        heap = new MaxHeap(array);
        assertEquals(heap.toString(),"|           ┌── 59\n" +
                "|           │   └── 13\n" +
                "|       ┌── 76\n" +
                "|       │   |   ┌── 15\n" +
                "|       │   └── 42\n" +
                "|       │       └── 22\n" +
                "|   ┌── 90\n" +
                "|   │   |       ┌── 10\n" +
                "|   │   |   ┌── 40\n" +
                "|   │   |   │   └── 36\n" +
                "|   │   └── 88\n" +
                "|   │       |   ┌── 2\n" +
                "|   │       └── 78\n" +
                "|   │           └── 62\n" +
                "└── 94\n" +
                "    |           ┌── 17\n" +
                "    |       ┌── 31\n" +
                "    |       │   └── 5\n" +
                "    |   ┌── 73\n" +
                "    |   │   |   ┌── 19\n" +
                "    |   │   └── 64\n" +
                "    |   │       └── 47\n" +
                "    └── 83\n" +
                "        |       ┌── 8\n" +
                "        |   ┌── 68\n" +
                "        |   │   └── 29\n" +
                "        └── 71\n" +
                "            |   ┌── 25\n" +
                "            └── 56\n" +
                "                └── 51\n");

        array = new Integer[]{85, 34, 99, 45, 72, 59, 92, 24, 18, 63, 77, 40, 56, 81, 30};
        MinHeap<Integer> heap2 = new MinHeap(array);
        assertEquals(heap2.toString(),"|           ┌── 92\n" +
                "|       ┌── 81\n" +
                "|       │   └── 99\n" +
                "|   ┌── 30\n" +
                "|   │   |   ┌── 56\n" +
                "|   │   └── 40\n" +
                "|   │       └── 59\n" +
                "└── 18\n" +
                "    |       ┌── 77\n" +
                "    |   ┌── 63\n" +
                "    |   │   └── 72\n" +
                "    └── 24\n" +
                "        |   ┌── 45\n" +
                "        └── 34\n" +
                "            └── 85\n");

        array = new Integer[]{85};
        heap2 = new MinHeap(array);
        assertEquals(heap2.toString(),"└── 85\n");

        array = new Integer[]{17, 94, 22, 56, 83, 10, 42, 71, 29, 64, 5, 78, 36, 90, 13, 51, 25, 68, 8, 47, 19, 73, 31, 62, 2, 88, 40, 76, 15, 59};
        heap2 = new MinHeap(array);
        assertEquals(heap2.toString(),"|           ┌── 42\n" +
                "|           │   └── 59\n" +
                "|       ┌── 13\n" +
                "|       │   |   ┌── 90\n" +
                "|       │   └── 15\n" +
                "|       │       └── 76\n" +
                "|   ┌── 10\n" +
                "|   │   |       ┌── 40\n" +
                "|   │   |   ┌── 36\n" +
                "|   │   |   │   └── 88\n" +
                "|   │   └── 17\n" +
                "|   │       |   ┌── 78\n" +
                "|   │       └── 22\n" +
                "|   │           └── 62\n" +
                "└── 2\n" +
                "    |           ┌── 83\n" +
                "    |       ┌── 31\n" +
                "    |       │   └── 73\n" +
                "    |   ┌── 19\n" +
                "    |   │   |   ┌── 64\n" +
                "    |   │   └── 47\n" +
                "    |   │       └── 94\n" +
                "    └── 5\n" +
                "        |       ┌── 56\n" +
                "        |   ┌── 29\n" +
                "        |   │   └── 68\n" +
                "        └── 8\n" +
                "            |   ┌── 71\n" +
                "            └── 25\n" +
                "                └── 51\n");

        endSuite("Constructor Test");

        startSuite("Insert Test");
        //17, 94, 22, 56, 83, 10, 42, 71, 29, 64, 5, 78, 36, 90, 13, 51, 25, 68, 8, 47, 19, 73, 31, 62, 2, 88, 40, 76, 15, 59
        heap2 = new MinHeap<>();
        heap = new MaxHeap<>();

        assertEquals(heap.toString(),"");
        assertEquals(heap2.toString(),"");

        heap.push(17);
        assertEquals(heap.toString(),"└── 17\n");

        heap.push(94);
        assertEquals(heap.toString(),"└── 94\n" +
                "    └── 17\n");

        heap.push(22);
        assertEquals(heap.toString(),"|   ┌── 22\n" +
                "└── 94\n" +
                "    └── 17\n");

        heap.push(56);
        assertEquals(heap.toString(),"|   ┌── 22\n" +
                "└── 94\n" +
                "    └── 17\n");

        heap2.push(17);
        assertEquals(heap2.toString(),"└── 17\n");

        endSuite("Insert Test");

        endAll();
    }
}