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

        /*startSuite("Append Test");
        RecursiveArray myArray = new RecursiveArray();
        assertEquals(new Integer [] {1},new Integer [] {1}); //Test [] -> [1]
        myArray.array = new Integer[] {1};
        assertEquals(new Integer [] {1},new Integer [] {1,2}); //Test [1] -> [1,2]
        myArray.array = new Integer[] {1,2,3,4,5};
        assertEquals(new Integer [] {1},new Integer [] {1, 2, 3, 4, 5,6}); //Test [1,2,3,4,5] -> [1,2,3,4,5,6]
        endSuite("Append Test");*/

        endAll();
    }
}
