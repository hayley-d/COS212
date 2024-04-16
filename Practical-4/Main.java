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

        longTest();
        hashTest();


        startSuite("Logic Test");
        assertEquals(true,true);
        assertEquals(false,false);
        assertEquals(true,true);
        assertEquals(false,false);
        endSuite("Number Test");

        endAll();
    }

    public static void longTest(){
        startSuite("Prime Number Generator Test");
        PrimeNumberGenerator p = new PrimeNumberGenerator();

        //test if head is 2
        assertEquals(p.head.value,2);

        p.sieveOfEratosthenes();
        assertEquals(p.currentPrime(),2);
        assertEquals(p.nextPrime(),3);
        p.sieveOfEratosthenes();
        assertEquals(p.currentPrime(),3);
        assertEquals(p.nextPrime(),5);
        p.sieveOfEratosthenes();
        assertEquals(p.currentPrime(),5);
        assertEquals(p.nextPrime(),7);
        p.sieveOfEratosthenes();
        assertEquals(p.currentPrime(),7);
        assertEquals(p.nextPrime(),11);
        assertEquals(p.currentPrime(),11);
        assertEquals(p.nextPrime(),13);
        p.sieveOfEratosthenes();
        assertEquals(p.currentPrime(),13);
        assertEquals(p.nextPrime(),17);
        assertEquals(p.currentPrime(),17);
        assertEquals(p.nextPrime(),19);
        assertEquals(p.nextPrime(),23);
        p.sieveOfEratosthenes();
        assertEquals(p.nextPrime(),29);
        assertEquals(p.nextPrime(),31);
        assertEquals(p.nextPrime(),37);
        assertEquals(p.nextPrime(),41);
        assertEquals(p.nextPrime(),43);
        p.sieveOfEratosthenes();

        endSuite("Prime Number Generator Test");
    }

    public static void hashTest(){
        startSuite("Hash Test");
        Hashmap hash = new Hashmap();
        System.out.println(hash.hash(21528790));
        System.out.println(hash.hash(2928790));
        endSuite("Hash Test");
    }

}
