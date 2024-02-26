public class app {
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
        RecursiveArray myArray = new RecursiveArray("");
        assertEquals(myArray.array,new Integer [] {}); //Test [] -> []
        myArray = new RecursiveArray("1");
        assertEquals(myArray.array,new Integer [] {1}); //Test [1] -> [1]
        myArray = new RecursiveArray("1,2");
        assertEquals(myArray.array,new Integer [] {1, 2});
        myArray = new RecursiveArray("1,2,3,4,5,6,7");
        assertEquals(myArray.array,new Integer [] {1, 2,3,4,5,6,7});
        endSuite("Constructor Test");

        startSuite("Print Test");
        myArray = new RecursiveArray("");
        assertEquals(myArray.toString(), "Empty Array"); //Test empty array
        myArray.array = new Integer[] {1};
        assertEquals(myArray.toString(), "[1]"); //Test single element
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(myArray.toString(),"[1,2,3,4,5]"); //Test multiple elements
        endSuite("Print Test");

        startSuite("Append Test");
        myArray = new RecursiveArray("");
        myArray.append(1);
        assertEquals(myArray.array,new Integer [] {1}); //Test empty append [] - > [1]
        assertEquals(myArray.array.length,1);
        myArray = new RecursiveArray("1");
        myArray.append(2);
        assertEquals(myArray.array,new Integer [] {1,2}); //Test single existing append [1] -> [1,2]
        assertEquals(myArray.array.length,2);
        myArray = new RecursiveArray("1,2,3,4,5");
        myArray.append(6);
        assertEquals(myArray.array,new Integer [] {1, 2,3,4,5,6}); //Test many existing [1,2,3,4,5] -> [1,2,3,4,5,6]
        assertEquals(myArray.array.length,6);
        endSuite("Append Test");

        startSuite("Prepend Test");
        myArray = new RecursiveArray("");
        myArray.prepend(1);
        assertEquals(myArray.array,new Integer [] {1}); //Test empty append [] - > [1]
        assertEquals(myArray.array.length,1);
        myArray = new RecursiveArray("1");
        myArray.prepend(2);
        assertEquals(myArray.array,new Integer [] {2,1}); //Test single existing append [1] -> [2,1]
        assertEquals(myArray.array.length,2);
        myArray = new RecursiveArray("1,2,3,4,5");
        myArray.prepend(6);
        assertEquals(myArray.array,new Integer [] {6,1, 2,3,4,5}); //Test many existing [1,2,3,4,5] -> [6,1,2,3,4,5]
        assertEquals(myArray.array.length,6);
        endSuite("Prepend Test");

        startSuite("Contains Test");
        myArray = new RecursiveArray("");
        assertEquals(myArray.contains(1), false); //Test empty array
        assertEquals(myArray.contains(0), false); //Test empty array
        assertEquals(myArray.contains(null), false); //Test empty array
        myArray.array = new Integer[] {1};
        assertEquals(myArray.contains(1), true); //Test single element
        assertEquals(myArray.contains(2), false); //Test single element
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(myArray.contains(1),true); //Test multiple elements
        assertEquals(myArray.contains(2),true); //Test multiple elements
        assertEquals(myArray.contains(3),true); //Test multiple elements
        assertEquals(myArray.contains(4),true); //Test multiple elements
        assertEquals(myArray.contains(5),true); //Test multiple elements
        assertEquals(myArray.contains(6),false); //Test multiple elements
        assertEquals(myArray.contains(0),false); //Test multiple elements
        endSuite("Contains Test");

        startSuite("isAscending Test");
        //Empty Array Test
        myArray = new RecursiveArray("");
        assertEquals(myArray.isAscending(), true); //Test empty array

        //Single Element
        myArray.array = new Integer[] {1};
        assertEquals(myArray.isAscending(), true); //Test single element

        //Mant elements tests
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(myArray.isAscending(),true); //Test multiple elements
        myArray.array = new Integer[] {5,4,3,2,1};
        assertEquals(myArray.isAscending(),false); //Test multiple elements
        myArray.array = new Integer[] {3,4,5};
        assertEquals(myArray.isAscending(),true); //Test multiple elements
        myArray.array = new Integer[] {4,5};
        assertEquals(myArray.isAscending(),true); //Test multiple elements
        myArray.array = new Integer[] {1,2,3,4,5,6,7,8,1};
        assertEquals(myArray.isAscending(),false); //Test multiple elements
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(myArray.isAscending(),true); //Test multiple elements

        //same Element test
        myArray.array = new Integer[] {1,1,1,1,1,1};
        assertEquals(myArray.isAscending(),true); //Test same elements
        myArray.array = new Integer[] {1,1,2,2,2,2};
        assertEquals(myArray.isAscending(),true); //Test same elements
        myArray.array = new Integer[] {1,1,2,2,3,3,3,3,4,5,5,6,6,6,6,6,7,7,7};
        assertEquals(myArray.isAscending(),true); //Test same elements
        myArray.array = new Integer[] {1,1,2,1,1,2};
        assertEquals(myArray.isAscending(),false); //Test same elements
        myArray.array = new Integer[] {2,2,2,2,3,3,3,3,4,4,5};
        assertEquals(myArray.isAscending(),true); //Test same elements
        myArray.array = new Integer[] {2,2,2,2,2,1,1,1,1};
        assertEquals(myArray.isAscending(),false); //Test same elements
        endSuite("Ascending Test");

        startSuite("isDescending Test");
        //Empty Array Test
        myArray = new RecursiveArray("");
        assertEquals(myArray.isDescending(), true); //Test empty array

        //Single Element
        myArray.array = new Integer[] {1};
        assertEquals(myArray.isDescending(), true); //Test single element

        //Mant elements tests
        myArray.array = new Integer[] {9,8,7,6,5};
        assertEquals(myArray.isDescending(),true); //Test multiple elements
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(myArray.isDescending(),false); //Test multiple elements
        myArray.array = new Integer[] {5,4,3};
        assertEquals(myArray.isDescending(),true); //Test multiple elements
        myArray.array = new Integer[] {6,4};
        assertEquals(myArray.isDescending(),true); //Test multiple elements
        myArray.array = new Integer[] {8,7,6,5,4,3,2,1,9};
        assertEquals(myArray.isDescending(),false); //Test multiple elements
        myArray.array = new Integer[] {5,4,3,2,1};
        assertEquals(myArray.isDescending(),true); //Test multiple elements

        //same Element test
        myArray.array = new Integer[] {1,1,1,1,1,1};
        assertEquals(myArray.isDescending(),true); //Test same elements
        myArray.array = new Integer[] {7,6,5,4,3,2,2,2,1,1,1};
        assertEquals(myArray.isDescending(),true); //Test same elements
        myArray.array = new Integer[] {9,9,9,9,8,8,8,7,7,7,6,6,6,5,5,4,4,4,3,3,3,2,1};
        assertEquals(myArray.isDescending(),true); //Test same elements
        myArray.array = new Integer[] {6,6,3,3,8,6,5};
        assertEquals(myArray.isDescending(),false); //Test same elements
        myArray.array = new Integer[] {7,7,7,6,5,5,5,4,4,4,2,1};
        assertEquals(myArray.isDescending(),true); //Test same elements
        myArray.array = new Integer[] {3,3,3,3,3,8,8,8,8};
        assertEquals(myArray.isDescending(),false); //Test same elements
        endSuite("isDescending Test");

        startSuite("Ascending Test");
        //Empty test
        myArray = new RecursiveArray("");
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test empty array

        //Single Element test
        myArray.array = new Integer[] {1};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element

        //Multiple elements sorted
        myArray.array = new Integer[] {1,2};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        myArray.array = new Integer[] {1,2,3};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        myArray.array = new Integer[] {1,2,3,4};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element

        //Multiple Elements Descending Order
        myArray.array = new Integer[] {2,1};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        myArray.array = new Integer[] {3,2,1};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        myArray.array = new Integer[] {4,3,2,1};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        myArray.array = new Integer[] {9,8,7,6,5,4,3,2,1};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element

        //Multiple Elements random order
        myArray.array = new Integer[] {2,1,4,6,3,2,1,3,8,6};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element

        myArray.array = new Integer[] {9,1,0,3,6,2,8,4};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element

        myArray.array = new Integer[] {9,9,0,7,0,3,5,4};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        assertEquals(myArray.toString(), "[0,0,3,4,5,7,9,9]");

        myArray.array = new Integer[] {1,1,1,1,1,4,4,4,7,7,74,4,9,0,3,0};
        myArray.sortAscending();
        assertEquals(myArray.isAscending(), true); //Test single element
        assertEquals(myArray.toString(), "[0,0,1,1,1,1,1,3,4,4,4,4,7,7,9,74]");



        endSuite("Ascending Test");



        endAll();
    }
}
